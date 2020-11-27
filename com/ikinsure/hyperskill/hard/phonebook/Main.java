package com.ikinsure.hyperskill.hard.phonebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final String BASE_FILE = "C:\\Users\\Wojciech\\IdeaProjects\\zabawa\\directory.txt";
    private static final String FIND_FILE = "C:\\Users\\Wojciech\\IdeaProjects\\zabawa\\find.txt";

    public static void main(String[] args) throws IOException {

        // loading data
        List<String> srcData = load(BASE_FILE).stream()
                .map(s -> s.substring(s.indexOf(" ") + 1)).collect(Collectors.toList());
        List<String> queries = load(FIND_FILE);
        List<String> data = new ArrayList<>(srcData);

        // prepare
        Benchmark<String> benchmark = new Benchmark<>();
        Searchable<String> linearSearch = new LinearSearch<>();
        Searchable<String> jumpSearch = new JumpSearch<>();
        Searchable<String> binarySearch = new BinarySearch<>();
        Sortable<String> bubbleSort = new BubbleSort<>();
        Sortable<String> quickSort = new QuickSort<>();

        // linear search
        benchmark.setMethods(null, linearSearch, null);
        Duration maxTime = benchmark.start(data, queries,null);

        // bubble sort + jump search
        Collections.copy(data, srcData);
        benchmark.setMethods(bubbleSort, jumpSearch, linearSearch);
        benchmark.start(data, queries, maxTime);

        // quick sort + binary search
        Collections.copy(data, srcData);
        benchmark.setMethods(quickSort, binarySearch, null);
        benchmark.start(data, queries, null);
    }

    private static List<String> load(String file) throws IOException {
        return Files.readAllLines(Path.of(file));
    }
}
