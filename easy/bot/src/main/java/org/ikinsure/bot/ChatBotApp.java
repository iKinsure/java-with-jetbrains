package org.ikinsure.bot;

import org.ikinsure.bot.bot.AidBot;
import org.ikinsure.bot.bot.Bot;
import org.ikinsure.bot.quiz.IndexQuizService;
import org.ikinsure.bot.quiz.QuizService;

import java.util.Scanner;
import java.util.function.Consumer;

public class ChatBotApp {

    /**
     * Entry app point
     */
    public static void main(String[] args) {

        // bot config
        final Scanner scanner = new Scanner(System.in);
        final Consumer<String> output = System.out::println;
        final String name = "Aid";
        final int birthYear = 2018;
        final QuizService service = new IndexQuizService();

        // run bot
        Bot bot = new AidBot(scanner, output, name, birthYear, service);
        bot.run();

        scanner.close();
    }
}
