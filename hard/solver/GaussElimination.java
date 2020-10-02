package hard.solver;

import java.util.Arrays;

public class GaussElimination implements LinearEquationAlgorithm<Matrix, Complex> {

    private String message;

    @Override
    public Complex[] solve(Matrix m) {

        m.sort();
        m.echelonForm();
        m.reducedRowEchelonForm();

//        m.sort();
//        m.echelonForm();
//        m.reducedRowEchelonForm();

        final int equations = m.getRowsCounter() - m.zeroRowsCounter();
        final int variables = m.getColsCounter() - 1;

        if (m.checkNoSolution()) {
            this.message = "No solutions";
        } else if (equations == variables) {
            Complex[] result = Arrays.copyOf(
                    m.getNewColMultiplied(m.getColsCounter() - 1, 1.0), equations);
            this.message = "The solution is: " + Arrays.toString(result);
            return result;
        } else if (equations < variables) {
            this.message = "Infinitely many solutions";
        } else {
            this.message = "No solutions";
        }
        return null;
    }

    public String getMessage() {
        return message;
    }
}
