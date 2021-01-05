package org.ikinsure.contacts.model;

import java.util.Scanner;
import java.util.function.Predicate;

public interface Settable {

    void setFields(Scanner scanner);

    default String enter(Scanner scanner, String field, String ... options) {
        String joiner = options.length >= 1 ? " (" + String.join(", ", options) + ")" : "";
        System.out.print("Enter the " + field + joiner + ": ");
        String input = scanner.nextLine();
        if (options.length >= 1) {
            for (var option : options) {
                if (option.equals(input)) {
                    return input;
                }
            }
            System.out.println("Wrong format!");
            return "[no data]";
        }
        return input;
    }

    default String enter(Scanner scanner, String field, Predicate<String> predicate) {
        System.out.print("Enter the " + field + ": ");
        String input = scanner.nextLine();
        if (predicate.test(input)) {
            return input;
        }
        System.out.println("Wrong format!");
        return "[no data]";
    }
}
