package com.ikinsure.hyperskill.hard.phonebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final String BASE_FILE = "C:\\Users\\Wojciech\\IdeaProjects\\zabawa\\directory.txt";
    private static final String FIND_FILE = "C:\\Users\\Wojciech\\IdeaProjects\\zabawa\\find.txt";
    private static final long MILLIS_TO_NANO = 1_000_000L;

    public static void main(String[] args) throws IOException {

        List<String> data = load(BASE_FILE);
        List<String> persons = load(FIND_FILE);
        data = data.stream().map(s -> s.substring(s.indexOf(" ") + 1)).collect(Collectors.toList());


        System.out.println("Start searching (linear search)...");
        long start = System.currentTimeMillis();
        int counter = 0;
        for (String datum : data) {
            for (String person : persons) {
                if (datum.equals(person)) {
                    counter++;
                }
            }
        }
        long end = System.currentTimeMillis();
        LocalTime time = LocalTime.ofNanoOfDay((end - start) * MILLIS_TO_NANO);
        printTime(time, counter, persons.size());


        System.out.println("\nStart searching (bubble sort + jump search)...");
        start = System.currentTimeMillis();
        counter = 0;
        Collections.sort(data);
        jumpSearch(data, persons);
        end = System.currentTimeMillis();
        time = LocalTime.ofNanoOfDay((end - start) * MILLIS_TO_NANO);
        printTime(time, counter, persons.size());

    }

    private static <T> int jumpSearch(List<T> source, List<T> find) {
        int counter = 0;
        for (T finder : find) {
            if (jumpSearchAlgorithm(source, find) != -1) {
                counter++;
            }
        }
        return counter;
    }

    private static <T extends Comparable<T>> int jumpSearchAlgorithm(List<T> array, T element) {
        int blockSize = (int) Math.floor(Math.sqrt(array.size()));
        int currentLastIndex = blockSize-1;
        while (currentLastIndex < array.size() && element.compareTo(array.get(currentLastIndex)) > 0) {
            currentLastIndex += blockSize;
        }
        for (int currentSearchIndex = currentLastIndex - blockSize + 1;
             currentSearchIndex <= currentLastIndex && currentSearchIndex < array.size(); currentSearchIndex++) {
            if (element.equals(array.get(currentSearchIndex))) {
                return currentSearchIndex;
            }
        }
        return -1;
    }

    private static List<String> load(String file) throws IOException {
        return Files.readAllLines(Path.of(file));
    }

    private static void printTime(LocalTime time, int found, int max) {
        System.out.println("Found " + found + " / " + max + " entries. Time taken: "
                + time.getMinute() + " min. " + time.getSecond() + " sec. " + time.getNano() / MILLIS_TO_NANO + " ms.");
    }
}
