package org.ikinsure.bullscows;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Game game;
        int secretLength;
        int symbolsRange;

        System.out.println("Input the length of the secret code: ");
        try {
            secretLength = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Error: invalid input.");
            return;
        }

        System.out.println("Input the number of possible symbols in the code: ");
        try {
            symbolsRange = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Error: invalid input.");
            return;
        }

        try {
            game = Game.create(secretLength, symbolsRange);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println(game.prepare());
        System.out.println("Okay, let's start a game!");

        int round = 1;
        while (true) {

            System.out.println("Turn " + round + ":");
            String input;

            try {
                input = scanner.next();
            } catch (Exception e) {
                System.out.println("Error: invalid input.");
                continue;
            }

            if (input.length() != secretLength) {
                System.out.println("Error: invalid size");
                continue;
            }

            if (!game.validateChars(input)) {
                System.out.println("Error: invalid format");
                continue;
            }

            int bulls = (int) game.bulls(input.toCharArray());
            int cows = (int) game.cows(input.toCharArray());
            System.out.println(Util.grade(bulls, cows));

            if (bulls == secretLength) {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }

            round++;
        }
    }

}
