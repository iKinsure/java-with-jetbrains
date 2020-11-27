package com.ikinsure.hyperskill.hard.phonebook;

import java.util.List;

public interface Searchable <T extends Comparable<T>> {
    int search(List<T> data, List<T> queries);
}
