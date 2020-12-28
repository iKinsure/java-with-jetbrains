package org.ikinsure.platform;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    @GetMapping(value = "/api/code/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Code getById(@PathVariable int id) {
        return CodeContainer.getByIndex(id);
    }

    @GetMapping(value = "/api/code/latest", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Code> latest() {
        return CodeContainer.getLatest(10);
    }

    @PostMapping(value = "/api/code/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String post(@RequestBody Code code) {
        CodeContainer.add(new Code(code.getCode()));
        return "{ \"id\" : \"" + (CodeContainer.size() - 1) + "\"}";
    }
}
