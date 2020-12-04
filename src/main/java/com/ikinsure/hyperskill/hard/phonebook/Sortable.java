package com.ikinsure.hyperskill.hard.phonebook;

import java.time.Duration;
import java.util.List;

public interface Sortable <T extends Comparable<T>> {
    boolean sort(List<T> data, Duration duration);
}
