package com.ikinsure.hyperskill.hard.phonebook;

import java.util.List;

public class JumpSearch<T extends Comparable<T>> implements Searchable<T> {

    @Override
    public int search(List<T> data, List<T> queries) {
        int counter = 0;
        for (T query : queries) {
            if (algorithm(data, query) != -1) {
                counter++;
            }
        }
        return counter;
    }

    private int algorithm(List<T> data, T query) {
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

    @Override
    public String toString() {
        return "jump search";
    }
}
