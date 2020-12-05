package org.ikinsure.easy.bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int MAX_RANGE = 36;

    public static void main(String[] args) {

        System.out.println("Input the length of the secret code: ");
        int length;
        try {
            length = SCANNER.nextInt();
        } catch (Exception e) {
            System.out.println("Error: invalid input.");
            return;
        }
        System.out.println("Input the number of possible symbols in the code: ");
        int range;
        try {
            range = SCANNER.nextInt();
        } catch (Exception e) {
            System.out.println("Error: invalid input.");
            return;
        }
        if (range > MAX_RANGE) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }
        if (length > range || length == 0) {
            System.out.println("Error: it's not possible to generate a code with a length of " + length + " with " + range + " unique symbols.");
            return;
        }


        String number = getRandom(length, range);
        System.out.println(getPrepareString(length, range));
        System.out.println("Okay, let's start a game!");

        int round = 1;
        while (true) {
            System.out.println("Turn " + (round++) + ":");
            String input;
            try {
                input = SCANNER.next();
            } catch (Exception e) {
                System.out.println("Error: invalid input.");
                break;
            }
            if (input.length() != length) {
                System.out.println("Error: invalid size");
                break;
            }
            if (!isCorrectFormat(input, range)) {
                System.out.println("Error: invalid format");
                break;
            }

            final int bulls = getBulls(input, number);
            final int cows = getCows(input, number);
            System.out.println(getGradeString(bulls, cows));
            if (bulls == length) {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }
        }
    }

    private static boolean isCorrectFormat(String input, int range) {
        for (int i = 0; i < input.length(); i++) {
            boolean isGood = false;
            for (int j = 0; j < range; j++) {
                char val = (char) (j < 10 ? j + 48 : j + 87);
                if (input.charAt(i) == val) {
                    isGood = true;
                    break;
                }
            }
            if (!isGood) {
                return false;
            }
        }
        return true;
    }

    private static String getPrepareString(int length, int range ) {
        StringBuilder builder = new StringBuilder("The secret is prepared: ");
        builder.append("*".repeat(length)).append(" ");
        if (range <= 10) {
            builder.append("(0-").append(range - 1).append(").");
        } else {
            builder.append("(0-9, a-").append((char)(range + 86)).append(").");
        }
        return builder.toString();
    }

    private static String getGradeString(int bulls, int cows) {
        StringBuilder builder = new StringBuilder();
        builder.append("Grade: ");
        if (bulls >= 1 && cows >= 1) {
            builder.append(bulls).append(bulls == 1 ? " bull" : " bulls");
            builder.append(" and ");
            builder.append(cows).append(cows == 1 ? " cow." : " cows.");
        } else if (bulls >= 1) {
            builder.append(bulls).append(bulls == 1 ? " bull." : " bulls.");
        } else if (cows >= 1) {
            builder.append(cows).append(cows == 1 ? " cow." : " cows.");
        } else {
            builder.append("None.");
        }
        return builder.toString();
    }

    private static int getBulls(String input, String number) {
        int counter = 0;
        if (input.length() == number.length()) {
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == number.charAt(i)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static int getCows(String input, String number) {
        int counter = 0;
        if (input.length() == number.length()) {
            for (int i = 0; i < input.length(); i++) {
                int index = number.indexOf(input.charAt(i));
                if (index != -1 && index != i) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static String getRandom(int length, int range) {
        Random rand = new Random();
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int r = rand.nextInt(range);
            r += r < 10 ? 48 : 87; // 48 - 58, 97 - 123
            if (number.indexOf(String.valueOf((char) r)) == -1) {
                number.append((char) r);
            } else {
                length++;
            }
        }
        return number.toString();
    }
}
