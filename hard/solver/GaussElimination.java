package hard.solver;

public class GaussElimination implements LinearEquationAlgorithm<Matrix> {

    @Override
    public double[] solve(Matrix matrix) {
        echelonForm(matrix);
        reducedEchelonForm(matrix);
        return matrix.getColumn(matrix.getColSize() - 1);
    }

    private void reducedEchelonForm(Matrix matrix) {
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

    private void echelonForm(Matrix matrix) {
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
