package org.ikinsure.animals;

import org.ikinsure.animals.view.Menu;
import org.ikinsure.animals.view.MenuController;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {

    private static final List<String> GOODBYE = List.of(
            "Have a nice day!", "See you soon!", "Bye!", "Talk to you later!",
            "See you later!", "Catch you later!", "Have a good one!");

    private final Scanner scanner;
    private final MenuController controller;
    private final Menu main;
    private final BinaryTree tree;
    private final DataFormatter formatter;
    private final Random random;

    public App(Scanner scanner) {
        this.scanner = scanner;
        this.controller = new MenuController();
        this.formatter = new DataFormatter();
        this.random = new Random();
        this.tree = load();
        this.main = new Menu.Builder()
                .setScanner(scanner)
                .addTitle("\nWhat do you want to do:\n")
                .addItem(1, "Play the guessing game", this::play)
                .addItem(2, "List of all animals", this::list)
                .addItem(3, "Search for an animal",this::search)
                .addItem(4, "Calculate statistics", this::statistics)
                .addItem(5, "Print the Knowledge Tree", this::tree)
                .addItem(0, "Exit", this::exit)
                .build();
    }

    private void play() {
        new Game(tree, scanner, formatter, random).start();
    }

    private void list() {
        System.out.println("Here are the animals I know:");
        System.out.println(tree.list());
    }

    private void search() {
        System.out.println("Enter the animal:");
        String animal = formatter.createAnimal(Main.input(scanner));
        System.out.println("Facts about " + animal + ":");
        System.out.println(tree.search(formatter, animal));
    }

    private void statistics() {
        int total = tree.numberOfNodes(formatter);
        int animals = tree.numberOfAnimals();
        int statements = total - animals;
        int height = tree.depth(tree.root);
        int minDepth = (tree.minDepth(tree.root));
        double avg = (tree.averageDepth(tree.root, 0)) * 1.0 / animals;
        avg--;
        System.out.println("The Knowledge Tree stats\n");
        System.out.println("- root node                    " + tree.root.data);
        System.out.println("- total number of nodes        " + total);
        System.out.println("- total number of animals      " + animals);
        System.out.println("- total number of statements   " + statements);
        System.out.println("- height of the tree           " + height);
        System.out.println("- minimum depth                " + minDepth);
        System.out.println("- average depth                " + avg);
    }

    private void tree() {
        System.out.println(tree.tree(formatter));
    }

    private void exit() {
        try {
            Main.saver(tree);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n" + formatter.randomQuote(random, GOODBYE));
        controller.exitAll();
    }

    private BinaryTree load() {
        BinaryTree tree;
        try {
            tree =  Main.loader();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("I want to learn about animals.\n" +
                    "Which animal do you like most?");
            tree = new BinaryTree();
            String a1 = formatter.createAnimal(Main.input(scanner));
            tree.root = new Node(a1);
        }
        System.out.println("Welcome to the animal expert system!");
        return tree;
    }

    public void run() {
        System.out.println(formatter.generateHello() + "\n");
        controller.run(main);
    }

}
