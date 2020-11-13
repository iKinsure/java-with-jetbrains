package com.ikinsure.hyperskill.hard.sorting;
import java.text.DecimalFormat;
import java.util.*;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final DecimalFormat FORMAT = new DecimalFormat("#");

    public static void main(final String[] args) {

        // create map from args
        LinkedHashMap<String, String> commands = new LinkedHashMap<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                if (args.length - 1 > i && !args[i + 1].startsWith("-")) {
                    commands.put(args[i], args[i + 1]);
                    i++;
                } else {
                    commands.put(args[i], null);
                }
            }
        }

        // process commands
        if (commands.containsKey("-sortIntegers")) {
            sortIntegers();
        } else {
            if ("long".equals(commands.get("-dataType"))) {
                dataTypeLong();
            } else if ("line".equals(commands.get("-dataType"))) {
                dataTypeLine();
            } else {
                dataTypeWord();
            }
        }
    }

    private static void sortIntegers() {
        ArrayList<Long> list = new ArrayList<>();
        while (SCANNER.hasNextLong()) {
            list.add(SCANNER.nextLong());
        }
        printSortIntegers(list);
    }

    private static void dataTypeWord() {
        ArrayList<String> list = new ArrayList<>();
        while (SCANNER.hasNext()) {
            list.add(SCANNER.next());
        }
        printDataTypeWord(list);
    }

    private static void dataTypeLong() {
        ArrayList<Long> list = new ArrayList<>();
        while (SCANNER.hasNextLong()) {
            list.add(SCANNER.nextLong());
        }
        printDataTypeLong(list);
    }

    private static void dataTypeLine() {
        ArrayList<String> list = new ArrayList<>();
        while (SCANNER.hasNextLine()) {
            list.add(SCANNER.nextLine());
        }
        printDataTypeLine(list);
    }

    private static void printSortIntegers(ArrayList<Long> list) {
        Collections.sort(list);
        System.out.println("Total numbers: " + list.size() + ".");
        System.out.print("Sorted data: ");
        list.forEach(i -> System.out.print(i + " "));
        System.out.println();
    }

    private static void printDataTypeLong(ArrayList<Long> list) {
        long max = Collections.max(list);
        int freq = Collections.frequency(list, max);
        double percent = 100.0 * freq / list.size();
        System.out.println("Total numbers: " + list.size() + ".");
        System.out.println("The largest number: " + max + " (" + freq + " time(s), " + FORMAT.format(percent) + "%).");
    }

    private static void printDataTypeLine(ArrayList<String> list) {
        String max = Collections.max(list, Comparator.comparingInt(String::length));
        int freq = Collections.frequency(list, max);
        double percent = 100.0 * freq / list.size();
        System.out.println("Total lines: " + list.size() + ".");
        System.out.println("The longest line: ");
        System.out.println(max);
        System.out.println("(" + freq + " time(s), " + FORMAT.format(percent) + "%).");
    }

    private static void printDataTypeWord(ArrayList<String> list) {
        String max = Collections.max(list, Comparator.comparingInt(String::length));
        int freq = Collections.frequency(list, max);
        double percent = 100.0 * freq / list.size();
        System.out.println("Total words: " + list.size() + ".");
        System.out.println("The largest word: " + max + " (" + freq + " time(s), " + FORMAT.format(percent) + "%).");
    }
}
