package org.ikinsure.platform;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

    @GetMapping(value = "/api/code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Code get() {
        return CodeSharingPlatform.code;
    }

    @PostMapping(value = "/api/code/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String post(@RequestBody Code code) {
        CodeSharingPlatform.code = new Code(code.getCode());
        return "{}";
    }
}
