package com.ikinsure.hyperskill.hard.phonebook;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class BubbleSort<T extends Comparable<T>> implements Sortable<T> {

    @Override
    public boolean sort(List<T> data, Duration duration) {
        Duration limit = duration.multipliedBy(10L);
        long start = System.currentTimeMillis();
        int n = data.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (data.get(j).compareTo(data.get(j + 1)) > 0) {
                    Collections.swap(data, j, j + 1);
                }
            }
            if (limit.toMillis() < (System.currentTimeMillis() - start)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "bubble sort";
    }
}
