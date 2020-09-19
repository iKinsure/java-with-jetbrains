package medium.converter;

import java.util.Scanner;

public class Main {

    final static private Scanner SCANNER = new Scanner(System.in);
    private static boolean error = false;

    public static void main(String[] args) {

        // input
        int inRadix = 0;
        String input = null;
        int outRadix = 0;
        try {
            inRadix = SCANNER.nextInt();
            input = SCANNER.next();
            outRadix = SCANNER.nextInt();
            error = inRadix < 1 || inRadix > 36 || outRadix < 1 || outRadix > 36;
        } catch (Exception e) {
            error = true;
        }
        if (!error) {

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

        } else {
            System.out.println("error");
        }
    }
}
