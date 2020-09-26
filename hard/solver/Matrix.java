package hard.solver;

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
        if (firstRowId < 0 || firstRowId >= rows || secondRowId < 0 || secondRowId >= rows) {
            throw new IllegalArgumentException();
        }
        Row temporary = array[firstRowId];
        array[firstRowId] = array[secondRowId];
        array[secondRowId] = temporary;
    }

    public void multiply(int rowId, double multiplier) {
        if (Utility.equals(multiplier, 0.0) || rowId < 0 || rowId >= rows) {
            throw new IllegalArgumentException();
        }
        array[rowId].multiply(multiplier);
    }

    public void add(int from, int to) {
        if (from < 0 || from >= rows || to < 0 || to >= rows) {
            throw new IllegalArgumentException();
        }
        array[to].add(array[from]);
    }

    public void subtract(int from, int to) {
        if (from < 0 || from >= rows || to < 0 || to >= rows) {
            throw new IllegalArgumentException();
        }
        array[to].subtract(array[from]);
    }

    public boolean isColumnBelowNull(int row, int column) {
        checkArguments(row, column);
        boolean isNull = true;
        for (int i = row; i < rows; i++) {
            if (!Utility.equals(array[i].getElement(column), 0.0)) {
                isNull = false;
                break;
            }
        }
        return isNull;
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
        checkArguments(row, column);
        return array[row].getElement(column);
    }

    public void setElement(int row, int column, double value) {
        checkArguments(row, column);
        array[row].setElement(column, value);
    }

    private void checkArguments(int row, int column) {
        if (row < 0 || row >= rows || column < 0 || column >= columns) {
            throw new IllegalArgumentException();
        }
    }

    //////////////////////////// Override methods ///////////////////////////////

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Row row : array) {
            result.append(row).append("\n");
        }
        return result.toString();
    }
}
