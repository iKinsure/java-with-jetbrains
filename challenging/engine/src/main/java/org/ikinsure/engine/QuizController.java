package org.ikinsure.engine;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class QuizController {

    @GetMapping("api/quiz")
    public Question getQuestion() {
        String title = "The Java Logo";
        String text = "What is depicted on the Java logo?";
        List<String> options = List.of("Robot", "Tea leaf", "Cup of coffee", "Bug");
        return new Question(title, text, options);
    }

    @PostMapping(value = "api/quiz")
    public Response answer(@RequestParam int answer) {
        return answer == 2
                ? new Response(true, "Congratulations, you're right!")
                : new Response(false, "Wrong answer! Please, try again.");
    }
}
