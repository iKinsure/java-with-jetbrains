package org.ikinsure.phonebook;

import java.util.List;

public class LinearSearch<T extends Comparable<T>> implements Searchable<T> {

    @Override
    public int search(List<T> data, List<T> queries) {
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

    @Override
    public String toString() {
        return "linear search";
    }
}
