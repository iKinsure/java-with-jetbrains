package org.ikinsure.platform;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CodeContainer {

    private static final ArrayList<Code> codes = new ArrayList<>(List.of(new Code("")));

    public static List<Code> getLatest(int size) {
        final int length = codes.size() - 1;
        List<Code> result = new ArrayList<>();
        IntStream.range(0, Math.min(length, size))
                .forEach(i -> result.add(codes.get(length - i)));
        return result;
    }

    public static Code getByIndex(int index) {
        return codes.get(index);
    }

    public static boolean add(Code code) {
        return codes.add(code);
    }

    public static int size() {
        return codes.size();
    }
}
