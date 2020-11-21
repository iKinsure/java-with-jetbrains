package com.ikinsure.hyperskill.hard.flashcards;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Input the number of cards:");
        int size = Integer.parseInt(SCANNER.nextLine());

        List<Card> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            System.out.println("Card #" + (i + 1) + ":");
            String name = SCANNER.nextLine();
            System.out.println("The definition for card #" + (i + 1) + ":");
            String def = SCANNER.nextLine();
            list.add(new Card(name, def));
        }

        for (Card card : list) {
            System.out.println("Print the definition of \"" + card.getTerm() + "\":");
            if (card.getDefinition().equals(SCANNER.nextLine())) {
                System.out.println("Correct!");
            } else {
                System.out.println("Wrong. The right answer is \"" + card.getDefinition() + "\".");
            }
        }
    }
}
