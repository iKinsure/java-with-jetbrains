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
    private static final Random RANDOM = new Random();


    public static void main(String[] args) {

        hello();
        print("I want to learn about animals.\n" +
                "Which animal do you like most?");

        FactFactory factory = new FactFactory();
        BinaryTree tree = new BinaryTree();
        Animal a1 = factory.createAnimal(input());
        tree.root = new Node(a1);

        print("Wonderful! I've learned so much about animals!\n" +
                "Let's play a game!\n" +
                "You think of an animal, and I guess it.\n" +
                "Press enter when you're ready.");
        input();

        Node node = tree.root;
        while (true) {
            print(node.fact.question());
            if (yesOrNo()) {
                if (node.right == null || node.left == null) {
                    print("Yeah!\n");
                    print("Would you like to play again?");
                    if (yesOrNo()) {
                        node = tree.root;
                    } else {
                        break;
                    }

                } else {
                    node = node.right;
                }
            } else {
                if (node.left == null || node.right == null) {
                    Node result = createStatement(factory, node);
                    tree.replace(node, result);
                    print("Would you like to play again?");
                    if (yesOrNo()) {
                        node = tree.root;
                    } else {
                        break;
                    }
                } else {
                    node = node.left;
                }
            }
        }

        print("\n" + GOODBYE.get(RANDOM.nextInt(GOODBYE.size())));
    }

    private static Node createStatement(FactFactory factory, Node previous) {
        Node result;
        print("I give up. What animal do you have in mind?");
        Animal a1 = (Animal) previous.fact;
        Animal a2 = factory.createAnimal(input());
        showSpecify(a1, a2);
        String data = input();
        print("Is the statement correct for " + a2.getUndefined() + "?");
        if (yesOrNo()) {
            result = new Node(factory.createFact(data));
            result.right = new Node(a2);
            result.left = new Node(previous.fact);
        } else {
            result = new Node(factory.createFact(data));
            result.right = new Node(previous.fact);
            result.left = new Node(a2);
        }
        showLearned(result);
        return result;
    }

    private static void showSpecify(Animal a1, Animal a2) {
        print("Specify a fact that distinguishes " + a1.getUndefined() + " from " + a2.getUndefined() + ".\n" +
                "The sentence should satisfy one of the following templates:\n" +
                "- It can ...\n" +
                "- It has ...\n" +
                "- It is a/an ...\n");
    }

   private static void showLearned(Node node) {
       print("I have learned the following facts about animals:");
       print(" - " + node.fact.sentence((Animal) node.right.fact, false));
       print(" - " + node.fact.sentence((Animal) node.left.fact, true));
       print("I can distinguish these animals by asking the question:");
       print(" - " + node.fact.question());
       print("Nice! I've learned so much about animals!");
       print("");
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

    private static boolean yesOrNo() {
        String input = input();
        if (POSITIVE.contains(input)) {
            return true;
        } else if (NEGATIVE.contains(input)) {
            return false;
        } else {
            print(ASK_AGAIN.get(RANDOM.nextInt(ASK_AGAIN.size())));
            return yesOrNo();
        }
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
        print("");
    }
}
