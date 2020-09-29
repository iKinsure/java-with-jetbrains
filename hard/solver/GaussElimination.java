package hard.solver;

import java.util.Arrays;

public class GaussElimination implements LinearEquationAlgorithm<Matrix> {

    private int variables;
    private int equations;
    private Matrix matrix;
    private boolean isSolution;
    private String message;

    @Override
    public double[] solve(Matrix matrix) {
        this.matrix = matrix;
        this.variables = matrix.getColSize() -1;
        this.equations = matrix.getRowSize();

        // clearance
        sortZeros();
        isNoSolution();
        removeNullRows();

        if (!isSolution) { // no solutions
            this.message = "No solutions";
        } else if (variables == equations) { // 1 solution
            echelonForm();
            reducedEchelonForm();
            double[] result = matrix.getColumn(matrix.getColSize() - 1);
            this.message = "The solution is: " + Arrays.toString(result);
            return result;
        } else { // infinite solutions
            this.message = "Infinitely many solutions";
        }
        return null;
    }

    public String getMessage() {
        return message;
    }

    private void sortZeros() {
        Arrays.sort(matrix.getRows());

    }

    private void isNoSolution() {
        for (Row row : matrix.getRows()) {
            if (row.getPriority() == variables) {
                this.isSolution = false;
                return;
            }
        }
        this.isSolution = true;
    }

    private void removeNullRows() {
        for (Row row : matrix.getRows()) {
            if (row.getPriority() == variables + 1) {
                matrix.deleteLastRow();
            }
        }
        this.equations = matrix.getRowSize();
    }


    private void reducedEchelonForm() {
        int size = Math.min(matrix.getRowSize(), matrix.getColSize());
        for (int j = 1; j < size; j++) {
            while (!matrix.isColumnUpperNull(j, j)) {
                for (int i = 0; i < j; i++) {
                    double factor = matrix.getElement(i, j);
                    if (!Utility.equals(factor, 0.0)) {
                        Row row = new Row(matrix.getRows()[j].getElements().clone());
                        row.multiply(factor);
                        matrix.subtract(i, row);
                    }
                }
            }
        }
    }

    private void echelonForm() {
        int size = Math.min(matrix.getRowSize(), matrix.getColSize());
        for (int j = 0; j < size; j++) {
            if (!Utility.equals(matrix.getElement(j, j), 0.0)) {
                matrix.multiply(j, 1.0 / matrix.getElement(j, j));
            }
            while (!matrix.isColumnBelowNull(j, j)) {
                for (int i = j + 1; i < matrix.getRowSize(); i++) {
                    double factor = matrix.getElement(i, j);
                    if (!Utility.equals(factor, 0.0)) {
                        Row row = new Row(matrix.getRows()[j].getElements().clone());
                        row.multiply(factor);
                        matrix.subtract(i, row);
                    }
                }
            }
        }
    }
}
