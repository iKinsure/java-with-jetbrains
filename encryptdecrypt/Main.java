package encryptdecrypt;

import java.util.Scanner;

public class Main {

    public static final int MIN_CHAR = 'a'; // 97
    public static final int MAX_CHAR = 'z'; // 122
    public static final Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) {
        final int range = MAX_CHAR - MIN_CHAR + 1;
        String text = SCAN.nextLine();
        int key = SCAN.nextInt() % range;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (isCharInRange(c)) {
                c += key;
                c -= isCharInRange(c) ? 0 : range;
            }
            System.out.print(c);
        }
    }

    private static boolean isCharInRange(char c) {
        return (c >= MIN_CHAR && c <= MAX_CHAR);
    }

    private static char codeCharCorrespondingPosition(char c) {
        return isCharInRange(c) ? (char) (MAX_CHAR + MIN_CHAR - c) : c;
    }
}
