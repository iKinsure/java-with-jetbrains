package com.ikinsure.hyperskill.hard.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("\nEnter the number of people");
        int people = Integer.parseInt(SCANNER.nextLine());

        ArrayList<Person> list = new ArrayList<>();
        System.out.println("\nEnter all people:");
        for (int i = 0; i < people; i++) {
            String[] data = SCANNER.nextLine().split("\\s+");
            list.add(new Person(
                    data.length >= 1 ? data[0] : "",
                    data.length >= 2 ? data[1] : "",
                    data.length >= 3 ? data[2] : ""));
        }

        System.out.println("\nEnter the number of search queries:");
        int queries = Integer.parseInt(SCANNER.nextLine());

        for (int i = 0; i < queries; i++) {
            System.out.println("\nEnter data to search people:");
            List<Person> result = search(list, SCANNER.nextLine());
            if (result.isEmpty()) {
                System.out.println("\nNo matching people found.");
            } else {
                System.out.println("\nFound people:");
                result.forEach(System.out::println);
            }
        }

    }

    private static List<Person> search(List<Person> list, String data) {
        ArrayList<Person> result = new ArrayList<>();
        for (Person p : list) {
            if (p.getFirstName().toLowerCase().contains(data.toLowerCase())) {
                result.add(p);
            } else if (p.getLastName().toLowerCase().contains(data.toLowerCase())) {
                result.add(p);
            } else if (p.getEmail().toLowerCase().contains(data.toLowerCase())) {
                result.add(p);
            }
        }
        return result;
    }
}
