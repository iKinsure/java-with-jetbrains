package hard.solver;

import java.util.function.IntConsumer;

public class Row {

    private final double[] elements;

    public Row(double[] elements) {
        this.elements = elements;
    }

    //////////////////////////// Class regular methods //////////////////////////

    public void multiply(double multiplier) {
        iterate(j -> elements[j] *= multiplier);
    }

    public void add(Row row) {
        iterate(j -> elements[j] += row.getElement(j));
    }

    public void subtract(Row row) {
        iterate(j -> elements[j] -= row.getElement(j));
    }

    public void iterate(IntConsumer consumer) {
        for (int j = 0; j < elements.length; j++) {
            consumer.accept(j);
        }
    }

    //////////////////////////// Getters and setters ////////////////////////////

    public int getSize() {
        return elements.length;
    }

    public double[] getElements() {
        return elements;
    }

    public double getElement(int index) {
        return elements[index];
    }

    public void setElement(int index, double value) {
        elements[index] = value;
    }

    //////////////////////////// Override methods ///////////////////////////////

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        iterate(j -> result.append(elements[j]).append(" "));
        return result.toString();
    }
}
