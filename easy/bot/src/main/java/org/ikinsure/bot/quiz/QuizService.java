package org.ikinsure.bot.quiz;

import java.util.Scanner;
import java.util.function.Consumer;

@FunctionalInterface
public interface QuizService {
    void ask(Quiz quiz, Consumer<String> output, Scanner scanner);
}
