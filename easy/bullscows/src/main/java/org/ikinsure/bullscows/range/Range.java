package org.ikinsure.bullscows.range;

public interface Range {
    int[] get(int limit);
    int[] get();
    String joinedRange(int limit);
    String joinedRange();
}
