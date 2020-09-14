package processor;

public class MatrixManager {

    public static Matrix add(Matrix m1, Matrix m2) {
        if (m1.getRows() == m2.getRows() && m1.getColumns() == m2.getColumns()) {
            Matrix result = new Matrix(m1.getRows(), m2.getColumns());
            result.iterate((r, c) -> result.set(r, c, m1.get(r, c) + m2.get(r, c)));
            return result;
        }
        return null;
    }

    public static Matrix scalarMultiply(Matrix m, int multiplier) {
        Matrix result = new Matrix(m.getRows(), m.getColumns());
        result.iterate((r, c) -> result.set(r, c, m.get(r, c) * multiplier));
        return result;
    }

    public static Matrix multiply(Matrix m1, Matrix m2) {
        if (m1.getColumns() == m2.getRows()) {
            Matrix result = new Matrix(m1.getRows(), m2.getColumns());
            result.iterate((r, c) -> {
                double sum = 0.0;
                for (int i = 0; i < m1.getColumns(); i++) {
                    sum += m1.get(r, i) * m2.get(i, c);
                }
                result.set(r, c, sum);
            });
            return result;
        }
        return null;
    }
}
