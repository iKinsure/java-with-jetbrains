package hard.solver;

public class GaussElimination implements LinearEquationAlgorithm<Matrix> {

    @Override
    public double[] solve(Matrix matrix) {
        matrix.prepare();
        matrix.check();
        matrix.echelonForm();
        matrix.reducedRowEchelonForm();
        return matrix.getNewColMultiplied(matrix.getColsCounter() - 1, 1.0);
    }
}
