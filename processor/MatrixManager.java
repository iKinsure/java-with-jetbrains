package processor;

public class MatrixManager {

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

    public static Matrix add(Matrix m1, Matrix m2) {
        return (m1.getRows() == m2.getRows() && m1.getColumns() == m2.getColumns()) ?
                new Matrix(m1.getRows(), m2.getColumns(),
                        (r, c) -> m1.get(r, c) + m2.get(r, c)) : null;
    }

    public static Matrix scalarMultiply(Matrix m, int multiplier) {
        return new Matrix(m.getRows(), m.getColumns(),
                (r, c) -> m.get(r, c) * multiplier);
    }

    public static Matrix transposeOverMainDiagonal(Matrix m) {
        return new Matrix(m.getColumns(), m.getRows(),
                (r, c) -> m.get(c, r));
    }

    public static Matrix transposeOverSideDiagonal(Matrix m) {
        return new Matrix(m.getColumns(), m.getRows(),
                (r, c) -> m.get(m.getColumns() - c - 1, m.getRows() - r - 1));
    }

    public static Matrix transposeByVerticalLine(Matrix m) {
        return new Matrix(m.getRows(), m.getColumns(),
                (r, c) -> m.get(r, m.getColumns() - c - 1));
    }

    public static Matrix transposeByHorizontalLine(Matrix m) {
        return new Matrix(m.getRows(), m.getColumns(),
                (r, c) -> m.get(m.getRows() - r - 1, c));
    }

    public static Matrix removeRow(Matrix m, int index) {
        return new Matrix(m.getRows() - 1, m.getColumns(),
                (r, c) -> m.get(r >= index ? r + 1 : r, c));
    }

    public static Matrix removeColumn(Matrix m, int index) {
        return new Matrix(m.getRows(), m.getColumns() - 1,
                (r, c) -> m.get(r, c >= index ? c + 1 : c));
    }

    public static double determinant(Matrix m) {
        return m.getRows() == m.getColumns() ? determinantRecursion(m) : 0.0;
    }

    private static double determinantRecursion(Matrix m) {
        if (m.getRows() == 1) {
            return m.get(0, 0);
        }
        double sum = 0.0;
        for (int i = 0; i < m.getRows(); i++) {
            double minor = determinantRecursion(removeColumn(removeRow(m, 0), i));
            sum += Math.pow(-1, i) * m.get(0, i) * minor;
        }
        return sum;
    }
}
