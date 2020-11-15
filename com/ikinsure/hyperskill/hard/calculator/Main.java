package com.ikinsure.hyperskill.hard.calculator;

import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            String[] commands = SCANNER.nextLine().split("\\s+");
            if (commands.length >= 2) {
                long sum = 0;
                for (String command : commands) {
                    sum += Long.parseLong(command);
                }
                System.out.println(sum);
            } else if (commands.length >= 1) {
                if ("/exit".equals(commands[0])) {
                    break;
                } else if ("/help".equals(commands[0])) {
                    System.out.println("The program calculates the sum of numbers");
                } else {
                    if (!commands[0].isBlank()) {
                        System.out.println(commands[0]);
                    }
                }
            }
        }
        System.out.println("Bye!");
    }
}
