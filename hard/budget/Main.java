package hard.budget;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static double balance = 0.0;
    private static double sum = 0.0;
    private static ArrayList<Purchase> list = new ArrayList<>();

    public static void main(String[] args) {
        int decision = 1;
        while (decision != 0) {
            System.out.println("Choose your action:\n" +
                    "1) Add income\n" +
                    "2) Add purchase\n" +
                    "3) Show list of purchases\n" +
                    "4) Balance\n" +
                    "0) Exit");
            decision = SCANNER.nextInt();
            System.out.println();
            switch (decision) {
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
                default:
                    decision = 0;
                    System.out.println("Bye!");
            }
            System.out.println();
        }
    }

    private static void income() {
        System.out.println("Enter income: ");
        balance += SCANNER.nextDouble();
        System.out.println("Income was added!");
    }

    private static void purchase() {
        System.out.println("Enter purchase name: ");
        SCANNER.nextLine();
        String name = SCANNER.nextLine();
        System.out.println("Enter its price: ");
        double price = SCANNER.nextDouble();
        sum += price;
        balance -= balance == 0.0 ? 0.0 : price;
        list.add(new Purchase(name, price));
        System.out.println("Purchase was added!");
    }

    private static void list() {
        if (list.isEmpty()) {
            System.out.println("Purchase list is empty");
        } else {
            for (Purchase p : list) {
                System.out.println(p);
            }
            System.out.println("Total sum: $" + sum);
        }
    }

    private static void balance() {
        System.out.println("Balance: $" + balance);
    }
}
