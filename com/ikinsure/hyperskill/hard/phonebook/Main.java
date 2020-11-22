package com.ikinsure.hyperskill.hard.phonebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.List;

public class Main {

    private static final String BASE_FILE = "C:\\Users\\Wojciech\\IdeaProjects\\zabawa\\directory.txt";
    private static final String FIND_FILE = "C:\\Users\\Wojciech\\IdeaProjects\\zabawa\\find.txt";
    private static final long MILLIS_TO_NANO = 1_000_000L;

    public static void main(String[] args) throws IOException {
        System.out.println("Start searching...");
        long start = System.currentTimeMillis();

        List<String> data = load(BASE_FILE);
        List<String> persons = load(FIND_FILE);

        int counter = 0;
        for (String datumEntry : data) {
            int index = datumEntry.indexOf(" ");
            String datum = datumEntry.substring(index + 1);
            for (String person : persons) {
                if (datum.equals(person)) {
                    counter++;
                }
            }
        }

//        int counter = 0;
//        for (String person : persons) {
//            for (String datumEntry : data) {
//                int index = datumEntry.indexOf(" ");
//                String datum = datumEntry.substring(index + 1);
//                if (datum.equals(person)) {
//                    counter++;
//                }
//            }
//        }

        long end = System.currentTimeMillis();
        LocalTime time = LocalTime.ofNanoOfDay((end - start) * MILLIS_TO_NANO);
        System.out.println("Found " + counter + " / " + persons.size() + " entries. Time taken: "
                + time.getMinute() + " min. " + time.getSecond() + " sec. " + time.getNano() / MILLIS_TO_NANO + " ms.");
    }

    private static List<String> load(String file) throws IOException {
        return Files.readAllLines(Path.of(file));
    }
}
