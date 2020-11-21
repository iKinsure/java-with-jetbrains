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
            while (true) {
                String term = SCANNER.nextLine();
                if (terms.add(term)) {
                    break;
                } else {
                    System.out.println("The term \"" + term + "\" already exists. Try again:");
                }
            }

            System.out.println("The definition for card #" + (i + 1) + ":");
            while (true) {
                String def = SCANNER.nextLine();
                if (defs.add(def)) {
                    break;
                } else {
                    System.out.println("The definition \"" + def + "\" already exists. Try again:");
                }
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
                if (defsList.contains(input)) {
                    System.out.println("Wrong. The right answer is \"" + defsList.get(i) + "\", " +
                            "but your definition is correct for \"" + termsList.get(defsList.indexOf(input)) + "\".");
                } else {
                    System.out.println("Wrong. The right answer is \"" + defsList.get(i) + "\".");
                }
            }
        }

    }
}
