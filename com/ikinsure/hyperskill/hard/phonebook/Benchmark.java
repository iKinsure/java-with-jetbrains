package com.ikinsure.hyperskill.hard.phonebook;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Benchmark <T extends Comparable<T>> {

    private Sortable<T> sortMethod;
    private Searchable<T> mainSearchMethod;
    private Searchable<T> failSearchMethod;

    void setMethods(Sortable<T> sortMethod, Searchable<T> mainSearchMethod, Searchable<T> failSearchMethod) {
        this.sortMethod = sortMethod;
        this.mainSearchMethod = mainSearchMethod;
        this.failSearchMethod = failSearchMethod;
    }

    Duration start(List<T> data, List<T> queries, Duration maxSortingDuration) {

        boolean isSortMethod = sortMethod != null;
        boolean isFailSearchMethod = failSearchMethod != null;

        System.out.println("\nStart searching (" + (isSortMethod ? sortMethod + " + " : "") + mainSearchMethod + ")...");

        boolean isSuccess = true;
        Duration sortDuration = Duration.ZERO;
        if (isSortMethod) {
            long start = System.currentTimeMillis();
            isSuccess = sortMethod.sort(data, maxSortingDuration);
            long end = System.currentTimeMillis();
            sortDuration = Duration.of(end - start, ChronoUnit.MILLIS);
        }


        long start = System.currentTimeMillis();
        final int counter;
        final Duration searchDuration;
        if (isSuccess) {
            counter = mainSearchMethod.search(data, queries);
        } else {
            counter = failSearchMethod.search(data, queries);
        }
        long end = System.currentTimeMillis();

        searchDuration = Duration.of(end - start, ChronoUnit.MILLIS);
        Duration totalDuration = searchDuration.plus(sortDuration);
        System.out.println("Found " + counter + " / " + queries.size() + " entries. Time taken: " + formatTime(totalDuration));
        if (isSortMethod) {
            if (isSuccess) {
                System.out.println("Sorting time: " + formatTime(sortDuration));
            } else {
                System.out.println("Sorting time: " + formatTime(sortDuration) + " - STOPPED, moved to linear search");
            }
            System.out.println("Searching time: " + formatTime(searchDuration));
        }
        return totalDuration;
    }

    static String formatTime(Duration duration) {
        return duration.toMinutesPart() + " min. " + duration.toSecondsPart() + " sec. " + duration.toMillisPart() + " ms.";
    }
}
