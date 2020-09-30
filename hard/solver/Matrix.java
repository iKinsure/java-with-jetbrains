package hard.solver;

import java.util.Arrays;

public class Matrix {

    private int rowSize;
    private int colSize;
    private Row[] rows;

    public Matrix(double[][] array) {
        this.rowSize = array.length;
        this.colSize = array[0].length;
        this.rows = new Row[rowSize];
        for (int i = 0; i < array.length; i++) {
            Row row = new Row(array[i]);
            if (row.getSize() != colSize) {
                throw new IllegalArgumentException();
            }
            this.rows[i] = row;
        }
    }

    //////////////////////////// Class regular methods //////////////////////////

    /**
     * swaps the rows
     */
    public void interchange(int firstRowIndex, int secondRowIndex) {
        Row temporary = rows[firstRowIndex];
        rows[firstRowIndex] = rows[secondRowIndex];
        rows[secondRowIndex] = temporary;
    }

    /**
     * multiply the row by scalar
     */
    public void multiply(int rowIndex, double multiplier) {
        rows[rowIndex].multiply(multiplier);
    }

    public void add(int to, int from) {
        rows[to].add(rows[from]);
    }

    public void subtract(int to, int from) {
        rows[to].subtract(rows[from]);
    }

    public void add(int to, Row from) {
        rows[to].add(from);
    }

    public void subtract(int to, Row from) {
        rows[to].subtract(from);
    }

    /**
     * checks if column below @params is filled with zeros
     */
    public boolean isColumnBelowNull(int row, int column) {
        boolean isZero = true;
        for (int i = row + 1; i < rowSize; i++) {
            if (!Utility.equals(rows[i].getElement(column), 0.0)) {
                isZero = false;
                break;
            }
        }
        return isZero;
    }

    /**
     * checks if column up from @params is filled with zeros
     */
    public boolean isColumnUpperNull(int row, int column) {
        boolean isZero= true;
        for (int i = 0; i <= row - 1; i++) {
            if (!Utility.equals(rows[i].getElement(column), 0.0)) {
                isZero = false;
                break;
            }
        }
        return isZero;
    }

    public double[] getColumn(int column) {
        double[] result = new double[rowSize];
        for (int i = 0; i < result.length; i++) {
            result[i] = getElement(i, column);
        }
        return result;
    }

    public void deleteLastRow() {
        this.rows = Arrays.copyOfRange(rows, 0, rows.length -1);
        this.rowSize = rows.length;
    }

    public void swapColumn(int firstColIndex, int secondColIndex) {
        for (int i = 0; i < rowSize; i++) {
            double temporary = getElement(i, firstColIndex);
            setElement(i, firstColIndex, getElement(i, secondColIndex));
            setElement(i,secondColIndex, temporary);
        }
    }

    //////////////////////////// Getters and setters ////////////////////////////

    public int getRowSize() {
        return rowSize;
    }

    public int getColSize() {
        return colSize;
    }

    public Row[] getRows() {
        return rows;
    }

    public double getElement(int rowIndex, int columnIndex) {
        return rows[rowIndex].getElement(columnIndex);
    }

    public void setElement(int rowIndex, int columnIndex, double value) {
        rows[rowIndex].setElement(columnIndex, value);
    }

    //////////////////////////// Override methods ///////////////////////////////

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rowSize; i++) {
            result.append(rows[i]).append("\n");
        }
        return result.toString();
    }
}
