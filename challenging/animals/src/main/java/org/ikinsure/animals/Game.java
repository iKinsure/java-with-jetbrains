package org.ikinsure.animals;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private final BinaryTree tree;
    private final Scanner scanner;
    private final DataFormatter formatter;
    private final Random random;

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

    public Game(BinaryTree tree, Scanner scanner, DataFormatter formatter, Random random) {
        this.tree = tree;
        this.scanner = scanner;
        this.formatter = formatter;
        this.random = random;
    }

    public void start() {

        showStart();
        Node node = tree.root;

        while (true) {
            System.out.println(question(node));

            if (inputYesOrNo()) {

                if (node.isAnimal()) {
                    System.out.println("Yeah!\n");
                    System.out.println("Would you like to play again?");

                    if (inputYesOrNo()) {
                        node = tree.root;
                        showStart();
                    } else {
                        break;
                    }

                } else {

                    node = node.right;

                }

            } else {

                if (node.isAnimal()) {

                    Node result = createStatement(node);
                    tree.replace(node, result);
                    System.out.println("Would you like to play again?");

                    if (inputYesOrNo()) {
                        node = tree.root;
                        showStart();
                    } else {
                        break;
                    }

                } else {
                    node = node.left;
                }
            }

        }


    }


    private void showStart() {
        System.out.println("Wonderful! I've learned so much about animals!\n" +
                "Let's play a game!\n" +
                "You think of an animal, and I guess it.\n" +
                "Press enter when you're ready.");
        Main.input(scanner);
    }

    private boolean inputYesOrNo() {
        String input = Main.input(scanner);
        if (POSITIVE.contains(input)) {
            return true;
        } else if (NEGATIVE.contains(input)) {
            return false;
        } else {
            System.out.println(formatter.randomQuote(random, ASK_AGAIN));
            return inputYesOrNo();
        }
    }

    private String question(Node node) {
        if (node.isAnimal()) {
            return "Is it " + node.data + "?";
        }
        return formatter.question(node.data);
    }

    private Node createStatement(Node previous) {
        Node result;
        System.out.println("I give up. What animal do you have in mind?");
        String a1 = previous.data;
        String a2 = formatter.createAnimal(Main.input(scanner));
        showSpecify(a1, a2);
        String data = Main.input(scanner);
        System.out.println("Is the statement correct for " + a2 + "?");
        if (inputYesOrNo()) {
            result = new Node(formatter.createFact(data));
            result.right = new Node(a2);
            result.left = new Node(a1);
        } else {
            result = new Node(formatter.createFact(data));
            result.right = new Node(a1);
            result.left = new Node(a2);
        }
        showLearned(result);
        return result;
    }

    private void showSpecify(String a1, String a2) {
        System.out.println("Specify a fact that distinguishes " + a1 + " from " + a2 + ".\n" +
                "The sentence should satisfy one of the following templates:\n" +
                "- It can ...\n" +
                "- It has ...\n" +
                "- It is a/an ...\n");
    }

    private void showLearned(Node node) {
        String prop = formatter.createProp(node.right.data, node.data);
        String con = formatter.createCon(node.left.data, node.data);
        System.out.println("I have learned the following facts about animals:");
        System.out.println(" - " + prop);
        System.out.println(" - " + con);
        System.out.println("I can distinguish these animals by asking the question:");
        System.out.println(" - " + question(node));
        System.out.println("Nice! I've learned so much about animals!");
        System.out.println("");
    }
}
