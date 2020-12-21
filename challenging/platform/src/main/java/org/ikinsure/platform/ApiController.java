package org.ikinsure.platform;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class ApiController {

    @RequestMapping(value = "/api/code", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> get() {
        return Collections.singletonMap("code",
                "public static void main(String[] args) {\n    SpringApplication.run(CodeSharingPlatform.class, args);\n}");
    }
}
