package com.ikinsure.hyperskill.hard.recognition;

import java.util.Scanner;

public class Main {

    private static final int HEIGHT = 5;
    private static final int WIDTH = 3;
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Input grid:");
        int[] array = input();
        NumberData result = NumberData.N0;
        int max = Integer.MIN_VALUE;
        for (NumberData number : NumberData.values()) {
            int actual = getNeuralSum(array, number.weight, number.bias);
            if (actual > max) {
                result = number;
                max = actual;
            }
        }
        System.out.println("This number is " + result.sign);
    }

    private static int getNeuralSum(int[] array, int[] weight, int bias) {
        for (int i = 0; i < weight.length; i++) {
            bias += weight[i] * array[i];
        }
        return bias;
    }

    private static int[] input() {
        int[] array = new int[HEIGHT * WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            char[] input = SCANNER.nextLine().strip().toCharArray();
            for (int j = 0; j < WIDTH; j++) {
                switch (input[j]) {
                    case 'X':
                        array[i * WIDTH + j] = 1;
                        break;
                    case '_':
                        array[i * WIDTH + j] = 0;
                        break;
                }
            }
        }
        return array;
    }
}
