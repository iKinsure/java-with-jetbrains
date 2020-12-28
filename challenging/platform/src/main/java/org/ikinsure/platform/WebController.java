package org.ikinsure.platform;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@Controller
public class WebController {

    @GetMapping(path = "/code/{id}")
    public String get(Model model, @PathVariable int id) {
        Code code = CodeContainer.getByIndex(id);
        model.addAttribute("code", code.getCode());
        model.addAttribute("date", code.getDate());
        return "get";
    }

    @GetMapping(path = "/code/latest")
    public String latest(Model model) {
        Map<String, Object> root = new HashMap<>();
        root.put("snippets", CodeContainer.getLatest(10));
        model.addAllAttributes(root);
        return "latest";
    }

    @GetMapping(path = "/code/new")
    public String submit() {
        return "post";
    }

    @GetMapping(path = "/")
    public String index() {
        return "index";
    }
}
