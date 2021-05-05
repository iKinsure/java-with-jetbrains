package org.ikinsure.bullscows;

import org.ikinsure.bullscows.range.ASCIIRange;
import org.ikinsure.bullscows.range.ASCIIRangeExtended;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Game {

    private final int[] range;
    private final int[] code;
    private final String rangeString;

    private Game(int length, int[] range, String rangeString) {
        this.range = range;
        this.code = generateCodeWithoutRepeat(length, range);
        this.rangeString = rangeString;
    }

    public long bulls(char[] input) {
        return IntStream.range(0, Math.min(input.length, code.length))
                .filter(i -> input[i] == code[i])
                .count();
    }

    public long cows(char[] input) {
        int size = Math.min(input.length, code.length);
        return IntStream.range(0, size)
                .filter(i -> IntStream.range(0, size).anyMatch(j -> input[i] == code[j] && i != j))
                .count();
    }

    /**
     * check if input code is containing range symbols
     */
    public boolean validateChars(String input) {
        return input.chars().allMatch(c -> Arrays.stream(range).anyMatch(r -> r == c));
    }

    public String prepare() {
        return "The secret is prepared: " + "*".repeat(code.length) + " " + rangeString + ".";
    }

    public int[] getCode() {
        return code;
    }

    public int[] getRange() {
        return range;
    }

    /**
     * generate random code of length without repeat from range array
     */
    private int[] generateCodeWithoutRepeat(int length, int[] range) {
        if (length > range.length) {
            throw new IllegalArgumentException("Length is greater than range");
        }
        Random random = new Random();
        int[] code = new int[length];
        for (int i = 0; i < length; i++) {
            while (true) {
                final int rand = range[random.nextInt(range.length)];
                if (Arrays.stream(code).noneMatch(c -> c == rand)) {
                    code[i] = rand;
                    break;
                }
            }
        }
        return code;
    }

    /**
     * create Game with validation
     */
    public static Game create(int secretLength, int symbolsRange) {

        ASCIIRangeExtended ascii = new ASCIIRangeExtended(List.of(
                new ASCIIRange('0', '9'), new ASCIIRange('a', 'z')
        ));
        String rangeString = ascii.joinedRange(symbolsRange);
        String totalRangeString = ascii.joinedRange();
        int maxRange = ascii.get().length;

        if (symbolsRange > maxRange) {
            throw new IllegalArgumentException(String.format(
                    "Error: maximum number of possible symbols in the code is %d %s.",
                    maxRange,
                    totalRangeString
            ));
        }

        if (secretLength > symbolsRange || secretLength <= 0) {
            throw new IllegalArgumentException(String.format(
                    "Error: it's not possible to generate a code with a length of %d with %d unique symbols.",
                    secretLength,
                    symbolsRange
            ));
        }

        return new Game(secretLength, ascii.get(symbolsRange), rangeString);
    }

}
