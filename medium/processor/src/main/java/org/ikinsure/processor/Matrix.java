package org.ikinsure.processor;

import java.util.function.BiConsumer;
import java.util.function.ToDoubleBiFunction;

public class Matrix {

    private final int rows;
    private final int columns;
    private final double[][] array;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.array = new double[rows][columns];
    }

    public Matrix(int rows, int columns, ToDoubleBiFunction<Integer,Integer> function) {
        this(rows, columns);
        iterate((r, c) -> array[r][c] = function.applyAsDouble(r, c));
    }

    public void iterate(BiConsumer<Integer,Integer> consumer) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                consumer.accept(r, c);
            }
        }
    }

    public double get(int r, int c) {
        return array[r][c];
    }

    public void set(int r, int c, double value) {
        this.array[r][c] = value;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
