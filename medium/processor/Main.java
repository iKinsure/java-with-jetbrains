package medium.processor;

import java.util.Scanner;

public class Main {

    public static final Scanner SCAN = new Scanner(System.in);
    private static Matrix m1 = null;
    private static Matrix m2 = null;
    private static Decision decision = null;

    public static void main(String[] args) {
        while (decision != Decision.EXIT) {
            decision = input();
            switch (decision) {
                case ADD:
                    add();
                    break;
                case SCALAR_MULTIPLY:
                    scalarMultiply();
                    break;
                case MULTIPLY:
                    multiply();
                    break;
                case TRANSPOSE:
                    transpose();
                    break;
                case DETERMINANT:
                    determinant();
                    break;
                case INVERSE:
                    inverse();
                    break;
            }
            System.out.println();
        }
    }

    private static void add() {
        m1 = create();
        m2 = create();
        print(MatrixManager.add(m1, m2));
    }

    private static void scalarMultiply() {
        m1 = create();
        System.out.println("Enter multiplier ");
        print(MatrixManager.scalarMultiply(m1, SCAN.nextInt()));
    }

    private static void multiply() {
        m1 = create();
        m2 = create();
        print(MatrixManager.multiply(m1, m2));
    }

    private static void transpose() {
        System.out.println("\n1. Main diagonal\n" +
                "2. Side diagonal\n" +
                "3. Vertical line\n" +
                "4. Horizontal line");
        System.out.print("Your choice: ");
        int dec = SCAN.nextInt();
        m1 = create();
        switch (dec) {
            case 1:
                print(MatrixManager.transposeOverMainDiagonal(m1));
                break;
            case 2:
                print(MatrixManager.transposeOverSideDiagonal(m1));
                break;
            case 3:
                print(MatrixManager.transposeByVerticalLine(m1));
                break;
            case 4:
                print(MatrixManager.transposeByHorizontalLine(m1));
                break;
        }
    }

    private static void determinant() {
        m1 = create();
        System.out.println("The result is: \n" + MatrixManager.determinant(m1));
    }

    private static void inverse() {
        m1 = create();
        print(MatrixManager.inverse(m1));
    }

    private static Matrix create() {
        System.out.print("Enter matrix size: ");
        Matrix m = new Matrix(SCAN.nextInt(), SCAN.nextInt());
        System.out.println("Enter matrix: ");
        m.iterate((r, c) -> m.set(r, c, SCAN.nextDouble()));
        return m;
    }

    private static Decision input() {
        for (Decision d : Decision.values()) {
            System.out.println(d);
        }
        System.out.print("Your choice: ");
        Decision d = Decision.findById(SCAN.nextInt());
        return d != null ? d : Decision.EXIT;
    }

    private static void print(Matrix m) {
        if (m != null) {
            m.iterate((r, c) -> System.out.print(m.get(r, c) + (c == m.getColumns() - 1 ? "\n" : " ")));
        } else {
            System.out.println("ERROR");
        }
    }
}
