package org.ikinsure.animals;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final List<String> POSITIVE = List.of(
            "y", "yes", "yeah", "yep", "sure", "right", "affirmative",
            "correct", "indeed", "you bet", "exactly", "you said it");
    private static final List<String> NEGATIVE = List.of(
            "n", "no", "no way", "nah", "nope", "negative", "i don't think so", "yeah no");
    private static final List<String> ASK_AGAIN = List.of(
            "I'm not sure I caught you: was it yes or no?",
            "Funny, I still don't understand, is it yes or no?",
            "Oh, it's too complicated for me: just tell me yes or no.",
            "Could you please simply say yes or no?",
            "Oh, no, don't try to confuse me: say yes or no.");
    private static final List<String> GOODBYE = List.of(
            "Have a nice day!", "See you soon!", "Bye!", "Talk to you later!",
            "See you later!", "Catch you later!", "Have a good one!");

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        hello();


        // enter animals
        print("I want to learn about animals.");
        print("Which animal do you like most?");

        AnimalFactory factory = new AnimalFactory();
        print("\nEnter the first animal:");
        Animal a1 = factory.parse(input());
        print("Enter the second animal:");
        Animal a2 = factory.parse(input());



        Random rand = new Random();
        while (true) {
            print("Specify a fact that distinguishes " + a1.getUndefined() + " from " + a2.getUndefined() + ".");
            print("The sentence should be of the format: 'It can/has/is ...'.\n");

            String in = input();
            FactCategory category;
            String fact;
            if (in.startsWith("it can ")) {
                category = FactCategory.ABILITY;
                fact = in.substring(7);



            } else if (in.startsWith("it has ")) {
                category = FactCategory.POSSESS;
                fact = in.substring(7);



            } else if (in.startsWith("it is ")) {
                category = FactCategory.LINKING;
                fact = in.substring(6);



            } else {
                print("The examples of a statement:\n" +
                        " - It can fly\n" +
                        " - It has horn\n" +
                        " - It is a mammal");
                continue;
            }

            while (true) {
                print("Is it correct for " + a2.getUndefined() + "?");
                in = input();
                if (POSITIVE.contains(in)) {
                    a1.add(new Fact(fact, category, true));
                    a2.add(new Fact(fact, category, false));
                    break;
                } else if (NEGATIVE.contains(in)) {
                    a1.add(new Fact(fact, category, false));
                    a2.add(new Fact(fact, category, true));
                    break;
                } else {
                    print(ASK_AGAIN.get(rand.nextInt(ASK_AGAIN.size())));
                }
            }



            print("I learned the following facts about animals:");
            print(a1.getProperties());
            print(a2.getProperties());

            print("I can distinguish these animals by asking the question:");
            if (category == FactCategory.ABILITY) {
                print(" - Can it " + fact + "?");
            } else if (category == FactCategory.LINKING) {
                print(" - Is it " + fact + "?");
            } else if (category == FactCategory.POSSESS) {
                print(" - Does it have " + fact + "?");
            }

            break;
        }

        // bye
        print("");
        print(GOODBYE.get(rand.nextInt(GOODBYE.size())));
    }

    private static String upperCase(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    private static void process(String s) {

    }

    private static void print(String text) {
        System.out.println(text);
    }

    private static String input() {
        return SCANNER.nextLine().strip().toLowerCase()
                .replace("?", "")
                .replace("!", "")
                .replace(".", "");
    }

    private static void hello() {
        LocalTime time = LocalTime.now();
        if (time.isBefore(LocalTime.of(5, 0))) {
            print("Hi, Night Owl!");
        } else if (time.isBefore(LocalTime.of(12, 0))) {
            print("Good morning!");
        } else if (time.isBefore(LocalTime.of(18, 0))) {
            print("Good evening!");
        } else {
            print("Good afternoon!");
        }
    }
}
