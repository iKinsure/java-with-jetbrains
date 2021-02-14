package org.ikinsure.animals;

import java.text.MessageFormat;
import java.time.LocalTime;
import java.util.*;

public class Resource {

    private final ResourceBundle messages;
    private final ResourceBundle patterns;
    private final Scanner sc;
    private final Random rand;

    public Resource(Scanner sc, Random rand) {
        this.sc = sc;
        this.rand = rand;
        this.messages = ResourceBundle.getBundle("messages");
        this.patterns = ResourceBundle.getBundle("patterns");
    }

    /**
     * returns message property as string by key
     */
    public String message(String key) {
        return messages.getString(key);
    }

    public String message(String key,Object ... args) {
        return MessageFormat.format(message(key), args);
    }

    /**
     * println property value by key
     */
    public void println(String key) {
        System.out.println(message(key));
    }

    public void println(String key, Object ... args) {
        System.out.println(message(key, args));
    }

    /**
     * print property value by key
     */
    public void print(String key) {
        System.out.print(message(key));
    }

    public void print(String key, Object ... args) {
        System.out.print(message(key, args));
    }

    /**
     * printf property value by key
     */
    public void printf(String key, Object... args) {
        System.out.printf(message(key), args);
    }

    /**
     * returns value from patterns as string
     */
    public String regex(String key) {
        return patterns.getString(key);
    }

    public void printlnRandom(String key) {
        String[] quotes = message(key).split("\f");
        System.out.println(quotes[rand.nextInt(quotes.length)]);
    }

    public void printRandom(String key) {
        String[] quotes = message(key).split("\f");
        System.out.print(quotes[rand.nextInt(quotes.length)]);
    }

    /**
     * get all patterns and replaces by key and format string
     */
    public String format(String key, String data) {
        int counter = 1;
        do {
            String pat = MessageFormat.format(key + ".{0}.pattern", counter);
            String rep = MessageFormat.format(key + ".{0}.replace", counter);
            if (patterns.containsKey(pat) && patterns.containsKey(rep)) {
                data = data.replaceFirst(regex(pat), regex(rep));
                counter++;
                continue;
            }
            break;
        } while (true);
        return data;
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    public void printHello() {
        LocalTime time = LocalTime.now();
        if (time.isBefore(LocalTime.of(5, 0))) {
            println("greeting.night");
        } else if (time.isBefore(LocalTime.of(7, 0))) {
            println("greeting.early");
        } else if (time.isBefore(LocalTime.of(12, 0))) {
            println("greeting.morning");
        } else if (time.isBefore(LocalTime.of(18, 0))) {
            println("greeting.evening");
        } else {
            println("greeting.afternoon");
        }
    }

    public String createAnimal() {
        println("animal.prompt");
        String animal = sc.nextLine();

        while (!animal.matches(regex("animal.isCorrect"))) {
            println("animal.error");
            println("animal.prompt");
            animal = sc.nextLine();
        }
        return format("animal", animal);
    }


    public String createStatement(String a1, String a2) {
        println("statement.prompt", a1, a2);
        String statement = sc.nextLine();

        while (!statement.matches(regex("statement.isCorrect"))) {
            println("statement.error");
            println("statement.prompt", a1, a2);
            statement = sc.nextLine();
        }
        return format("statement", statement);
    }

    public boolean inputYesOrNo() {
        String input = sc.nextLine();
        if (input.matches(regex("positiveAnswer.isCorrect"))) {
            return true;
        } else if (input.matches(regex("negativeAnswer.isCorrect"))) {
            return false;
        } else {
            printlnRandom("ask.again");
            return inputYesOrNo();
        }
    }

}
