package org.ikinsure.search;

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
                    System.out.println("\nSelect a matching strategy: ALL, ANY, NONE");
                    String strategy = SCANNER.nextLine();
                    System.out.println("\nEnter a name or email to search all suitable people.");
                    String[] queries = SCANNER.nextLine().toLowerCase().split("\\s+");

                    Set<Integer> positions = new HashSet<>();
                    if (strategy.equals("ALL")) {
                        positions = intersection(map, queries);
                    } else if (strategy.equals("ANY")) {
                        positions = union(map, queries);
                    } else if (strategy.equals("NONE")) {
                        positions = difference(map, queries);
                    }

                    if (positions.isEmpty()) {
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

    private static Set<Integer> union(Map<String, ArrayList<Integer>> map, String[] queries) {
        Set<Integer> positions = new HashSet<>();
        for (String query : queries) {
            List<Integer> temp = map.get(query);
            if (temp != null && !temp.isEmpty()) {
                positions.addAll(map.get(query));
            }
        }
        return positions;
    }

    private static Set<Integer> intersection(Map<String, ArrayList<Integer>> map, String[] queries) {
        Set<Integer> result = new HashSet<>();
        ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
        for (String query : queries) {
            var entry = map.get(query);
            if (entry != null && !entry.isEmpty()) {
                temp.add(entry);
            }
        }
        if (temp.size() >= 1) {
            for (Integer i : temp.get(0)) {
                boolean contains = true;
                for (int j = 1; j < temp.size(); j++) {
                    if (!temp.get(j).contains(i)) {
                        contains = false;
                        break;
                    }
                }
                if (contains) {
                    result.add(i);
                }
            }
        }
        return result;
    }

    private static Set<Integer> difference(Map<String, ArrayList<Integer>> map, String[] queries) {
        Set<Integer> result = new HashSet<>();
        map.values().forEach(result::addAll);
        result.removeAll(union(map, queries));
        return result;
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
