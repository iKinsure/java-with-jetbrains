package org.ikinsure.platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class CodeController {

    private final Logger LOGGER = LoggerFactory.getLogger(CodeController.class);
    private final CodeRepository repository;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    @Autowired
    public CodeController(CodeRepository repository) {
        this.repository = repository;
    }

    //////////////////// API ////////////////////////////////////

    @ResponseBody
    @GetMapping(value = "/api/code/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Code get(@PathVariable String id) {

        LOGGER.info("GET /api/code/" + id);
        Code code = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource"));

        // time counter
        if (code.getTime() > 0L) {
            code.setTime(Duration.between(LocalDateTime.now(), code.getDestroyDate()).getSeconds());
            if (code.getTime() <= 0L) {
                repository.deleteById(code.getId());
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
            }
        }

        // views counter
        long views = code.getViews();
        if (views > 0L) {
            code.setViews(--views);
            if (views <= 0L) {
                repository.deleteById(code.getId());
                return code;
            }
        }

        repository.save(code);
        return code;
    }

    @ResponseBody
    @GetMapping(value = "/api/code/latest", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Code> latest() {
        LOGGER.info("GET /api/code/latest");
        return repository.findFirst10ByTimeLessThanEqualAndViewsLessThanEqualOrderByCreateDateDesc(0 ,0);
    }

    @ResponseBody
    @PostMapping(value = "/api/code/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String post(@RequestBody Code code) {
        String uuid = UUID.randomUUID().toString();
        code.setId(uuid);
        LocalDateTime now = LocalDateTime.now();
        code.setCreateDate(now);
        code.setDate(now.format(FORMATTER));
        code.setDestroyDate(now.plusSeconds(code.getTime()));
        LOGGER.info("POST /api/code/new : " + code);
        repository.save(code);
        return "{ \"id\" : \"" + uuid + "\"}";
    }

    //////////////////// HTML ////////////////////////////////////

    @GetMapping(path = "/code/{id}")
    public String get(Model model, @PathVariable String id) {
        Code code = get(id);
        model.addAttribute("code", code.getCode());
        model.addAttribute("date", code.getDate());
        model.addAttribute("views", code.getViews());
        model.addAttribute("time", code.getTime());
        return "get";
    }

    @GetMapping(path = "/code/latest")
    public String latest(Model model) {
        List<Code> codes = latest();
        Map<String, Object> root = new HashMap<>();
        root.put("snippets", codes);
        model.addAllAttributes(root);
        return "latest";
    }

    @GetMapping(path = "/code/new")
    public String post() {
        return "post";
    }

    @GetMapping(path = "/")
    public String index() {
        return "index";
    }
}
