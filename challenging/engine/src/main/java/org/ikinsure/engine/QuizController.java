package org.ikinsure.engine;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuizController {

    private final List<Question> questions = new ArrayList<>();

    @PostMapping("api/quizzes")
    public Question createQuestion(@RequestBody Question question) {
        question.setId(questions.size());
        questions.add(question);
        return question;
    }

    @PostMapping("api/quizzes/{id}/solve")
    public Response solveQuestion(@PathVariable int id, @RequestParam int answer) {
        return findQuestionById(id).getAnswer() == answer
                ? new Response(true, "Congratulations, you're right!")
                : new Response(false, "Wrong answer! Please, try again.");
    }

    @GetMapping("api/quizzes/{id}")
    public Question findQuestionById(@PathVariable int id) {
        return questions.stream()
                .filter(q -> q.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("api/quizzes")
    public List<Question> getQuestions() {
        return questions;
    }

}
