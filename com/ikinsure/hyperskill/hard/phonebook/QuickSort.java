package com.ikinsure.hyperskill.hard.phonebook;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class QuickSort<T extends Comparable<T>> implements Sortable<T> {

    @Override
    public boolean sort(List<T> data, Duration duration) {
        algorithm(data, 0, data.size() - 1);
        return true;
    }

    private void algorithm(List<T> data, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(data, left, right);
            algorithm(data, left, pivotIndex - 1);
            algorithm(data, pivotIndex + 1, right);
        }
    }

    private int partition(List<T> data, int left, int right) {
        T pivot = data.get(right);
        int partitionIndex = left;
        for (int i = left; i < right; i++) {
            if (data.get(i).compareTo(pivot) <= 0) {
                Collections.swap(data, i, partitionIndex);
                partitionIndex++;
            }
        }
        Collections.swap(data, partitionIndex, right);
        return partitionIndex;
    }
}
