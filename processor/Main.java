package processor;

import java.util.Scanner;

public class Main {

    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int[][] m1 = new int[SCANNER.nextInt()][SCANNER.nextInt()];
        readMatrix(m1);
        int multiplier = SCANNER.nextInt();
        printMatrix(multiplication(m1, multiplier));
    }

    private static int[][] addition(int[][] m1, int [][] m2) {
        if (m1.length == m2.length && m1[0].length == m2[0].length) {
            int[][] result = new int[m1.length][m1[0].length];
            for (int m = 0; m < m1.length; m++) {
                for (int n = 0; n < m1[m].length; n++) {
                    result[m][n] = m1[m][n] + m2[m][n];
                }
            }
            return result;
        }
        return null;
    }

    private static int[][] multiplication(int[][] matrix, int multiplier) {
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int m = 0; m < matrix.length; m++) {
            for (int n = 0; n < matrix[m].length; n++) {
                result[m][n] = matrix[m][n] * multiplier;
            }
        }
        return result;
    }

    private static void readMatrix(int[][] matrix) {
        for (int m = 0; m < matrix.length; m++) {
            for (int n = 0; n < matrix[m].length; n++) {
                matrix[m][n] = SCANNER.nextInt();
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        if (matrix != null) {
            for (int[] elements : matrix) {
                for (int element : elements) {
                    System.out.print(element + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("ERROR");
        }
    }
}
