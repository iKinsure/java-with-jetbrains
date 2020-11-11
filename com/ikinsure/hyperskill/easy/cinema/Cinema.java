package com.ikinsure.hyperskill.easy.cinema;

import java.util.Scanner;

public class Cinema {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        int rows = SCANNER.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = SCANNER.nextInt();

        int income;
        if (rows * seats <= 60) {
            income = rows * seats * 10;
        } else {
            int half = rows / 2;
            income = half * seats * 10;
            income += (rows - half) * seats * 8;
        }

        System.out.println("Total income:");
        System.out.println("$" + income);
    }
}
