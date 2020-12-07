package org.ikinsure.cinema;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Cinema {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        final Scene scene = new Scene(rowsNumber(), seatsNumber());
        final Budget budget = new Budget(scene);
        while (true) {
            switch (menu()) {
                case 1:
                    System.out.print(scene);
                    break;
                case 2:
                    buyTicket(scene, budget);
                    break;
                case 3:
                    statistics(budget);
                    break;
                default:
                    return;
            }
        }
    }

    private static void buyTicket(Scene scene, Budget budget) {
        while (true) {
            int y = rowNumber();
            int x = seatNumber();
            if (x < 1 || y < 1 || x > scene.getCols() || y > scene.getRows()) {
                System.out.println("Wrong input!");
            } else if (scene.getPlaces()[y - 1][x - 1] == Scene.Code.BUSY.id) {
                System.out.println("That ticket has already been purchased!");
            } else {
                int price = scene.getSeats() <= 60 ? 10 : y > scene.getRows() / 2 ? 8 : 10;
                System.out.println("Ticket price: $" + price);
                scene.getPlaces()[y - 1][x - 1] = Scene.Code.BUSY.id;
                budget.currentIncome += price;
                budget.soldTickets++;
                break;
            }
        }
    }

    private static void statistics(Budget budget) {
        System.out.println("Number of purchased tickets: " + budget.soldTickets);
        System.out.println("Percentage: " + new DecimalFormat("#0.00").format(budget.getPercent()) + "%");
        System.out.println("Current income: $" + budget.currentIncome);
        System.out.println("Total income: $" + budget.getTotalIncome());
    }

    private static int rowsNumber() {
        System.out.println("Enter the number of rows:");
        return SCANNER.nextInt();
    }

    private static int seatsNumber() {
        System.out.println("Enter the number of seats in each row:");
        return SCANNER.nextInt();
    }

    private static int rowNumber() {
        System.out.println("Enter a row number:");
        return SCANNER.nextInt();
    }

    private static int seatNumber() {
        System.out.println("Enter a seat number in that row:");
        return SCANNER.nextInt();
    }

    private static int menu() {
        System.out.println("\n1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
        return SCANNER.nextInt();
    }
}
