package com.ikinsure.hyperskill.hard.search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        List<Person> data = read(args[1]);
        Map<String, ArrayList<Integer>> map = Mappable.invertedIndex(data);
        while (true) {
            switch (menu()) {
                case 1:
                    System.out.println("\nEnter a name or email to search all suitable people.");
                    List<Integer> positions = map.get(SCANNER.nextLine());
                    if (positions == null) {
                        System.out.println("\nNo matching people found.");
                    } else {
                        List<Person> result = positions.stream().map(data::get).collect(Collectors.toList());
                        System.out.println("\nFound " + result.size() + " people:");
                        result.forEach(System.out::println);
                    }
                    break;
                case 2:
                    System.out.println("\n=== List of people ===");
                    data.forEach(System.out::println);
                    break;
                case 0:
                    System.out.println("\nBye!");
                    return;
                default:
                    System.out.println("\nIncorrect option! Try again.");
                    break;
            }
        }
    }

    private static int menu() {
        System.out.println("\n=== Menu ===\n" +
                           "1. Find a person\n" +
                           "2. Print all people\n" +
                           "0. Exit");
        return Integer.parseInt(SCANNER.nextLine());
    }

    private static List<Person> read(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines().map(Person::parsePerson).collect(Collectors.toList());
        }
    }
}
