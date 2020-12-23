package org.ikinsure.platform;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BotController {

    @PostMapping(value = "/greet", consumes = "application/json")
    public String greet(@RequestBody List<UserInfo> userInfoList) {
        StringBuilder builder = new StringBuilder();
        int counter = 0;
        for (UserInfo userInfo : userInfoList) {
            if (userInfo.isEnabled()) {
                builder.append(String.format("Hello! Nice to see you, %s!\n", userInfo.getName()));
                counter++;
            } else {
                builder.append(String.format("Hello, Nice to see you, %s! Your account is disabled\n", userInfo.getName()));
            }
        }
        builder.append(String.format("%d users have been logged out!", counter));
        return builder.toString();
    }
}
