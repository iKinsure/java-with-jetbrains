package org.ikinsure.recognition;
import java.util.Scanner;

public class Main {

    private static final int HEIGHT = 5;
    private static final int WIDTH = 3;
    private static final Scanner SCANNER = new Scanner(System.in);
    private static int generation = 0;
    private static final Network network = new Network(new NumberData(),12, 12);

    public static void main(String[] args) {
        setIdeals();
        while (true) {
            System.out.println("1. Learn the network");
            System.out.println("2. Guess a number");
            System.out.print("Your choice: ");
            switch (SCANNER.nextInt()) {
                case 1:
                    System.out.println("Learning... #" + generation++);
                    learning();
                    System.out.println("Done! Saved to the file.");
                    break;
                case 2:
                    System.out.println("Input grid:");
                    System.out.println("This number is " + guess());
                    break;
                default:
                    return;
            }
        }
    }

    private static int guess() {
        neuralOutput(readInput());
        int index = 0;
        double[] values = network.getLastLayer().getValuesVector();
        for (int i = 0; i < values.length; i++) {
            index = values[i] > values[index] ? i : index;
        }
        return index + 1;
    }

    private static void learning() {
//        for (int i = 0; i < 10; i++) {
//            for (NumberData num : NumberData.values()) {
//                neuralOutput(num.idealInput);
//                neuralWeight(num.idealOutput);
//            }
//        }
    }

    private static void neuralOutput(double[] array) {
        Layer layer = network.getFirstLayer();
        layer.setNeuronsValues(array);
        for (int i = 1; i < network.getLength(); i++) {
            double[] values = network.calculateNextLayerValues(layer);
            layer = network.getLayer(i);
            layer.setNeuronsValues(values);
        }
    }

    private static void neuralWeight() {
//        for (int j = 0; j < network.getLength(); j++) {
//            Layer layer = network.getLayer(j);
//
//            for (int i = 0; i < layer.getLength(); i++) {
//                Neuron neuron = layer.getNeuron(i);
//                double[] w = new double[layer.getConnectionsForEachNeuron()];
//
//                for (int k = 0; k < layer.getConnectionsForEachNeuron(); k++) {
//                    w[k] = neuron.getWeight(k) + delta(input[], layer.getIdealInput()[][], values[]);
//
//                    // num.weight[j] += delta(input[j], ideals[num.ordinal()], num.output);
//                }
//                neuron = neuron.setWeights(w);
//            }
//        }
    }

    private static void setIdeals() {
        double[] output = network.getLastLayer().getValuesVector();
        for (int i = network.getLength() - 2; i >= 1; i--) {
            Layer layer = network.getLayer(i);
            double[] input = layer.getValuesVector();

            double[][] ideals = new double[10][network.getLayer(i).getLength()];

            for (int j = 0; j < output.length; j++) {
                output[j] = 1.0 / Network.sigmoid(output[j]);
                for (int k = 0; k < input.length; k++) {
                    double delta = (output[j] - input[k]) / input[k];
                    ideals[j][k] = output[j] + (delta - 0.5) / input[k];
                }
            }
            output = input;
            network.getLayer(i).setIdeals(ideals);
        }
    }



    private static double delta(double input, double ideal, double actual) {
        final double eta = 0.5;
        return eta * input * (ideal - actual);
    }

    private static double[] readInput() {
        double[] array = new double[HEIGHT * WIDTH + 1];
        for (int i = 0; i < HEIGHT; i++) {
            char[] input = SCANNER.next().strip().toCharArray();
            for (int j = 0; j < WIDTH; j++) {
                switch (input[j]) {
                    case 'X':
                        array[i * WIDTH + j] = 1.0;
                        break;
                    case '_':
                        array[i * WIDTH + j] = 0.0;
                        break;
                }
            }
        }
        array[array.length - 1] = 1;
        return array;
    }
}
