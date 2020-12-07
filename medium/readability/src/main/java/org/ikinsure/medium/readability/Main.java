package org.ikinsure.medium.readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    static int wordsNum = 0;
    static int sentencesNum = 0;
    static int charactersNum = 0;
    static int syllablesNum = 0;
    static int polysyllablesNum = 0;

    public static void main(String[] args) throws IOException {
        String text = readFromFile(args[0]);
        String[] sentences = Pattern.compile("[?!.][\\s]*").split(text);
        for (String sentence : sentences) {
            String[] words = Pattern.compile("\\s").split(sentence);
            for (String word : words) {
                int temp = getSyllables(word);
                if (temp > 2) {
                    polysyllablesNum++;
                }
                syllablesNum += temp;
            }
            wordsNum += words.length;
        }
        sentencesNum = sentences.length;
        charactersNum = text.length() - wordsNum + 1;

        System.out.println("The text is: " + "\n" + text + "\n");
        System.out.println("Words: " + wordsNum);
        System.out.println("Sentences: " + sentencesNum);
        System.out.println("Characters: " + charactersNum);
        System.out.println("Syllables: " + syllablesNum);
        System.out.println("Polysyllables: " + polysyllablesNum);

        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String decision = new Scanner(System.in).next();
        System.out.println();
        boolean all = "all".equals(decision);
        int sum = 0;
        if ("ARI".equals(decision) || all) {
            double score = 4.71 * (1.0 * charactersNum / wordsNum) + 0.5 * (1.0 * wordsNum / sentencesNum) - 21.43;
            System.out.print("Automated Readability Index: " + new DecimalFormat("#0.00").format(score));
            System.out.println(" (about " + getAge(score) +" year olds).");
            sum += getAge(score);
        }
        if ("FK".equals(decision) || all) {
            double score = 0.39 * (1.0 * wordsNum / sentencesNum) + 11.8 * (1.0 * syllablesNum / wordsNum) - 15.59;
            System.out.print("Flesch–Kincaid rename.readability tests: " + new DecimalFormat("#0.00").format(score));
            System.out.println(" (about " + getAge(score) +" year olds).");
            sum += getAge(score);
        }
        if ("SMOG".equals(decision) | all) {
            double score = 1.043 * Math.sqrt(polysyllablesNum * (30.0 / sentencesNum)) + 3.1291;
            System.out.print("Simple Measure of Gobbledygook: " + new DecimalFormat("#0.00").format(score));
            System.out.println(" (about " + getAge(score) +" year olds).");
            sum += getAge(score);
        }
        if ("CL".equals(decision) || all) {
            double score = 0.0588 * (100.0 * charactersNum / wordsNum) - 0.296 * (100.0 * sentencesNum / wordsNum) - 15.8;
            System.out.print("Coleman–Liau index: " + new DecimalFormat("#0.00").format(score));
            System.out.println(" (about " + getAge(score) +" year olds).");
            sum += getAge(score);
        }
        System.out.println("This text should be understood in average by " + new DecimalFormat("#0.00").format(1.0 * sum / 4.0) + " year olds.");
    }

    private static int getSyllables(String word) {
        int counter = 0;
        boolean wasVowel = false;
        for (int i = 0; i < word.length(); i++) {
            char c = Character.toLowerCase(word.charAt(i));
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y') {
                if (!wasVowel) {
                    wasVowel = true;
                    counter++;;
                }
            } else {
                wasVowel = false;
            }
        }
        if (word.charAt(word.length() - 1) == 'e') {
            counter--;
        }
        if (counter == 0) {
            counter = 1;
        }
        return counter;
    }

    private static String readFromFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    private static int getAge(double score) {
        final int[] age = {5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 24, 25};
        int intScore = (int) Math.ceil(score);
        if (intScore >= age.length - 1) {
            intScore = age.length - 1;
        }
        return age[intScore];
    }
}
