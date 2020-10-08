package easy.bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Please, enter the secret code's length:");
        int size = SCANNER.nextInt();
        if (size > 10) {
            System.out.println("Error: can't generate a secret number with a length of " + size + " because there aren't enough unique digits.");
            return;
        }
        String number = getRandom(size);
        System.out.println("Okay, let's start a game!");
        int round = 1;
        while (true) {
            System.out.println("Turn " + (round++) + ":");
            String input = SCANNER.next();
            final int bulls = getBulls(input, number);
            final int cows = getCows(input, number);
            System.out.println(getString(bulls, cows));
            if (bulls == size) {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }
        }
    }

    private static String getString(int bulls, int cows) {
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

    private static String getRandom(int size) {
        Random rand = new Random();
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int r = rand.nextInt(10);
            if (number.indexOf(String.valueOf(r)) == -1) {
                number.append(r);
            } else {
                size++;
            }
        }
        return number.toString();
    }
}
