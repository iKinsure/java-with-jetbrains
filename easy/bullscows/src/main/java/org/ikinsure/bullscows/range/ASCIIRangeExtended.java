package org.ikinsure.bullscows.range;

import java.util.*;
import java.util.stream.Collectors;

public class ASCIIRangeExtended implements Range {

    private final List<ASCIIRange> ranges;

    public ASCIIRangeExtended(List<ASCIIRange> ranges) {
        List<ASCIIRange> temp = new ArrayList<>(ranges);
        temp.sort(Comparator.comparingInt(ASCIIRange::getStart));
        this.ranges = temp;
    }

    @Override
    public int[] get(int limit) {
        return ranges.stream()
                .flatMapToInt(r -> Arrays.stream(r.get()))
                .limit(limit)
                .toArray();
    }

    @Override
    public int[] get() {
        return ranges.stream()
                .flatMapToInt(r -> Arrays.stream(r.get()))
                .toArray();
    }

    @Override
    public String joinedRange(int limit) {

        // last item by limit
        int[] data = get(limit);
        int lastItem = data[data.length - 1];

        // replace last
        List<ASCIIRange> temp = ranges.stream()
                .takeWhile(r -> lastItem >= r.getStart())
                .collect(Collectors.toList());
        final int index = temp.size() - 1;
        ASCIIRange last = temp.get(index);
        temp.remove(index);
        temp.add(new ASCIIRange(last.getStart(), lastItem));
        return "(" + joinedRange(temp) + ")";
    }

    @Override
    public String joinedRange() {
        return "(" + joinedRange(ranges) + ")";
    }

    private String joinedRange(List<ASCIIRange> data) {
        return data.stream()
                .map(r -> (char) r.getStart() + "-" + (char) r.getEnd())
                .collect(Collectors.joining(", "));
    }

}
