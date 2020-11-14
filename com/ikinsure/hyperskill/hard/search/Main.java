package com.ikinsure.hyperskill.hard.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        List<Person> list = enterPeople();

        while (true) {
            switch (menu()) {
                case 1:
                    System.out.println("\nEnter a name or email to search all suitable people.");
                    List<Person> result = search(list, SCANNER.nextLine());
                    if (result.isEmpty()) {
                        System.out.println("\nNo matching people found.");
                    } else {
                        System.out.println("\nFound people:");
                        result.forEach(System.out::println);
                    }
                    break;
                case 2:
                    System.out.println("\n=== List of people ===");
                    list.forEach(System.out::println);
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

    private static List<Person> enterPeople() {
        System.out.println("\nEnter the number of people");
        int people = Integer.parseInt(SCANNER.nextLine());
        ArrayList<Person> list = new ArrayList<>();
        System.out.println("\nEnter all people:");
        for (int i = 0; i < people; i++) {
            list.add(Person.parsePerson(SCANNER.nextLine()));
        }
        return list;
    }

    private static List<Person> search(List<Person> data, String datum) {
        return data.stream().filter(p ->
                    p.getFirstName().toLowerCase().contains(datum.toLowerCase()) ||
                    p.getLastName().toLowerCase().contains(datum.toLowerCase()) ||
                    p.getEmail().toLowerCase().contains(datum.toLowerCase()))
                .collect(Collectors.toList());
    }
}
