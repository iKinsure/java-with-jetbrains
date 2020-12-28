package org.ikinsure.platform;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {

    @GetMapping(path = "/code/{id}")
    public String get(Model model, @PathVariable int id) {
        Code code = CodeSharingPlatform.codes.get(id);
        model.addAttribute("code", code.getCode());
        model.addAttribute("date", code.getDate());
        return "get";
    }

    @GetMapping(path = "/code/latest")
    public String get(Model model) {
        Code code = CodeSharingPlatform.codes.get(CodeSharingPlatform.codes.size() - 1);
        model.addAttribute("code", code.getCode());
        model.addAttribute("date", code.getDate());
        return "get";
    }

    @GetMapping(path = "/code/new")
    public String submit(Model model) {
        return "post";
    }

    @GetMapping(path = "/")
    public String index(Model model) {
        return "index";
    }
}
