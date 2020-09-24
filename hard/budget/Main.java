package hard.budget;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static double balance = 0.0;
    private static final Scanner SCANNER = new Scanner(System.in);
    private static ArrayList<Purchase> list = new ArrayList<>();
    private static final String FILENAME = "purchases.txt";

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
                default:
                    decision = 0;
                    System.out.println("Bye!");
            }
            System.out.println();
        }
    }

    private static void save() throws IOException {
        try (FileWriter writer = new FileWriter(FILENAME)) {
            writer.write(String.valueOf(balance));
            writer.write("\n");
            for (Purchase p : list) {
                writer.write(p.getCategory().name());
                writer.write('#');
                writer.write(p.getName());
                writer.write('#');
                writer.write(String.valueOf(p.getPrice()));
                writer.write("\n");
            }
        }
        System.out.println("Purchases were saved!");
    }

    private static void load() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            list.clear();
            balance = Double.parseDouble(reader.readLine());
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] lines = line.split("#");
                list.add(new Purchase(lines[1], Double.parseDouble(lines[2]), Purchase.Categories.valueOf(lines[0])));
            }
        }
        System.out.println("Purchases were loaded!");
    }

    private static int getDecision() {
        System.out.println("Choose your action:\n" +
                "1) Add income\n" +
                "2) Add purchase\n" +
                "3) Show list of purchases\n" +
                "4) Balance\n" +
                "5) Save\n" +
                "6) Load\n" +
                "0) Exit");
        int decision = SCANNER.nextInt();
        System.out.println();
        return decision;
    }

    private static void income() {
        System.out.println("Enter income: ");
        balance += SCANNER.nextDouble();
        System.out.println("Income was added!");
    }

    private static void balance() {
        System.out.println("Balance: $" + balance);
    }

    private static void purchase() {
        Purchase.Categories category = null;
        System.out.println("Choose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) Back");
        switch (SCANNER.nextInt()) {
            case 1:
                category = Purchase.Categories.FOOD;
                break;
            case 2:
                category = Purchase.Categories.CLOTHES;
                break;
            case 3:
                category = Purchase.Categories.ENTERTAINMENT;
                break;
            case 4:
                category = Purchase.Categories.OTHER;
                break;
            case 5:
                return;
        }
        addPurchase(category);
    }

    private static void addPurchase(Purchase.Categories category) {
        System.out.println();
        System.out.println("Enter purchase name: ");
        SCANNER.nextLine();
        String name = SCANNER.nextLine();
        System.out.println("Enter its price: ");
        double price = SCANNER.nextDouble();
        System.out.println("Purchase was added!");
        balance -= balance == 0.0 ? 0.0 : price;
        list.add(new Purchase(name, price, category));
        System.out.println();
        purchase();
    }

    private static void list() {
        if (list.isEmpty()) {
            System.out.println("Purchase list is empty!");
            return;
        }
        Purchase.Categories category = typeWithAll();
        if (category == null) {
            return;
        }
        System.out.println();
        System.out.println(category.description + ":");
        double sum = 0.0;
        if (category == Purchase.Categories.ALL) {
            for (Purchase p : list) {
                System.out.println(p);
                sum += p.getPrice();
            }
            System.out.println("Total sum: $" + sum);
        }
        else {
            int counter = 0;
            double cash = 0.0;
            for (Purchase p : list) {
                if (p.getCategory() == category) {
                    System.out.println(p);
                    cash += p.getPrice();
                    counter++;
                }
            }
            if (counter == 0) {
                System.out.println("Purchase list is empty!");
            } else {
                System.out.println("Total sum: $" + cash);
            }
        }
        System.out.println();
        list();
    }



    private static Purchase.Categories typeWithAll() {
        System.out.println("Choose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) All\n" +
                "6) Back");
        switch (SCANNER.nextInt()) {
            case 1:
                return Purchase.Categories.FOOD;
            case 2:
                return Purchase.Categories.CLOTHES;
            case 3:
                return Purchase.Categories.ENTERTAINMENT;
            case 4:
                return Purchase.Categories.OTHER;
            case 5:
                return Purchase.Categories.ALL;
        }
        return null;
    }
}
