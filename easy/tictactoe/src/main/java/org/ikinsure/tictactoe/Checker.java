package org.ikinsure.tictactoe;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Checker {

    private final Field[][] matrix;

    public Checker(Field[][] matrix) {
        this.matrix = matrix;
    }

    public boolean isDraw() {
        return Arrays.stream(matrix).flatMap(Arrays::stream).noneMatch(i -> i.equals(Field.EMPTY));
    }

    /**
     * checking if side is winner
     * checking rows -> checking columns -> checking diagonals
     */
    public boolean isWinner(Field field) {
        return Arrays.stream(matrix).anyMatch(row -> check(field, row)) ||
                IntStream.range(0, matrix.length).anyMatch(i -> check(field, column(i))) ||
                check(field, diagonal(false)) ||
                check(field, diagonal(true));
    }

    private Field[] diagonal(boolean left) {
        AtomicInteger counter = new AtomicInteger(left ? 0 : matrix.length - 1);
        return Arrays.stream(matrix)
                .map(row -> row[left ? counter.getAndIncrement() : counter.getAndDecrement()])
                .toArray(Field[]::new);
    }

    private Field[] column(int j) {
        return Arrays.stream(matrix).map(row -> row[j]).toArray(Field[]::new);
    }

    private boolean check(Field field, Field[] row) {
        return Arrays.stream(row).allMatch(i -> i.equals(field));
    }

}
