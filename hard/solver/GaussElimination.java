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
        sortRows();
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

    private void sortColumns() {
        int size = Math.min(matrix.getColSize(), matrix.getRowSize());
        for (int i = 0; i < size; i++) {
            double el = matrix.getElement(i, i);
            if (Utility.equals(el, 0.0)) {
                if (matrix.isColumnBelowNull(i, i)) {
                    for (int j = i; j < matrix.getColSize() - 1; j++) {
                        double other = matrix.getElement(i, j);
                        if (!Utility.equals(other, 0.0)) {
                            matrix.swapColumn(i, j);
                            break;
                        }
                    }
                }
            }
        }
    }

    public String getMessage() {
        return message;
    }

    private void sortRows() {
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
        for (int j = 1; j < matrix.getRowSize(); j++) {
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
        for (int j = 0; j < matrix.getRowSize(); j++) {
            matrix.multiply(j, 1.0 / matrix.getElement(j, j));
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
