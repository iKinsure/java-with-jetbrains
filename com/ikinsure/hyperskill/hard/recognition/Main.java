package com.ikinsure.hyperskill.hard.recognition;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final int HEIGHT = 5;
    private static final int WIDTH = 3;
    private static final Scanner SCANNER = new Scanner(System.in);

    private static double[] input = new double[16];
    private static double[] output = new double[10];

    public static void main(String[] args) {

        for (int i = 0; i < 12; i++) {
            learning();
        }

        while (true) {
            System.out.println("1. Learn the network");
            System.out.println("2. Guess a number");
            System.out.print("Your choice: ");
            switch (SCANNER.nextInt()) {
                case 1:
                    System.out.println("Learning...");
                    learning();
                    System.out.println("Done! Saved to the file.");
                    break;
                case 2:
                    System.out.println("Input grid:");
                    System.out.println("This number is " + guess().sign);
                    return;
                default:
                    return;
            }
        }
    }

    private static NumberData guess() {
        neuralOutput(readInput());
        int index = 0;
        for (int i = 1; i < output.length; i++) {
            index = output[i] > output[index] ? i : index;
        }
        return NumberData.values()[index];
    }

    private static void learning() {
        for (int i = 0; i < 10; i++) {
            for (NumberData num : NumberData.values()) {
                neuralOutput(num.idealInput);
                neuralWeight(num.idealOutput);
            }
        }
    }

    private static void neuralWeight(double[] ideals) {
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < input.length; j++) {
                NumberData num = NumberData.values()[i];
                num.weight[j] += delta(input[j], ideals[i], output[i]);
            }
        }
    }

    private static void neuralOutput(double[] array) {
        input = Arrays.copyOf(array, array.length);
        for (int i = 0; i < output.length; i++) {
            output[i] = 0.0;
            for (int j = 0; j < input.length; j++) {
                output[i] += input[j] * NumberData.values()[i].weight[j];
            }
            output[i] = sigmoid(output[i]);
        }
    }

    private static double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
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
