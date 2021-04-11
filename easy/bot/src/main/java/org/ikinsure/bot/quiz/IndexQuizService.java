package org.ikinsure.bot.quiz;

import java.util.Scanner;
import java.util.function.Consumer;

public class IndexQuizService implements QuizService {

    @Override
    public void ask(Quiz quiz, Consumer<String> output, Scanner scanner) {

        output.accept(quiz.getQuestion());

        int index = 1;
        for (String answer : quiz.getAnswers()) {
            output.accept(index + ". " + answer);
            index++;
        }

        while ((scanner.nextInt() - 1) != quiz.getCorrectIndex()) {
            output.accept("Please, try again.");
        }
        output.accept("Completed, have a nice day!");
    }
}
