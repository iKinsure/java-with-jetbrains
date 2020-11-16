package com.ikinsure.hyperskill.hard.calculator;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            String order = SCANNER.nextLine();
            if (order.matches("([-+ ]*\\d* *[-+]+ *\\d+|\\d)+")) {
                System.out.println(eval(order.replaceAll("\\s+", "")
                        .replaceAll("(--)+", "+")
                        .replaceAll("\\++", "+")
                        .replaceAll("(\\+-)+", "-")
                        .replaceAll("(-\\+)+", "-")));
            } else if (order.equals("/exit")) {
                System.out.println("Bye!");
                break;
            } else if (order.equals("/help")) {
                System.out.println("The program calculates the sum of numbers");
            } else if (order.isBlank()) {

            } else {
                System.out.println(order.startsWith("/") ? "Unknown command" : "Invalid expression");
            }
        }
    }

    private static long eval(String command) {
        long counter = 0;
        Pattern pattern = Pattern.compile("[+-]?\\d+");
        Matcher matcher = pattern.matcher(command);
        while (matcher.find()) {
            counter += Long.parseLong(matcher.group());
        }
        return counter;
    }
}
