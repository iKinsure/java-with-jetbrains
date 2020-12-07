package org.ikinsure.phonebook;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Benchmark <T extends Comparable<T>> {

    private Sortable<T> sortMethod;
    private Searchable<T> mainSearchMethod;
    private Searchable<T> failSearchMethod;
    private long timer;

    void setMethods(Sortable<T> sortMethod, Searchable<T> mainSearchMethod, Searchable<T> failSearchMethod) {
        this.sortMethod = sortMethod;
        this.mainSearchMethod = mainSearchMethod;
        this.failSearchMethod = failSearchMethod;
    }

    Duration start(List<T> data, List<T> queries, Duration maxSortingDuration) {
        System.out.println("\nStart searching (" + (sortMethod != null ? sortMethod + " + " : "") + mainSearchMethod + ")...");

        startTimer(); // sorting benchmark
        boolean isSuccess = sortMethod == null || sortMethod.sort(data, maxSortingDuration);
        Duration sortDuration = stopTimer();

        startTimer(); // searching benchmark
        int counter = isSuccess ? mainSearchMethod.search(data, queries) : failSearchMethod.search(data, queries);
        Duration searchDuration = stopTimer();

        Duration totalDuration = searchDuration.plus(sortDuration);
        System.out.println(summarizing(counter, queries.size(), isSuccess, sortDuration, searchDuration, totalDuration));
        return totalDuration;
    }

    private String summarizing(int found, int size, boolean isSuccess, Duration sortTime, Duration searchTime, Duration totalTime) {
        StringBuilder res = new StringBuilder("Found " + found + " / " + size + " entries. Time taken: " + formatTime(totalTime) + "\n");
        if (sortMethod != null) {
            res.append("Sorting time: ").append(formatTime(sortTime))
                    .append(isSuccess ? "" : " - STOPPED, moved to linear search").append("\n")
                    .append("Searching time: ").append(formatTime(searchTime)).append("\n");
        }
        return res.toString();
    }

    private void startTimer() {
        this.timer = System.currentTimeMillis();
    }

    private Duration stopTimer() {
        return Duration.of(System.currentTimeMillis() - timer, ChronoUnit.MILLIS);
    }

    static String formatTime(Duration duration) {
        return duration.toMinutesPart() + " min. " + duration.toSecondsPart() + " sec. " + duration.toMillisPart() + " ms.";
    }
}
