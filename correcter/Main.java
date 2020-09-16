package correcter;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String origText = new Scanner(System.in).nextLine();
        String encodeText = encode(origText);
        String errorText = addError(encodeText);
        String finalText = removeError(errorText);
        System.out.printf("%s\n%s\n%s\n%s\n", origText, encodeText, errorText,finalText);
    }

    private static String removeError(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 2; i < text.length(); i += 3) {
            char a = text.charAt(i - 2);
            char b = text.charAt(i - 1);
            char c = text.charAt(i);
            if (a == b) {
                result.append(a);
            }
            else if (a == c) {
                result.append(a);
            }
            else if (b == c) {
                result.append(b);
            }
        }
        return result.toString();
    }

    private static String encode(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            result.append(text.charAt(i));
            result.append(text.charAt(i));
            result.append(text.charAt(i));
        }
        return result.toString();
    }

    private static String addError(String text) {
        Random r = new Random();
        StringBuilder result = new StringBuilder(text);
        for (int i = 2; i < result.length(); i += 3) {
            result.setCharAt(i - r.nextInt(3), (char) (r.nextInt(96) + 32));
        }
        return result.toString();
    }
}
