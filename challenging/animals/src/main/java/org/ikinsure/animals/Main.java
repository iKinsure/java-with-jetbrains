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

    public static void main(String[] args) {

        // say hello based on time
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

        // ask for animal
        Scanner sc = new Scanner(System.in);
        print("\nEnter an animal:");
        String animal = sc.nextLine().strip().toLowerCase();

        // determine article
        if (!(animal.startsWith("an ") || animal.startsWith("a "))) {
            animal = isVowel(animal.charAt(0)) ? "an " + animal : "a " + animal;
        }

        // question
        Random rand = new Random();
        print("Is it " + animal + "?");
        while (true) {
            String response = sc.nextLine()
                    .strip()
                    .toLowerCase();
            if (response.endsWith(".") || response.endsWith("?") || response.endsWith("!")) {
                response = response.substring(0, response.length() - 1);
            }

            if (POSITIVE.contains(response)) {
                print("You answered: Yes");
                break;
            } else if (NEGATIVE.contains(response)) {
                print("You answered: No");
                break;
            } else {
                print(ASK_AGAIN.get(rand.nextInt(ASK_AGAIN.size())));
            }
        }

        // bye
        print("");
        print(GOODBYE.get(rand.nextInt(GOODBYE.size())));
    }

    private static boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }

    private static void print(String text) {
        System.out.println(text);
    }
}
