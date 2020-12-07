package org.ikinsure.recognition;

import java.util.Arrays;
import java.util.Random;

public class Layer {

    private final Neuron[] neurons;
    private double[][] idealInput;
    private final int connectionsForEachNeuron;

    public Layer(int size, int connectionsForEachNeuron) {
        this.connectionsForEachNeuron = connectionsForEachNeuron;
        this.neurons = new Neuron[size];
        this.idealInput = new double[size][connectionsForEachNeuron];
        Random random = new Random();
        for (int i = 0; i < neurons.length; i++) {
            neurons[i] = new Neuron(
                    Arrays.stream(new double[connectionsForEachNeuron]).map(d -> random.nextGaussian()).toArray(), 0.0);
        }
    }

    public void setIdeals(double[][] idealInput) {
        this.idealInput = idealInput;
    }

    public double[][] getIdealInput() {
        return idealInput;
    }

    public void setNeuronsValues(double[] values) {
        if (values.length != neurons.length) {
            throw new IllegalArgumentException("Invalid size of data");
        }
        for (int i = 0; i < neurons.length; i++) {
            neurons[i] = neurons[i].setValue(values[i]);
        }
    }

    public double[][] getWeightsMatrix() {
        double[][] result = new double[connectionsForEachNeuron][neurons.length];
        for (int x = 0; x < neurons.length; x++) {
            for (int y = 0; y < connectionsForEachNeuron; y++) {
                result[y][x] = neurons[x].getWeight(y);
            }
        }
        return result;
    }

    public double[] getValuesVector() {
        double[] result = new double[neurons.length];
        for (int i = 0; i < neurons.length; i++) {
            result[i] = neurons[i].getValue();
        }
        return result;
    }

    public Neuron getNeuron(int index) {
        if (index < 0 || index > neurons.length - 1) {
            throw new IllegalArgumentException("Index out of bound");
        }
        return neurons[index];
    }

    public int getConnectionsForEachNeuron() {
        return connectionsForEachNeuron;
    }

    public int getLength() {
        return neurons.length;
    }
}
