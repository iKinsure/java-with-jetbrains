package org.ikinsure.contacts.model;

import java.util.*;
import java.util.function.Predicate;

/**
 * class represents one entry field in contact
 */
public class Entry implements Settable {

    String value; // mutable field
    final String key;
    final String printKey;
    final Predicate<String> predicate; // method to test value
    final List<String> options; // value needs to be included there

    Entry(String key, String printKey, String value, Predicate<String>  predicate, List<String> options) {
        this.key = key;
        this.printKey = printKey;
        this.value = value;
        this.predicate = predicate;
        this.options = options;
    }

    Entry(String key, String printKey, String value, List<String> options) {
        this(key, printKey, value, s -> !s.isBlank(), options);
    }

    Entry(String key, String printKey, String value, Predicate<String>  predicate) {
        this(key, printKey, value, predicate, new ArrayList<>());
    }

    Entry(String key, String printKey, String value) {
        this(key, printKey, value, s -> !s.isBlank(), new ArrayList<>());
    }

    Entry(Entry entry) {
        this.key = entry.key;
        this.printKey = entry.printKey;
        this.value = new String(entry.value);
        this.predicate = entry.predicate;
        this.options = entry.options;
    }

    @Override
    public void setValue(Scanner scanner) {
        String joiner = options.isEmpty() ? "" : " (" + String.join(", ", options) + ")";
        System.out.print("Enter the " + key + joiner + ": ");
        String input = scanner.nextLine();
        if (predicate.test(input)) {
            if (options.isEmpty()) {
                this.value = input;
                return;
            } else {
                for (var option : options) {
                    if (option.equals(input)) {
                        this.value = input;
                        return;
                    }
                }
            }
        }
        System.out.println("Wrong format!");
        this.value = "[no data]";
    }
}
