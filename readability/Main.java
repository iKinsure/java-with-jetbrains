package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        String text = readFromFile(args[0]);
        String[] sentences = Pattern.compile("[?!.][\\s]*").split(text);
        int wordCounter = 0;
        for (String sentence : sentences) {
            String[] words = Pattern.compile("\\s").split(sentence);
            wordCounter += words.length;
        }
        int charCounter = text.length() - wordCounter + 1;
        double score = 4.71 * (1.0 * charCounter / wordCounter) + 0.5 * (1.0 * wordCounter / sentences.length) - 21.43;
        System.out.println("The text is: " + "\n" + text + "\n");
        System.out.println("Words: " + wordCounter);
        System.out.println("Sentences: " + sentences.length);
        System.out.println("Characters: " + charCounter);
        System.out.println("The score is: " + new DecimalFormat("#0.00").format(score));
        System.out.println(getScoreString(score));
    }

    private static String readFromFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    private static String getScoreString(double score) {
        String info;
        if (score <= 1.0) {
            info = "5-6";
        } else if (score <= 2.0) {
            info = "6-7";
        } else if (score <= 3.0) {
            info = "7-9";
        } else if (score <= 4.0) {
            info = "9-10";
        } else if (score <= 5.0) {
            info = "10-11";
        } else if (score <= 6.0) {
            info = "11-12";
        } else if (score <= 7.0) {
            info = "12-13";
        } else if (score <= 8.0) {
            info = "13-14";
        } else if (score <= 9.0) {
            info = "14-15";
        } else if (score <= 10.0) {
            info = "15-16";
        } else if (score <= 11.0) {
            info = "16-17";
        } else if (score <= 12.0) {
            info = "17-18";
        } else if (score <= 13.0) {
            info = "18-24";
        } else {
            info = "24+";
        }
        return "This text should be understood by " + info + " year olds.";
    }
}
