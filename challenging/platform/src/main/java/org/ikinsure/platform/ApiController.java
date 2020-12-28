package org.ikinsure.platform;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

    @GetMapping(value = "/api/code/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Code getById(@PathVariable int id) {
        return CodeSharingPlatform.codes.get(id);
    }

    @GetMapping(value = "/api/code/latest", produces = MediaType.APPLICATION_JSON_VALUE)
    public Code[] latest() {
        int length = CodeSharingPlatform.codes.size() - 1;
        Code[] codes = new Code[Math.min(length, 10)];
        for (int i = 0; i < codes.length; i++) {
            codes[i] = CodeSharingPlatform.codes.get(length - i);
        }
        return codes;
    }

    @PostMapping(value = "/api/code/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String post(@RequestBody Code code) {
        CodeSharingPlatform.codes.add(new Code(code.getCode()));
        return "{ \"id\" : \"" + (CodeSharingPlatform.codes.size() - 1) + "\"}";
    }
}
