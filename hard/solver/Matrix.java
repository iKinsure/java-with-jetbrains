package hard.solver;

public class Matrix {

    private final int rows;
    private final int columns;
    private final double[][] array;

    public Matrix(double[][] array) {
        this.array = array;
        this.rows = array.length;
        this.columns = array[0].length;
    }

    //////////////////////////// Class regular methods //////////////////////////

    public void interchange(int row1, int row2) {
        if (row1 < 0 || row1 >= rows || row2 < 0 || row2 >= rows) {
            throw new IllegalArgumentException();
        }
        for (int j = 0; j < columns; j++) {
            double temporary = array[row1][j];
            array[row1][j] = array[row2][j];
            array[row2][j] = temporary;
        }
    }

    public void multiply(int row, double multiplier) {
        if (Utility.equals(multiplier, 0.0) || row < 0 || row >= rows) {
            throw new IllegalArgumentException();
        }
        for (int j = 0; j < columns; j++) {
            array[row][j] *= multiplier;
        }
    }

    public void add(int from, int to) {
        if (from < 0 || from >= rows || to < 0 || to >= rows) {
            throw new IllegalArgumentException();
        }
        for (int j = 0; j < columns; j++) {
            array[to][j] += array[from][j];
        }
    }

    public void subtract(int from, int to) {
        if (from < 0 || from >= rows || to < 0 || to >= rows) {
            throw new IllegalArgumentException();
        }
        for (int j = 0; j < columns; j++) {
            array[to][j] -= array[from][j];
        }
    }

    public boolean isColumnBelowNull(int row, int column) {
        checkArguments(row, column);
        boolean isNull = true;
        for (int i = row; i < rows; i++) {
            if (!Utility.equals(array[i][column], 0.0)) {
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

    public double[][] getArray() {
        return array;
    }

    public double getElement(int row, int column) {
        checkArguments(row, column);
        return array[row][column];
    }

    public void setElement(int row, int column, double value) {
        checkArguments(row, column);
        array[row][column] = value;
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
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.append(array[i][j]).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
