package org.ikinsure.bot.bot;

import org.ikinsure.bot.quiz.QuizService;
import org.ikinsure.bot.quiz.Quiz;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class AidBot implements Bot {

    private final Scanner scanner;
    private final Consumer<String> output;
    private final String name;
    private final int birthYear;
    private final QuizService quizService;

    public AidBot(Scanner scanner, Consumer<String> output, String name, int birthYear, QuizService quizService) {
        this.scanner = scanner;
        this.output = output;
        this.name = name;
        this.birthYear = birthYear;
        this.quizService = quizService;
    }

    @Override
    public void greetings() {
        output.accept("Hello! My name is " + name + ".");
        output.accept("I was created in " + birthYear + ".");
        output.accept("Please, remind me your name.");
        output.accept("What a great name you have, " + scanner.nextLine() + "!");
    }

    @Override
    public void guessAge() {
        output.accept("Let me guess your age.");
        output.accept("Tell me remainders of dividing your age by 3, 5 and 7.");
        int age = (scanner.nextInt() * 70 + scanner.nextInt() * 21 + scanner.nextInt() * 15) % 105;
        output.accept("Your age is " + age + ", that's a good time to start programming!");
    }

    @Override
    public void counting() {
        output.accept("Now I will prove to you that I can count to any number you want.");
        IntStream.rangeClosed(0, scanner.nextInt())
                .forEach(i -> output.accept(i + "!"));
    }

    @Override
    public void quiz() {

        Quiz quiz = Quiz.builder()
                .setQuestion("Why do we use methods?")
                .setAnswers(List.of(
                        "To repeat a statement multiple times.",
                        "To decompose a program into several small subroutines.",
                        "To determine the execution time of a program.",
                        "To interrupt the execution of a program."))
                .setCorrectIndex(1)
                .build();

        quizService.ask(quiz, output, scanner);
    }

    @Override
    public void bye() {
        output.accept("Congratulations, have a nice day!");
    }

}
