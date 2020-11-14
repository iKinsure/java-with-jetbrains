package com.ikinsure.hyperskill.hard.sorting;
import java.text.DecimalFormat;
import java.util.*;

public class Main {

    private static final DecimalFormat DECIMAL = new DecimalFormat("#");

    public static void main(final String[] args) {
        Map<String, String> commands = Input.createMapFromArgs(args);
        String dataType = commands.getOrDefault("-dataType", "word");
        String sortingType = commands.getOrDefault("-sortingType", "natural");
        process(sortingType, dataType);
    }

    private static void process(String sortingType, String dataType) {
        if (sortingType.equals("natural") && dataType.equals("word")) {
            sortNatural(Input.dataTypeWord(), Comparator.naturalOrder(), " ");
        }
        if (sortingType.equals("natural") && dataType.equals("line")) {
            sortNatural(Input.dataTypeLine(), (o1, o2) -> o2.length() - o1.length(), "\n");
        }
        if (sortingType.equals("natural") && dataType.equals("long")) {
            sortNatural(Input.dataTypeLong(), Comparator.naturalOrder(), " ");
        }
        if (sortingType.equals("byCount") && dataType.equals("word")) {
            sortByCount(Input.dataTypeWord(), Comparator.naturalOrder());
        }
        if (sortingType.equals("byCount") && dataType.equals("line")) {
            sortByCount(Input.dataTypeLine(), Comparator.naturalOrder());
        }
        if (sortingType.equals("byCount") && dataType.equals("long")) {
            sortByCount(Input.dataTypeLong(), Comparator.naturalOrder());
        }
    }

    private static <T> void sortByCount(List<T> list, Comparator<T> comparator) {
        HashMultiSet<T> set = new HashMultiSet<>(list);
        set.sort(comparator);
        System.out.println("Total: " + list.size() + ".");
        set.getMap().forEach((k, v) ->
                System.out.println(k + ": " + v + " time(s), " + DECIMAL.format(100.0 * v / list.size()) + "%"));
    }

    private static <T> void sortNatural(List<T> list, Comparator<T> comparator, String separator) {
        System.out.println("Total: " + list.size() + ".");
        System.out.print("Sorted data:" + separator);
        list.stream().sorted(comparator).forEach(i -> System.out.print(i + separator));
        System.out.println();
    }
}
