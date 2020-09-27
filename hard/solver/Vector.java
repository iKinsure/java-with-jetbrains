package hard.solver;

import java.util.function.IntConsumer;

public class Vector {

    private final double[] elements;

    public Vector(double[] elements) {
        this.elements = elements;
    }

    //////////////////////////// Class regular methods //////////////////////////

    public void multiply(double multiplier) {
        iterate(i -> elements[i] *= multiplier);
    }

    public void add(Vector vec) {
        iterate(i -> elements[i] += vec.getElement(i));
    }

    public void subtract(Vector vec) {
        iterate(i -> elements[i] -= vec.getElement(i));
    }

    public void iterate(IntConsumer consumer) {
        for (int i = 0; i < elements.length; i++) {
            consumer.accept(i);
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
