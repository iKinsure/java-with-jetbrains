package org.ikinsure.platform;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebController {

    private final CodeRepository repository;

    public WebController(CodeRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/code/{id}")
    public String get(Model model, @PathVariable int id) {
        Code code = repository.findById(id);
        model.addAttribute("code", code.getCode());
        model.addAttribute("date", code.getDate());
        return "get";
    }

    @GetMapping(path = "/code/latest")
    public String latest(Model model) {
        Map<String, Object> root = new HashMap<>();
        List<Code> codes = repository.findByIdBetween(repository.count() - 9, repository.count());
        Collections.reverse(codes);
        root.put("snippets", codes);
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
