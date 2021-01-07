package org.ikinsure.contacts;

import java.io.*;
import java.util.Scanner;

public class Main {

    private static final String FILE = "phonebook.obj";

    public static void main(String[] args) {
        PhoneBook phoneBook;
        try {
            phoneBook = read(FILE);
            System.out.println("Loaded phonebook from file: " + FILE);
        } catch (IOException | ClassNotFoundException e) {
            phoneBook = new PhoneBook();
            System.out.println("Failed to load phonebook from file: " + FILE);
        }
        App app = new App(new Scanner(System.in), phoneBook);
        try {
            write(app.getPhoneBook(), FILE);
            System.out.println("Saved phonebook to file: " + FILE);
        } catch (IOException e) {
            System.out.println("Failed when saving phonebook to file: " + FILE);
        }
    }

    private static PhoneBook read(String file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (PhoneBook) in.readObject();
        }
    }

    private static void write(PhoneBook phoneBook, String file) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(phoneBook);
        }
    }
}
