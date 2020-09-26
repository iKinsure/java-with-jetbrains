package hard.solver;

public class Row {

    private final int columns;
    private final double[] array;

    public Row(double[] array) {
        this.columns = array.length;
        this.array = array;
    }

    //////////////////////////// Class regular methods //////////////////////////

    public void multiply(double multiplier) {
        if (Utility.equals(multiplier, 0.0)) {
            throw new IllegalArgumentException();
        }
        for (int j = 0; j < columns; j++) {
            array[j] *= multiplier;
        }
    }

    public void add(Row row) {
        if (this.columns != row.getColumns()) {
            throw new IllegalArgumentException();
        }
        for (int j = 0; j < columns; j++) {
            array[j] += row.getElement(j);
        }
    }

    public void subtract(Row row) {
        if (this.columns != row.getColumns()) {
            throw new IllegalArgumentException();
        }
        for (int j = 0; j < columns; j++) {
            array[j] -= row.getElement(j);
        }
    }

    private void checkArguments(int column) {
        if (column < 0 || column >= columns) {
            throw new IllegalArgumentException();
        }
    }

    //////////////////////////// Getters and setters ////////////////////////////

    public int getColumns() {
        return columns;
    }

    public double[] getArray() {
        return array;
    }

    public double getElement(int column) {
        checkArguments(column);
        return array[column];
    }

    public void setElement(int column, double value) {
        checkArguments(column);
        array[column] = value;
    }

    //////////////////////////// Override methods ///////////////////////////////

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int j = 0; j < columns; j++) {
            result.append(array[j]).append(" ");
        }
        return result.toString();
    }
}
