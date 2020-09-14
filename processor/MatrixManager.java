package processor;

public class MatrixManager {

    public static Matrix add(Matrix m1, Matrix m2) {
        if (m1.getRows() == m2.getRows() && m1.getColumns() == m2.getColumns()) {
            return new Matrix(m1.getRows(), m2.getColumns(), (r, c) -> m1.get(r, c) + m2.get(r, c));
        }
        return null;
    }

    public static Matrix scalarMultiply(Matrix m, int multiplier) {
        return new Matrix(m.getRows(), m.getColumns(), (r, c) -> m.get(r, c) * multiplier);
    }

    public static Matrix multiply(Matrix m1, Matrix m2) {
        if (m1.getColumns() == m2.getRows()) {
            return new Matrix(m1.getRows(), m2.getColumns(), (r, c) -> {
                double sum = 0.0;
                for (int i = 0; i < m1.getColumns(); i++) {
                    sum += m1.get(r, i) * m2.get(i, c);
                }
                return sum;
            });
        }
        return null;
    }

    public static Matrix transposeOverMainDiagonal(Matrix m) {
        return new Matrix(m.getColumns(), m.getRows(), (r, c) -> m.get(c, r));
    }

    public static Matrix transposeOverSideDiagonal(Matrix m) {
        return new Matrix(m.getColumns(), m.getRows(), (r, c) -> m.get(m.getColumns() - c - 1, m.getRows() - r - 1));
    }

    public static Matrix transposeByVerticalLine(Matrix m) {
        return new Matrix(m.getRows(), m.getColumns(), (r, c) -> m.get(r, m.getColumns() - c - 1));
    }

    public static Matrix transposeByHorizontalLine(Matrix m) {
        return new Matrix(m.getRows(), m.getColumns(), (r, c) -> m.get(m.getRows() - r - 1, c));
    }
}
