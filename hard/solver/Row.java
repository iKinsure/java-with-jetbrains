package hard.solver;

import java.util.function.IntConsumer;

public class Row {

    private final int columns;
    private final double[] array;

    public Row(double[] array) {
        this.columns = array.length;
        this.array = array;
    }

    //////////////////////////// Class regular methods //////////////////////////

    public void multiply(double multiplier) {
        iterate(j -> array[j] *= multiplier);
    }

    public void add(Row row) {
        iterate(j -> array[j] += row.getElement(j));
    }

    public void subtract(Row row) {
        iterate(j -> array[j] -= row.getElement(j));
    }

    private void iterate(IntConsumer consumer) {
        for (int j = 0; j < columns; j++) {
            consumer.accept(j);
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
        return array[column];
    }

    public void setElement(int column, double value) {
        array[column] = value;
    }

    //////////////////////////// Override methods ///////////////////////////////

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        iterate(j -> result.append(array[j]).append(" "));
        return result.toString();
    }
}
