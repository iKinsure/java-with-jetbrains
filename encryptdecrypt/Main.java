package encryptdecrypt;

import java.util.Scanner;

public class Main {

    public static final Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) {
        switch (SCAN.nextLine()) {
            case "enc":
                System.out.println(crypt(SCAN.nextLine(), SCAN.nextInt(), false));
                break;
            case "dec":
                System.out.println(crypt(SCAN.nextLine(), SCAN.nextInt(), true));
                break;
        }
    }

    private static String crypt(String text, int key, boolean isReading) {
        StringBuilder builder = new StringBuilder(text);
        for (int i = 0; i < builder.length(); i++) {
            builder.setCharAt(i, (char) (builder.charAt(i) + (isReading ? - key : key)));
        }
        return builder.toString();
    }
}
