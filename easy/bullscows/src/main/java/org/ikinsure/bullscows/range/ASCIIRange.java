package org.ikinsure.bullscows.range;

import java.util.stream.IntStream;

public class ASCIIRange implements Range {

    private final int start;
    private final int end;

    public ASCIIRange(int startInclusive, int endInclusive) {
        if (startInclusive > endInclusive) {
            throw new IllegalArgumentException("start index cannot be greater than end");
        }
        this.start = startInclusive;
        this.end = endInclusive;
    }

    @Override
    public int[] get(int limit) {
        return IntStream.rangeClosed(start, end).limit(limit).toArray();
    }

    @Override
    public int[] get() {
        return IntStream.rangeClosed(start, end).toArray();
    }

    @Override
    public String joinedRange(int limit) {
        int end = start + limit - 1;
        return String.format("(%c-%c)", start, Math.min(end, this.end));
    }

    @Override
    public String joinedRange() {
        return String.format("(%c-%c)", start, end);
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

}
