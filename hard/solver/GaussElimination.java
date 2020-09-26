package hard.solver;

public class GaussElimination implements LinearEquationAlgorithm<Matrix> {

    @Override
    public double[] solve(Matrix matrix) {

        for (int j = 0; j < matrix.getColumns(); j++) {
            while (!matrix.isColumnBelowNull(j + 1, j)) {
                for (int i = 0; i < matrix.getRows(); i++) {
                    if (matrix.getElement(i, j) == 0.0) {

                    }
                }
            }
        }

        return new double[0];
    }
}
