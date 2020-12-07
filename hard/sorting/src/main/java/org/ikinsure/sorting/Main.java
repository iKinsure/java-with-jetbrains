package org.ikinsure.sorting;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class Main {

    private static final DecimalFormat DECIMAL = new DecimalFormat("#");

    // commands
    private static final String DATA = "dataType";
    private static final String WORD = "word";
    private static final String LINE = "line";
    private static final String LONG = "long";
    private static final String SORT = "sortingType";
    private static final String NATURAL = "natural";
    private static final String COUNT = "byCount";
    private static final String INPUT = "inputFile";
    private static final String OUTPUT = "outputFile";

    public static void main(final String[] args) {

        // get commands and prepare
        Map<String, String> commands = createMapFromArgs(args);
        String dataType = WORD;
        String sortingType = NATURAL;
        String inputFile = null;
        String outputFile = null;

        // check all commands
        for (var entry : commands.entrySet()) {
            switch (entry.getKey()) {
                case DATA:
                    if (entry.getValue() == null) {
                        System.out.println("No data type defined!");
                        return;
                    } else {
                        dataType = entry.getValue();
                    }
                    break;
                case SORT:
                    if (entry.getValue() == null) {
                        System.out.println("No sorting type defined!");
                        return;
                    } else {
                        sortingType = entry.getValue();
                    }
                    break;
                case INPUT:
                    if (entry.getValue() == null) {
                        System.out.println("No input file defined!");
                        return;
                    } else {
                        inputFile = entry.getValue();
                    }
                    break;
                case OUTPUT:
                    if (entry.getValue() == null) {
                        System.out.println("No output file defined!");
                        return;
                    } else {
                        outputFile = entry.getValue();
                    }
                    break;
                default:
                    System.out.println("\"" + entry.getKey() + "\" is not a valid parameter. It will be skipped.");
                    break;
            }
        }

        // process types
        process(sortingType, dataType, inputFile, outputFile);
    }

    private static void process(String sortingType, String dataType, String inputFile, String outputFile) {
        String data = null;
        List<String> list = null;
        try {
            list = inputFile == null
                    ? Input.readConsole()
                    : Input.readFile(inputFile);
        } catch (IOException e) {
            System.out.println("Error when reading " + inputFile);
            return;
        }
        if (sortingType.equals(NATURAL) && dataType.equals(WORD)) {
            data = sortNatural(lineToWord(list), Comparator.naturalOrder(), " ");
        } else if (sortingType.equals(NATURAL) && dataType.equals(LINE)) {
            data = sortNatural(list, (o1, o2) -> o2.length() - o1.length(), "\n");
        } else if (sortingType.equals(NATURAL) && dataType.equals(LONG)) {
            data = sortNatural(lineToLong(list), Comparator.naturalOrder(), " ");
        } else if (sortingType.equals(COUNT) && dataType.equals(WORD)) {
            data = sortByCount(lineToWord(list), Comparator.naturalOrder());
        } else if (sortingType.equals(COUNT) && dataType.equals(LINE)) {
            data = sortByCount(list, Comparator.naturalOrder());
        } else if (sortingType.equals(COUNT) && dataType.equals(LONG)) {
            data = sortByCount(lineToLong(list), Comparator.naturalOrder());
        } else {
            System.out.println("There is not sorting \"" + sortingType + "\" or data \"" + dataType + "\"");
            return;
        }
        if (outputFile == null) {
            System.out.println(data);
        } else {
            try {
                Input.write(outputFile, data);
            } catch (IOException e) {
                System.out.println("Error when saving to " + outputFile);
            }
        }
    }

    private static <T> String sortByCount(List<T> list, Comparator<T> comparator) {
        HashMultiSet<T> set = new HashMultiSet<>(list);
        set.sort(comparator);
        StringBuilder builder = new StringBuilder("Total: " + list.size() + ".\n");
        set.getMap().forEach((k, v) -> builder
                .append(k).append(": ")
                .append(v).append(" time(s), ")
                .append(DECIMAL.format(100.0 * v / list.size()))
                .append("%\n"));
        return builder.toString();
    }

    private static <T> String sortNatural(List<T> list, Comparator<T> comparator, String separator) {
        StringBuilder builder = new StringBuilder("Total: " + list.size() + ".\n");
        builder.append("Sorted data:").append(separator);
        list.stream().sorted(comparator).forEach(i -> builder
                .append(i).append(separator));
        builder.append("\n");
        return builder.toString();
    }

    private static List<String> lineToWord(List<String> lines) {
        ArrayList<String> result = new ArrayList<>();
        for (String line : lines) {
            result.addAll(Arrays.asList(line.split("\\s+")));
        }
        return result;
    }

    private static List<Long> lineToLong(List<String> lines) {
        ArrayList<Long> result = new ArrayList<>();
        for (String line : lines) {
            String[] words = line.split("\\s+");
            for (String word : words) {
                if (word.matches("-?\\d+")) {
                    result.add(Long.parseLong(word));
                } else {
                    System.out.println("\"" + word + "\" is not a long. It will be skipped.");
                }
            }
        }
        return result;
    }

    private static Map<String, String> createMapFromArgs(String[] args) {
        LinkedHashMap<String, String> commands = new LinkedHashMap<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                if (args.length - 1 > i && !args[i + 1].startsWith("-")) {
                    commands.put(args[i].substring(1), args[i + 1]);
                    i++;
                } else {
                    commands.put(args[i].substring(1), null);
                }
            }
        }
        return commands;
    }
}
