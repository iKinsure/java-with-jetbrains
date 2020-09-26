package hard.solver;

import java.util.function.IntConsumer;

public class Matrix {

    private final int rows;
    private final int columns;
    private final Row[] array;

    public Matrix(double[][] array) {
        this.rows = array.length;
        this.columns = array[0].length;
        this.array = new Row[rows];
        for (int i = 0; i < array.length; i++) {
            Row row = new Row(array[i]);
            if (row.getColumns() != columns) {
                throw new IllegalArgumentException();
            }
            this.array[i] = row;
        }
    }

    //////////////////////////// Class regular methods //////////////////////////

    public void interchange(int firstRowId, int secondRowId) {
        Row temporary = array[firstRowId];
        array[firstRowId] = array[secondRowId];
        array[secondRowId] = temporary;
    }

    public void multiply(int rowId, double multiplier) {
        array[rowId].multiply(multiplier);
    }

    public void add(int from, int to) {
        array[to].add(array[from]);
    }

    public void subtract(int from, int to) {
        array[to].subtract(array[from]);
    }

    public boolean isColumnBelowNull(int row, int column) {
        boolean isNull = true;
        for (int i = row; i < rows; i++) {
            if (!Utility.equals(array[i].getElement(column), 0.0)) {
                isNull = false;
                break;
            }
        }
        return isNull;
    }

    private void iterate(IntConsumer consumer) {
        for (int i = 0; i < rows; i++) {
            consumer.accept(i);
        }
    }

    //////////////////////////// Getters and setters ////////////////////////////

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Row[] getArray() {
        return array;
    }

    public double getElement(int row, int column) {
        return array[row].getElement(column);
    }

    public void setElement(int row, int column, double value) {
        array[row].setElement(column, value);
    }

    //////////////////////////// Override methods ///////////////////////////////

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        iterate(i -> result.append(array[i]).append("\n"));
        return result.toString();
    }
}
