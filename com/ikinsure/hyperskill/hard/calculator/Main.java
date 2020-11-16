package com.ikinsure.hyperskill.hard.calculator;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            String command = SCANNER.nextLine().replaceAll("\\s+", "");
            if ("/exit".equals(command)) {
                System.out.println("Bye!");
                break;
            } else if ("/help".equals(command)) {
                System.out.println("The program calculates the sum of numbers");
            } else if (command.matches("[-0-9+]{2,}|\\d")) {
                command = command.replaceAll("(--)+", "+");
                command = command.replaceAll("\\++", "+");
                command = command.replaceAll("(\\+-)+", "-");
                command = command.replaceAll("(-\\+)+", "-");
                System.out.println(eval(command));
            } else {
                System.out.println("Invalid input");
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
