package com.ikinsure.hyperskill.hard.phonebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final String BASE_FILE = "C:\\Users\\Wojciech\\IdeaProjects\\zabawa\\directory.txt";
    private static final String FIND_FILE = "C:\\Users\\Wojciech\\IdeaProjects\\zabawa\\find.txt";

    public static void main(String[] args) throws IOException {

        // loading data
        List<String> data = load(BASE_FILE);
        List<String> queries = load(FIND_FILE);
        data = data.stream().map(s -> s.substring(s.indexOf(" ") + 1)).collect(Collectors.toList());


        // linear search
        System.out.println("Start searching (linear search)...");
        long start = System.currentTimeMillis();
        int counter = linearSearch(data, queries);
        long end = System.currentTimeMillis();
        Duration duration = Duration.of(end - start, ChronoUnit.MILLIS);
        System.out.println("Found " + counter + " / " + queries.size() + " entries. Time taken: " + formatTime(duration));



        // bubble sort + jump search
        System.out.println("\nStart searching (bubble sort + jump search)...");
        start = System.currentTimeMillis();
        //Collections.sort(data);
        boolean val = bubbleSort(data, duration);
        end = System.currentTimeMillis();
        Duration sortDuration = Duration.of(end - start, ChronoUnit.MILLIS);

        start = System.currentTimeMillis();
        if (val) {
            counter = jumpSearch(data, queries);
            end = System.currentTimeMillis();
            Duration searchDuration = Duration.of(end - start, ChronoUnit.MILLIS);
            System.out.println("Found " + counter + " / " + queries.size() + " entries. Time taken: " + formatTime(searchDuration.plus(sortDuration)));
            System.out.println("Sorting time: " + formatTime(sortDuration));
            System.out.println("Searching time: " + formatTime(searchDuration));
        } else {
            counter = linearSearch(data, queries);
            end = System.currentTimeMillis();
            Duration searchDuration = Duration.of(end - start, ChronoUnit.MILLIS);
            System.out.println("Found " + counter + " / " + queries.size() + " entries. Time taken: " + formatTime(searchDuration.plus(sortDuration)));
            System.out.println("Sorting time: " + formatTime(sortDuration) + " - STOPPED, moved to linear search");
            System.out.println("Searching time: " + formatTime(searchDuration));
        }


    }


    static <T extends Comparable<T>> boolean bubbleSort(List<T> data, Duration duration) {
        Duration limit = duration.multipliedBy(10L);
        long start = System.currentTimeMillis();
        int n = data.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (data.get(j).compareTo(data.get(j + 1)) > 0) {
                    T temp = data.get(j);
                    data.set(j, data.get(j + 1));
                    data.set(j + 1, temp);
                }
            }
            if (limit.toMillis() < (System.currentTimeMillis() - start)) {
                return false;
            }
        }
        return true;
    }

    private static <T extends Comparable<T>> int linearSearch(List<T> data, List<T> queries) {
        int counter = 0;
        for (T datum : data) {
            for (T query : queries) {
                if (datum.equals(query)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static <T extends Comparable<T>> int jumpSearch(List<T> data, List<T> queries) {
        int counter = 0;
        for (T query : queries) {
            if (jumpSearchAlgorithm(data, query) != -1) {
                counter++;
            }
        }
        return counter;
    }

    private static <T extends Comparable<T>> int jumpSearchAlgorithm(List<T> data, T query) {
        int blockSize = (int) Math.floor(Math.sqrt(data.size()));
        int currentLastIndex = blockSize-1;
        while (currentLastIndex < data.size() && query.compareTo(data.get(currentLastIndex)) > 0) {
            currentLastIndex += blockSize;
        }
        for (int currentSearchIndex = currentLastIndex - blockSize + 1;
             currentSearchIndex <= currentLastIndex && currentSearchIndex < data.size(); currentSearchIndex++) {
            if (query.equals(data.get(currentSearchIndex))) {
                return currentSearchIndex;
            }
        }
        return -1;
    }

    private static List<String> load(String file) throws IOException {
        return Files.readAllLines(Path.of(file));
    }

    private static String formatTime(Duration d) {
        return d.toMinutesPart() + " min. " + d.toSecondsPart() + " sec. " + d.toMillisPart() + " ms.";
    }
}
