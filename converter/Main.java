package converter;

import java.util.Scanner;

public class Main {

    final static private Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        // input
        int inRadix = SCANNER.nextInt();
        String input = SCANNER.next();
        int outRadix = SCANNER.nextInt();

        // first stage
        String[] number = input.split("\\.");
        int integer = inRadix <= 1 ? number[0].length() : Integer.parseInt(number[0], inRadix);
        double fractional = 0.0;
        if (number.length == 2) {
            for (int i = 0; i < number[1].length(); i++) {
                fractional += Character.getNumericValue(number[1].charAt(i)) / Math.pow(inRadix, i + 1);
            }
        }

        // second stage
        StringBuilder builder = new StringBuilder(Integer.toString(integer, outRadix) + ".");
        for (int i = 0; i < 5; i++) {
            fractional *= outRadix;
            int temp = (int) fractional;
            if (temp >= 10) {
                builder.append((char) (temp + 87));
            } else {
                builder.append(temp);
            }
            fractional -= temp;
        }
        System.out.println(outRadix <= 1 ? "1".repeat(integer) : builder.toString());
    }
}
