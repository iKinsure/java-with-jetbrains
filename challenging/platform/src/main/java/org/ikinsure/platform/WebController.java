package org.ikinsure.platform;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

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
    public String latest(Model model) {
        int length = CodeSharingPlatform.codes.size() - 1;
        List<Code> snippets = new ArrayList<>();
        IntStream.range(0, Math.min(length, 10))
                .forEach(i -> snippets.add(CodeSharingPlatform.codes.get(length - i)));
        Map<String, Object> root = new HashMap<>();
        root.put("snippets", snippets);
        model.addAllAttributes(root);
        return "latest";
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
