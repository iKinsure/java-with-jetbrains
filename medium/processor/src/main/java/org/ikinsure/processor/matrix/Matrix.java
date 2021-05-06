package org.ikinsure.processor.matrix;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Matrix {

    private final int rows;
    private final int columns;
    private final double[][] array;

    public Matrix(int rows, int columns) {
        if (rows < 1 || columns < 1) {
            throw new IllegalArgumentException("Invalid matrix dimensions");
        }
        this.rows = rows;
        this.columns = columns;
        this.array = new double[rows][columns];
    }

    public Matrix(int rows, int columns, ToDoubleBiFunction<Integer,Integer> function) {
        this(rows, columns);
        iterate((i, j) -> array[i][j] = function.applyAsDouble(i, j));
    }

    public void iterate(BiConsumer<Integer,Integer> consumer) {
        IntStream.range(0, rows)
                .forEach(i -> IntStream.range(0, columns)
                        .forEach(j -> consumer.accept(i, j)));
    }

    public double get(int i, int j) {
        return array[i][j];
    }

    public void set(int i, int j, double value) {
        this.array[i][j] = value;
    }

    public int rows() {
        return rows;
    }

    public int columns() {
        return columns;
    }

    public boolean isSquare() {
        return columns == rows;
    }

    public String show() {
        return Arrays.stream(array)
                .map(r -> Arrays.stream(r)
                        .mapToObj(i -> i + "")
                        .collect(Collectors.joining(" ")))
                .collect(Collectors.joining("\n"));
    }
}
