package org.ikinsure.recognition;

import java.util.Arrays;

/**
 * immutable class represents network's neuron
 */
public class Neuron {

    private final double[] weights; // "w"
    private final double value; // "a"

    public Neuron(double[] weights, double value) {
        this.weights = Arrays.copyOf(weights, weights.length);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public int getConnections() {
        return weights.length;
    }

    public double getWeight(int index) {
        if (index < 0 || index > weights.length - 1) {
            throw new IllegalArgumentException("Index out of bound");
        }
        return weights[index];
    }

    public Neuron setWeights(double[] weights) {
        return new Neuron(weights, weights.length);
    }

    public Neuron setValue(double value) {
        return new Neuron( weights, value);
    }
}
