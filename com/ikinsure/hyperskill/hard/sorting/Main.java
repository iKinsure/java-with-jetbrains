package com.ikinsure.hyperskill.hard.sorting;
import java.text.DecimalFormat;
import java.util.*;

public class Main {

    private static final DecimalFormat DECIMAL = new DecimalFormat("#");

    public static void main(final String[] args) {
        Map<String, String> commands = Input.getInstance().createMapFromArgs(args);

        String dataType = "word";
        String sortingType = "natural";
        for (var entry : commands.entrySet()) {
            if (entry.getKey().equals("-dataType")) {
                if (entry.getValue() == null) {
                    System.out.println("No data type defined!");
                    return;
                } else {
                    dataType = entry.getValue();
                }
            } else if (entry.getKey().equals("-sortingType")) {
                if (entry.getValue() == null) {
                    System.out.println("No sorting type defined!");
                    return;
                } else {
                    sortingType = entry.getValue();
                }
            } else {
                System.out.println("\"" + entry.getKey() + "\" is not a valid parameter. It will be skipped.");
            }
        }

        process(sortingType, dataType);
    }

    private static void process(String sortingType, String dataType) {
        if (sortingType.equals("natural") && dataType.equals("word")) {
            sortNatural(Input.getInstance().dataTypeWord(), Comparator.naturalOrder(), " ");
        } else if (sortingType.equals("natural") && dataType.equals("line")) {
            sortNatural(Input.getInstance().dataTypeLine(), (o1, o2) -> o2.length() - o1.length(), "\n");
        } else if (sortingType.equals("natural") && dataType.equals("long")) {
            sortNatural(Input.getInstance().dataTypeLong(), Comparator.naturalOrder(), " ");
        } else if (sortingType.equals("byCount") && dataType.equals("word")) {
            sortByCount(Input.getInstance().dataTypeWord(), Comparator.naturalOrder());
        } else if (sortingType.equals("byCount") && dataType.equals("line")) {
            sortByCount(Input.getInstance().dataTypeLine(), Comparator.naturalOrder());
        } else if (sortingType.equals("byCount") && dataType.equals("long")) {
            sortByCount(Input.getInstance().dataTypeLong(), Comparator.naturalOrder());
        } else {
            System.out.println("There is not sorting \"" + sortingType + "\" or data \"" + dataType + "\"");
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
