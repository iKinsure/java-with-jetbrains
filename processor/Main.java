package processor;

import java.util.Scanner;

public class Main {

    public static final Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) {
        Decision decision = null;
        Matrix m1 = null;
        Matrix m2 = null;
        while (decision != Decision.EXIT) {
            decision = input();
            switch (decision) {
                case ADD:
                    System.out.println("Enter first matrix: ");
                    m1 = create();
                    System.out.println("Enter second matrix: ");
                    m2 = create();
                    print(MatrixManager.add(m1, m2));
                    break;
                case SCALAR_MULTIPLY:
                    System.out.println("Enter matrix: ");
                    m1 = create();
                    System.out.println("Enter multiplier ");
                    print(MatrixManager.scalarMultiply(m1, SCAN.nextInt()));
                    break;
                case MULTIPLY:
                    System.out.println("Enter first matrix: ");
                    m1 = create();
                    System.out.println("Enter second matrix: ");
                    m2 = create();
                    print(MatrixManager.multiply(m1, m2));
                    break;
                case TRANSPOSE:
                    System.out.println("\n1. Main diagonal\n" +
                            "2. Side diagonal\n" +
                            "3. Vertical line\n" +
                            "4. Horizontal line");
                    System.out.print("Your choice: ");
                    int dec = SCAN.nextInt();
                    System.out.println("Enter matrix: ");
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
                    break;
                case DETERMINANT:
                    System.out.println("Enter matrix: ");
                    m1 = create();
                    System.out.println("The result is: \n" + MatrixManager.determinant(m1));
                    break;
            }
            System.out.println();
        }
    }

    private static Matrix create() {
        Matrix m = new Matrix(SCAN.nextInt(), SCAN.nextInt());
        m.iterate((r, c) -> m.set(r, c, SCAN.nextDouble()));
        return m;
    }

    private static Decision input() {
        for (Decision decision : Decision.values()) {
            System.out.println(decision);
        }
        System.out.print("Your choice: ");
        Decision decision = Decision.findById(SCAN.nextInt());
        return decision != null ? decision : Decision.EXIT;
    }

    private static void print(Matrix m) {
        if (m != null) {
            m.iterate((r, c) -> System.out.print(m.get(r, c) + (c == m.getColumns() - 1 ? "\n" : " ")));
        } else {
            System.out.println("ERROR");
        }
    }
}
