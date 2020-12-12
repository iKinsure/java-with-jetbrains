package org.ikinsure.budget;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static double balance = 0.0;
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String FILENAME = "purchases.txt";
    public static DecimalFormat format = new DecimalFormat("#0.00");
    private static final PurchaseManager manager = new PurchaseManager();

    public static void main(String[] args) throws IOException {
        int decision = 1;
        while (decision != 0) {
            switch (getDecision()) {
                case 1:
                    income();
                    break;
                case 2:
                    purchase();
                    break;
                case 3:
                    list();
                    break;
                case 4:
                    balance();
                    break;
                case 5:
                    save();
                    break;
                case 6:
                    load();
                    break;
                case 7:
                    analyze();
                    break;
                default:
                    decision = 0;
                    System.out.println("\nBye!");
            }
            System.out.println();
        }
    }

    private static void analyze() {
        System.out.println("\nHow do you want to sort?\n" +
                "1) Sort all purchases\n" +
                "2) Sort by type\n" +
                "3) Sort certain type\n" +
                "4) Back");
        int input = SCANNER.nextInt();
        System.out.println();
        switch (input) {
            case 1:
                if (manager.isEmpty()) {
                    System.out.println("Purchase list is empty!");
                } else {
                    System.out.println("All:");
                    System.out.println(manager.getSortedList(null));
                    System.out.println("Total: $" + format.format(manager.getCurrentSum()));
                }
                break;
            case 2:
                System.out.println("Types:");
                double totalSum = 0.0;
                ArrayList<Purchase> purchases = new ArrayList<>(manager.getPurchases());
                Collections.sort(purchases);
                for (Categories category : Categories.values()) {
                    double sum = 0.0;
                    for (Purchase p : purchases) {
                        if (category == p.getCategory()) {
                            sum += p.getPrice();
                            totalSum += p.getPrice();
                        }
                    }
                    System.out.println(category.description + " - $" + format.format(sum));
                }
                System.out.println("Total sum: $" + format.format(totalSum));
                break;
            case 3:
                System.out.print("Choose the type of purchase\n" +
                        Categories.showAll());
                Categories category = Categories.findById(SCANNER.nextInt());
                if (manager.isEmpty() || category == null) {
                    System.out.println("\nPurchase list is empty!");
                } else {
                    System.out.println("\n" + category.description + ":");
                    System.out.print(manager.getSortedList(category));
                    System.out.println("Total sum: $" + format.format(manager.getCurrentSum()));
                }
                break;
            case 4:
                return;
        }
        analyze();
    }

    private static void save() throws IOException {
        String root = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        try (FileWriter writer = new FileWriter(root + FILENAME)) {
            writer.write(String.valueOf(balance));
            writer.write("\n");
            for (Purchase p : manager.getPurchases()) {
                writer.write(p.getCategory().name());
                writer.write('#');
                writer.write(p.getName());
                writer.write('#');
                writer.write(String.valueOf(p.getPrice()));
                writer.write("\n");
            }
        }
        System.out.println("\nPurchases were saved!");
    }

    private static void load() throws IOException {
        String root = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        try (BufferedReader reader = new BufferedReader(new FileReader(root + FILENAME))) {
            manager.clear();
            balance = Double.parseDouble(reader.readLine());
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] lines = line.split("#");
                manager.add(new Purchase(lines[1], Double.parseDouble(lines[2]), Categories.valueOf(lines[0])));
            }
        }
        System.out.println("\nPurchases were loaded!");
    }

    private static int getDecision() {
        System.out.println("Choose your action:\n" +
                "1) Add income\n" +
                "2) Add purchase\n" +
                "3) Show list of purchases\n" +
                "4) Balance\n" +
                "5) Save\n" +
                "6) Load\n" +
                "7) Analyze (Sort)\n" +
                "0) Exit");
        return SCANNER.nextInt();
    }

    private static void income() {
        System.out.println("\nEnter income: ");
        balance += SCANNER.nextDouble();
        System.out.println("Income was added!");
    }

    private static void balance() {
        System.out.println("\nBalance: $" + format.format(balance));
    }

    private static void purchase() {
        System.out.println("\nChoose the type of purchase\n" +
                Categories.showAll() +
                "5) Back");
        int input = SCANNER.nextInt();
        if (input == 5) {
            return;
        }
        Categories category = Categories.findById(input);
        addPurchase(category);
    }

    private static void addPurchase(Categories category) {
        System.out.println("\nEnter purchase name: ");
        SCANNER.nextLine();
        String name = SCANNER.nextLine();
        System.out.println("Enter its price: ");
        double price = SCANNER.nextDouble();
        System.out.println("Purchase was added!");
        balance -= balance == 0.0 ? 0.0 : price;
        manager.add(new Purchase(name, price, category));
        purchase();
    }

    private static void list() {
        if (manager.isEmpty()) {
            System.out.println("\nPurchase list is empty!");
            return;
        }
        System.out.println("\nChoose the type of purchase\n" +
                Categories.showAll() +
                "5) All\n" +
                "6) Back");
        int input = SCANNER.nextInt();
        if (input == 6) {
            return;
        }
        Categories category = Categories.findById(input);
        System.out.println(category == null ? "\nAll:" : "\n" + category.description + ":");
        System.out.print(manager.getList(category));
        System.out.println("Total sum: $" + format.format(manager.getCurrentSum()));
        list();
    }
}
