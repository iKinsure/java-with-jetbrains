package com.ikinsure.hyperskill.hard.sorting;
import java.text.DecimalFormat;
import java.util.*;

public class Main {

    private static final String TYPE = "-dataType";
    private static final String LONG = "long";
    private static final String LINE = "line";
    private static final String WORD = "word";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final DecimalFormat FORMAT = new DecimalFormat("#");

    public static void main(final String[] args) {
        boolean isArg = args != null && args.length >= 2;
        if (isArg && TYPE.equals(args[0]) && LONG.equals(args[1])) {
            ArrayList<Long> list = new ArrayList<>();
            while (SCANNER.hasNextLong()) {
                list.add(SCANNER.nextLong());
            }
            printLong(list);
        } else if (isArg && TYPE.equals(args[0]) && LINE.equals(args[1])) {
            ArrayList<String> list = new ArrayList<>();
            while (SCANNER.hasNextLine()) {
                list.add(SCANNER.nextLine());
            }
            printLine(list);
        } else {
            ArrayList<String> list = new ArrayList<>();
            while (SCANNER.hasNext()) {
                list.add(SCANNER.next());
            }
            printWord(list);
        }
    }

    private static void printLong(ArrayList<Long> list) {
        long max = Collections.max(list);
        int freq = Collections.frequency(list, max);
        double percent = 100.0 * freq / list.size();
        System.out.println("Total numbers: " + list.size() + ".");
        System.out.println("The largest number: " + max + " (" + freq + " time(s), " + FORMAT.format(percent) + "%).");
    }

    private static void printLine(ArrayList<String> list) {
        String max = Collections.max(list, Comparator.comparingInt(String::length));
        int freq = Collections.frequency(list, max);
        double percent = 100.0 * freq / list.size();
        System.out.println("Total lines: " + list.size() + ".");
        System.out.println("The longest line: ");
        System.out.println(max);
        System.out.println("(" + freq + " time(s), " + FORMAT.format(percent) + "%).");
    }

    private static void printWord(ArrayList<String> list) {
        String max = Collections.max(list, Comparator.comparingInt(String::length));
        int freq = Collections.frequency(list, max);
        double percent = 100.0 * freq / list.size();
        System.out.println("Total words: " + list.size() + ".");
        System.out.println("The largest word: " + max + " (" + freq + " time(s), " + FORMAT.format(percent) + "%).");
    }
}
