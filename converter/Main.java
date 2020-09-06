package converter;

import java.util.Scanner;

public class Main {

    final static private Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int x = SCANNER.nextInt();
        int radix = SCANNER.nextInt();
        switch (radix) {
            case 2:
                System.out.println("0b" + Long.toString(x, 2));
                break;
            case 8:
                System.out.println("0" + Long.toString(x, 8));
                break;
            case 16:
                System.out.println("0x" + Long.toString(x, 16));
                break;
        }
    }
}
