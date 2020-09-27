package hard.solver;

import java.util.Arrays;

public class GaussElimination implements LinearEquationAlgorithm<Matrix> {

    public static String solution = null;

    @Override
    public double[] solve(Matrix matrix) {
        sortToTriangle(matrix);
        deleteNullRows(matrix);
        if (isThereSolution(matrix)) {
            echelonForm(matrix);
            deleteNullRows(matrix);
            reducedEchelonForm(matrix);
            deleteNullRows(matrix);
            return matrix.getColumn(matrix.getColSize() - 1);
        } else {
            return null;
        }
    }

    private boolean isThereSolution(Matrix matrix) {
        if (matrix.getRowSize() < matrix.getColSize() - 1) {
            solution = "Infinitely many solutions";
            return false;
        }
        for (int i = 0; i < matrix.getRowSize(); i++) {
            int size = matrix.getColSize();
            if (matrix.getRows()[i].getPriority() == size - 1) {
                if (!Utility.equals(matrix.getElement(i, size - 1), 0.0)) {
                    solution = "No solutions";
                    return false;
                }
            }
        }
        return true;
    }

    private void deleteNullRows(Matrix matrix) {
        for (int i = matrix.getRowSize() - 1; i >= 0 ; i--) {
            if (matrix.getRows()[i].getPriority() == matrix.getRows()[i].getSize()) {
                matrix.deleteLastRow();
            }
        }
    }

    private void sortToTriangle(Matrix matrix) {
        Arrays.sort(matrix.getRows());
    }

    private void reducedEchelonForm(Matrix matrix) {
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

    private void echelonForm(Matrix matrix) {
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
