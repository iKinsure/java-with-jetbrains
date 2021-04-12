package org.ikinsure.bot.quiz;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

import static com.github.stefanbirkner.systemlambda.SystemLambda.*;
import static org.junit.jupiter.api.Assertions.*;

public class QuizServiceTest {

    @Test
    void shouldOutputMatch() throws Exception {

        QuizService service = new IndexQuizService();
        Quiz quiz = Quiz.builder()
                .setQuestion("is this test")
                .setAnswers(List.of("no", "i don't know", "yes"))
                .setCorrectIndex(2)
                .build();

        String[] expected = {
                "is this test",
                "1. no",
                "2. i don't know",
                "3. yes",
                "Please, try again.",
                "Please, try again.",
                "Completed, have a nice day!"
        };

        withTextFromSystemIn("1", "99", "3").execute(() -> {

            String[] actual = tapSystemOutNormalized(() -> service.ask(
                            quiz,
                            System.out::println,
                            new Scanner(System.in)))
                    .split("\n");

            assertArrayEquals(expected, actual);
        });

    }

}
