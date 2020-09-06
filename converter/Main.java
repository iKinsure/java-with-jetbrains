package converter;

import java.util.Scanner;

public class Main {

    final static private Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int inRadix = SCANNER.nextInt();
        int num = SCANNER.nextInt();
        int outRadix = SCANNER.nextInt();
        num = inRadix <= 1 ? String.valueOf(num).length() : Integer.parseInt(String.valueOf(num), inRadix);
        System.out.println(outRadix <= 1 ? "1".repeat(num) : Integer.toString(num, outRadix));
    }
}
