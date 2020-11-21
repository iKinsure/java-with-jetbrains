package com.ikinsure.hyperskill.hard.flashcards;

import java.util.*;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Input the number of cards:");
        int size = Integer.parseInt(SCANNER.nextLine());

        Set<String> terms = new LinkedHashSet<>(size);
        Set<String> defs = new LinkedHashSet<>(size);

        for (int i = 0; i < size; i++) {
            System.out.println("Card #" + (i + 1) + ":");
            String term = SCANNER.nextLine();
            while (!terms.add(term)) {
                System.out.println("The term \"" + term + "\" already exists. Try again:");;
                term = SCANNER.nextLine();
            }
            System.out.println("The definition for card #" + (i + 1) + ":");
            String def = SCANNER.nextLine();
            while (!defs.add(def)) {
                System.out.println("The definition \"" + def + "\" already exists. Try again:");
                def = SCANNER.nextLine();
            }
        }

        ArrayList<String> termsList = new ArrayList<>(terms);
        ArrayList<String> defsList = new ArrayList<>(defs);

        for (int i = 0; i < size; i++) {
            System.out.println("Print the definition of \"" + termsList.get(i) + "\":");
            String input = SCANNER.nextLine();
            if (defsList.get(i).equals(input)) {
                System.out.println("Correct!");
            } else {
                System.out.println("Wrong. The right answer is \"" + defsList.get(i) + (defsList.contains(input)
                        ? "\", " + "but your definition is correct for \"" + termsList.get(defsList.indexOf(input)) + "\"."
                        : "\"."));
            }
        }
    }
}
