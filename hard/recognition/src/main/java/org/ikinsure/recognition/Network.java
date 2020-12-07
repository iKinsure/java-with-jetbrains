package org.ikinsure.recognition;

public class Network {

    private final Layer[] layers;

    public Network(NeuralDatable data, int... sizes) {
        this.layers = new Layer[sizes.length + 2];
        setLayers(data, sizes);
    }

    private void setLayers(NeuralDatable data, int... sizes) {
        for (int size : sizes) {
            if (size < 1) {
                throw new IllegalArgumentException("Invalid size of layer");
            }
        }
        int firstSize = data.getIdealInput()[0].length;
        int lastSize = data.getIdealOutput()[0].length;
        if (sizes.length > 1) {
            layers[0] = new Layer(firstSize, sizes[0]); // first layer
            for (int i = 1; i < sizes.length; i++) {
                layers[i] = new Layer(sizes[i - 1], sizes[i]);
            }
            layers[layers.length - 2] = new Layer(sizes[sizes.length - 1], lastSize); // pre last layer
            layers[layers.length - 1] = new Layer(lastSize, 0); // last layer
        }
        else if (sizes.length == 1) {
            layers[0] = new Layer(firstSize, sizes[0]); // first layer
            layers[1] = new Layer(sizes[0], lastSize); // middle layer
            layers[2] = new Layer(lastSize, 0); // last layer
        }
        else {
            layers[0] = new Layer(firstSize, lastSize); // first layer
            layers[1] = new Layer(lastSize, 0); // last layer
        }
        layers[0].setIdeals(data.getIdealInput());
        layers[layers.length - 1].setIdeals(data.getIdealOutput());
    }

    public double[] calculateNextLayerValues(Layer prevLayer) {
        double[][] matrix = prevLayer.getWeightsMatrix();
        double[] vector = prevLayer.getValuesVector();
        double[] result = new double[matrix.length];
        for (int y = 0; y < matrix.length; y++) {
            double counter = 0.0;
            for (int x = 0; x < matrix[0].length; x++) {
                counter += matrix[y][x] * vector[x];
            }
            result[y] = sigmoid(counter);
        }
        return result;
    }

    public Layer getLayer(int index) {
        if (index < 0 || index > layers.length - 1) {
            throw new IllegalArgumentException("Index out of bound");
        }
        return layers[index];
    }

    public int getLength() {
        return layers.length;
    }

    public static double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    public Layer getFirstLayer() {
        return layers[0];
    }

    public Layer getLastLayer() {
        return layers[layers.length - 1];
    }
}
