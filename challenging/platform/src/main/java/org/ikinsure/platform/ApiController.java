package org.ikinsure.platform;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class ApiController {

    private final CodeRepository repository;

    public ApiController(CodeRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/api/code/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Code getById(@PathVariable long id) {
        return repository.findById(id);
    }

    @GetMapping(value = "/api/code/latest", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Code> latest() {
        List<Code> codes = repository.findByIdBetween(repository.count() - 9, repository.count());
        Collections.reverse(codes);
        return codes;
    }

    @PostMapping(value = "/api/code/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String post(@RequestBody Code code) {
        repository.save(new Code(code.getCode(), repository.count()));
        return "{ \"id\" : \"" + repository.count() + "\"}";
    }
}
