package hard.solver;

public class GaussElimination implements LinearEquationAlgorithm<Matrix> {

    @Override
    public double[] solve(Matrix matrix) {

        for (int j = 0; j < matrix.getColumns(); j++) {
            matrix.multiply(j, 1.0 / matrix.getElement(j, j));
            while (!matrix.isColumnBelowNull(j + 1, j)) {
                for (int i = j + 1; i < matrix.getRows(); i++) {
                    double factor = matrix.getElement(i, j);
                    if (!Utility.equals(factor, 0.0)) {
                        matrix.subtract(0, i);
                    }
                }
            }
        }

        return new double[0];
    }

}
