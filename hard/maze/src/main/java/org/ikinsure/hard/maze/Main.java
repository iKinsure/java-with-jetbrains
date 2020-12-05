package org.ikinsure.hard.maze;
import java.io.*;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static Maze maze = null;

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            switch (SCANNER.nextInt()) {
                case 1:
                    generate();
                    break;
                case 2:
                    try {
                        load();
                        System.out.println("Success in loading file!");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Failed to load from file!");
                    }
                    break;
                case 3:
                    try {
                        save();
                        System.out.println("Success in saving file!");
                    } catch (IOException e) {
                        System.out.println("Failed to save into file!");
                    }
                    break;
                case 4:
                    display();
                    break;
                case 5:
                    escape();
                    break;
                default:
                    isRunning = false;
            }
        }
        System.out.println("Bye!");
    }

    private static void escape() {
        Maze path = maze.solve();
        System.out.println(path);
    }

    private static void display() {
        System.out.println(maze);
    }

    private static void save() throws IOException {
        String fileName = SCANNER.next();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(maze);
        }
    }

    private static void load() throws IOException, ClassNotFoundException {
        String fileName = SCANNER.next();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            maze = (Maze) in.readObject();
        }
    }

    private static void generate() {
        System.out.println("Please, enter the size of a maze: ");
        int height = SCANNER.nextInt();
        int width = SCANNER.nextInt();
        maze = new Maze(height, width);
        maze.randomize();
        display();
    }

    private static void printMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1. Generate a new maze");
        System.out.println("2. Load a maze");
        if (maze != null) {
            System.out.println("3. Save the maze");
            System.out.println("4. Display the maze");
            System.out.println("5. Find the escape");
        }
        System.out.println("0. Exit");
    }
}
