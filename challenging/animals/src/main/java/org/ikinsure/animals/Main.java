package org.ikinsure.animals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Arrays;
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
    private static final String FILENAME = "animals";

    public static void main(String[] args) {

        List<String> arguments = Arrays.asList(args);
        int index = arguments.indexOf("-type");
        String type = "json";
        if (index != -1) {
            type = arguments.get(index + 1).toLowerCase();
        }

        DataFormatter formatter = new DataFormatter();
        print(formatter.generateHello() + "\n");

        BinaryTree tree;

        try {
            tree = loader(type);
        } catch (IOException e) {
            e.printStackTrace();
            print("I want to learn about animals.\n" +
                    "Which animal do you like most?");
            tree = new BinaryTree();
            String a1 = formatter.createAnimal(input());
            tree.root = new Node(a1);
        }

        showStart();
        Node node = tree.root;
        while (true) {
            print(question(formatter, node));
            if (inputYesOrNo()) {

                if (node.isAnimal()) {
                    print("Yeah!\n");
                    print("Would you like to play again?");
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
                    Node result = createStatement(formatter, node);
                    tree.replace(node, result);
                    print("Would you like to play again?");
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

        try {
            saver(tree, type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        print("\n" + GOODBYE.get(RANDOM.nextInt(GOODBYE.size())));
    }

    private static String question(DataFormatter formatter, Node node) {
        if (node.isAnimal()) {
            return "Is it " + node.data + "?";
        }
        return formatter.question(node.data);
    }

    private static Node createStatement(DataFormatter formatter, Node previous) {
        Node result;
        print("I give up. What animal do you have in mind?");
        String a1 = previous.data;
        String a2 = formatter.createAnimal(input());
        showSpecify(a1, a2);
        String data = input();
        print("Is the statement correct for " + a2 + "?");
        if (inputYesOrNo()) {
            result = new Node(formatter.createFact(data));
            result.right = new Node(a2);
            result.left = new Node(a1);
        } else {
            result = new Node(formatter.createFact(data));
            result.right = new Node(a1);
            result.left = new Node(a2);
        }
        showLearned(formatter, result);
        return result;
    }

    private static void showStart() {
        print("Wonderful! I've learned so much about animals!\n" +
                "Let's play a game!\n" +
                "You think of an animal, and I guess it.\n" +
                "Press enter when you're ready.");
        input();
    }

    private static void showSpecify(String a1, String a2) {
        print("Specify a fact that distinguishes " + a1 + " from " + a2 + ".\n" +
                "The sentence should satisfy one of the following templates:\n" +
                "- It can ...\n" +
                "- It has ...\n" +
                "- It is a/an ...\n");
    }

    private static void showLearned(DataFormatter formatter, Node node) {
        String prop = formatter.createProp(node.right.data, node.data);
        String con = formatter.createCon(node.left.data, node.data);
        print("I have learned the following facts about animals:");
        print(" - " + prop);
        print(" - " + con);
        print("I can distinguish these animals by asking the question:");
        print(" - " + question(formatter, node));
        print("Nice! I've learned so much about animals!");
        print("");
    }


    private static void print(String text) {
        System.out.println(text);
    }

    private static String input() {
        String word = SCANNER.nextLine().strip().toLowerCase();
        if (word.endsWith(".") || word.endsWith("!") || word.endsWith("?")) {
            word = word.substring(0, word.length() - 1);
        }
        return word;
    }

    private static boolean inputYesOrNo() {
        String input = input();
        if (POSITIVE.contains(input)) {
            return true;
        } else if (NEGATIVE.contains(input)) {
            return false;
        } else {
            System.out.println(randomQuote(ASK_AGAIN));
            return inputYesOrNo();
        }
    }

    private static String randomQuote(List<String> data) {
        return data.get(RANDOM.nextInt(data.size()));
    }


    private static void saver(BinaryTree tree, String type) throws IOException {
        ObjectMapper mapper;
       if (type.equals("xml")) {
           mapper = new XmlMapper();
       } else if (type.equals("yaml")) {
           mapper = new YAMLMapper();
       } else {
           mapper = new JsonMapper();
       }
        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(FILENAME + "." + type), tree);
    }

    private static BinaryTree loader(String type) throws IOException {
        ObjectMapper mapper;
        if ("xml".equals(type)) {
            mapper = new XmlMapper();
        } else if ("yaml".equals(type)) {
            mapper = new YAMLMapper();
        } else {
            mapper = new JsonMapper();
        }
        return mapper.readValue(new File(FILENAME + "." + type), BinaryTree.class);
    }
}
