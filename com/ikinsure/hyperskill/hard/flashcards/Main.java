package com.ikinsure.hyperskill.hard.flashcards;

import java.io.*;
import java.util.*;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ArrayList<String> terms = new ArrayList<>();
    private static final ArrayList<String> defs = new ArrayList<>();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\nInput the action (add, remove, import, export, ask, exit):");
            String command = SCANNER.nextLine();
            if (command.equalsIgnoreCase("add")) {
                addCommand();
            } else if (command.equalsIgnoreCase("remove")) {
                removeCommand();
            } else if (command.equalsIgnoreCase("import")) {
                importCommand();
            } else if (command.equalsIgnoreCase("export")) {
                exportCommand();
            } else if (command.equalsIgnoreCase("ask")) {
                askCommand();
            } else if (command.equalsIgnoreCase("exit")) {
                System.out.println("Bye bye!");
                break;
            }
        }
    }

    private static void askCommand() {
        System.out.println("How many times to ask?");
        int size = Integer.parseInt(SCANNER.nextLine());
        for (int i = 0; i < size; i++) {
            System.out.println("Print the definition of \"" + terms.get(i) + "\":");
            String input = SCANNER.nextLine();
            if (defs.get(i).equals(input)) {
                System.out.println("Correct!");
            } else {
                System.out.println("Wrong. The right answer is \"" + defs.get(i) + (defs.contains(input)
                        ? "\", " + "but your definition is correct for \"" + terms.get(defs.indexOf(input)) + "\"."
                        : "\"."));
            }
        }
    }

    private static void exportCommand() {
        System.out.println("File name:");
        String file = SCANNER.nextLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < terms.size(); i++) {
                writer.write(terms.get(i));
                writer.write(";");
                writer.write(defs.get(i));
                writer.newLine();
            }
            System.out.println(terms.size() + " cards have been saved.");
        } catch (IOException e) {
            System.out.println("Error when saving.");
        }
    }

    private static void importCommand() {
        System.out.println("File name:");
        String file = SCANNER.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int counter = 0;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                int termIndex = terms.indexOf(data[0]);
                counter++;
                if (termIndex == -1) {
                    terms.add(data[0]);
                    defs.add(data[1]);
                } else {
                    defs.set(termIndex, data[1]);
                }
            }
            System.out.println(counter + " cards have been loaded.");
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    private static void removeCommand() {
        System.out.println("Which card?");
        String term = SCANNER.nextLine();
        int index = terms.indexOf(term);
        if (index == -1) {
            System.out.println("Can't remove \"" + term + "\": there is no such card.");
        } else {
            terms.remove(index);
            defs.remove(index);
            System.out.println("The card has been removed.");
        }
    }

    private static void addCommand() {
        System.out.println("The card:");
        String term = SCANNER.nextLine();
        if (terms.contains(term)) {
            System.out.println("The card \"" + term + "\" already exists.");
            return;
        }
        System.out.println("The definition of the card:");
        String def = SCANNER.nextLine();
        if (defs.contains(def)) {
            System.out.println("The definition \"" + def + "\" already exists.");
            return;
        }
        terms.add(term);
        defs.add(def);
        System.out.println("The pair (\"" + term + "\":\"" + def + "\") has been added.");
    }
}
