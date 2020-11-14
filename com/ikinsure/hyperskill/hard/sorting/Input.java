package com.ikinsure.hyperskill.hard.sorting;

import java.util.*;

public class Input {

    private static final Scanner SCANNER = new Scanner(System.in);

    static Map<String, String> createMapFromArgs(String[] args) {
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
        return commands;
    }

    static List<Long> dataTypeLong() {
        ArrayList<Long> list = new ArrayList<>();
        while (SCANNER.hasNextLong()) {
            list.add(SCANNER.nextLong());
        }
        return list;
    }

    static List<String> dataTypeWord() {
        ArrayList<String> list = new ArrayList<>();
        while (SCANNER.hasNext()) {
            list.add(SCANNER.next());
        }
        return list;
    }

    static List<String> dataTypeLine() {
        ArrayList<String> list = new ArrayList<>();
        while (SCANNER.hasNextLine()) {
            list.add(SCANNER.nextLine());
        }
        return list;
    }
}
