package org.ikinsure.hard.phonebook;

import java.util.List;

public class BinarySearch<T extends Comparable<T>> implements Searchable<T> {

    @Override
    public int search(List<T> data, List<T> queries) {
        int counter = 0;
        for (T query : queries) {
            if (algorithm(data, query, 0, data.size() - 1) != -1) {
                counter++;
            }
        }
        return counter;
    }

    private int algorithm(List<T> data, T query, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (data.get(mid).equals(query)) {
                return mid;
            } else if (data.get(mid).compareTo(query) > 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "binary search";
    }
}
