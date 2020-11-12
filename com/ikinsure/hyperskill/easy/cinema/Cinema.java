package com.ikinsure.hyperskill.easy.cinema;

import java.util.Scanner;

public class Cinema {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Enter the number of rows:");
        int rows = SCANNER.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int cols = SCANNER.nextInt();
        Scene scene = new Scene(rows, cols);

        boolean isRunning = true;
        while (isRunning) {
            switch (menu()) {
                case 1:
                    System.out.println("\nCinema:");
                    System.out.print(scene);
                    break;
                case 2:
                    System.out.println("\nEnter a row number:");
                    int y = SCANNER.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    int x = SCANNER.nextInt();
                    int price;
                    if (scene.getSeats() <= 60) {
                        price = 10;
                    } else {
                        price = y > rows / 2 ? 8 : 10;
                    }
                    System.out.println("Ticket price: $" + price);
                    scene.getPlaces()[y - 1][x - 1] = Scene.Code.BUSY.id;
                    break;
                default:
                    isRunning = false;
            }
        }
    }

    private static int menu() {
        System.out.println("\n1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "0. Exit");
        return SCANNER.nextInt();
    }
}
