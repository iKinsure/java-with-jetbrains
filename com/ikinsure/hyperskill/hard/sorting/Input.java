package com.ikinsure.hyperskill.hard.sorting;

import java.util.*;

public class Input {

    private static final Scanner SCANNER = new Scanner(System.in);;
    private static Input instance;

    private Input() { }

    public static Input getInstance() {
        if (instance == null) {
            instance = new Input();
        }
        return instance;
    }

    protected Map<String, String> createMapFromArgs(String[] args) {
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

    protected List<Long> dataTypeLong() {
        ArrayList<Long> list = new ArrayList<>();
        while (SCANNER.hasNext()) {
            String in = SCANNER.next();
            if (in.matches("-?\\d+")) {
                list.add(Long.parseLong(in));
            } else {
                System.out.println("\"" + in + "\" is not a long. It will be skipped.");
            }
        }
        return list;
    }

    protected List<String> dataTypeWord() {
        ArrayList<String> list = new ArrayList<>();
        while (SCANNER.hasNext()) {
            list.add(SCANNER.next());
        }
        return list;
    }

    protected List<String> dataTypeLine() {
        ArrayList<String> list = new ArrayList<>();
        while (SCANNER.hasNextLine()) {
            list.add(SCANNER.nextLine());
        }
        return list;
    }
}
