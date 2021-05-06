package org.ikinsure.processor;

import org.ikinsure.processor.matrix.Matrix;
import org.ikinsure.processor.matrix.MatrixManager;

import java.util.List;

public class Processor {

    private final View view;
    private boolean running;

    public Processor(View view) {
        this.view = view;
        this.running = false;
    }

    public void run() {

        List<Decision> decisions = List.of(
                new Decision(1, "Add matrices", this::add),
                new Decision(2, "Multiply matrix by a constant", this::scalarMultiply),
                new Decision(3, "Multiply matrices", this::multiply),
                new Decision(4, "Transpose matrix", this::transpose),
                new Decision(5, "Calculate a determinant", this::determinant),
                new Decision(6, "Inverse matrix", this::inverse),
                new Decision(0, "Exit", () -> running = false)
        );

        running = true;
        while (running) {

            decisions.forEach(d -> view.out(d.getId() + ". " + d.getDescription()));
            view.out("Your choice: ");

            int id = view.nextInt();
            decisions.stream()
                    .filter(d -> d.getId() == id)
                    .findFirst()
                    .orElse(decisions.get(0))
                    .getExecutable()
                    .execute();
        }
    }

    private void add() {
        view.out(MatrixManager.add(create(), create()).show());
    }

    private void scalarMultiply() {
        Matrix matrix = create();
        view.out("Enter multiplier ");
        view.out(MatrixManager.scalarMultiply(matrix, view.nextDouble()).show());
    }

    private void multiply() {
        view.out(MatrixManager.multiply(create(), create()).show());
    }

    private void transpose() {
        view.out("\n1. Main diagonal\n" +
                "2. Side diagonal\n" +
                "3. Vertical line\n" +
                "4. Horizontal line\n" +
                "Your choice: "
        );
        int decision = view.nextInt();
        Matrix matrix = create();
        switch (decision) {
            case 1:
                view.out(MatrixManager.transposeOverMainDiagonal(matrix).show());
                break;
            case 2:
                view.out(MatrixManager.transposeOverSideDiagonal(matrix).show());
                break;
            case 3:
                view.out(MatrixManager.transposeByVerticalLine(matrix).show());
                break;
            case 4:
                view.out(MatrixManager.transposeByHorizontalLine(matrix).show());
                break;
        }
    }

    private void determinant() {
        view.out("The result is: \n" + MatrixManager.determinant(create()));
    }

    private void inverse() {
        view.out(MatrixManager.inverse(create()).show());
    }

    private Matrix create() {
        view.out("Enter matrix size: ");
        int rows = view.nextInt();
        int columns = view.nextInt();
        view.out("Enter matrix: ");
        Matrix matrix = new Matrix(rows, columns);
        matrix.iterate((i, j) -> matrix.set(i, j, view.nextDouble()));
        return matrix;
    }

}
