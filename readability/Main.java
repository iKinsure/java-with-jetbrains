package readability;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String[] sentences = Pattern.compile("[?!.]").split(new Scanner(System.in).nextLine());
        int sum = 0;
        for (String sentence : sentences) {
            sum += sentence.split(" ").length;
        }
        System.out.println((sum / sentences.length) > 10 ? "HARD" : "EASY");
    }
}
