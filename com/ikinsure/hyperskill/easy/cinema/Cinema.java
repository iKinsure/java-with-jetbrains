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
        System.out.println("\nCinema:");
        System.out.println(scene);

        System.out.println("Enter a row number:");
        int y = SCANNER.nextInt();
        System.out.println("Enter a seat number in that row:");
        int x = SCANNER.nextInt();

        int price;
        if (scene.getSeats() <= 60) {
            price = 10;
        } else {
            price = y > rows / 2 ? 8 : 10;
        }
        System.out.println("\nTicket price: $" + price);

        scene.getPlaces()[y - 1][x - 1] = Scene.Code.BUSY.id;
        System.out.println("\nCinema:");
        System.out.println(scene);
    }
}
