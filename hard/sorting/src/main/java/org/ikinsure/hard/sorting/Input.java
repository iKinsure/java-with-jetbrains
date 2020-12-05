package org.ikinsure.hard.sorting;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Input {

    private static final Scanner SCANNER = new Scanner(System.in);;

    static List<String> readConsole() {
        ArrayList<String> list = new ArrayList<>();
        while (SCANNER.hasNextLine()) {
            list.add(SCANNER.nextLine());
        }
        return list;
    }

    static void write(String fileName, String data) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(data);
        }
    }

    static List<String> readFile(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
    }
}
