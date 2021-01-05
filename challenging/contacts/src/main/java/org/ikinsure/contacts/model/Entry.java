package org.ikinsure.contacts.model;

import java.util.*;
import java.util.function.Predicate;

public class Entry implements Settable {

    String value;
    final String key;
    final Predicate<String> predicate;
    final List<String> options;

    Entry(String key, String value, Predicate<String> predicate, List<String> options) {
        this.key = key;
        this.value = value;
        this.predicate = predicate;
        this.options = options;
    }

    Entry(String key, String value, List<String> options) {
        this(key, value, s -> true, options);
    }

    Entry(String key, String value, Predicate<String> predicate) {
        this(key, value, predicate, new ArrayList<>());
    }

    Entry(String key, String value) {
        this(key, value, s -> true, new ArrayList<>());
    }

    Entry(Entry entry) {
        this.key = entry.key;
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
