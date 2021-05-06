package org.ikinsure.processor.matrix;

import java.util.stream.IntStream;

/**
 * Functional matrix manager
 */
public class MatrixManager {

    private static final double THRESHOLD = 0.0001;

    /**
     * Calculate multiplication of two matrices using cross product
     * @param m1 first data matrix
     * @param m2 second data matrix
     * @return new result matrix
     */
    public static Matrix multiply(Matrix m1, Matrix m2) {
        if (m1.columns() != m2.rows()) {
            throw new IllegalArgumentException("Incompatible dimensions");
        }
        return new Matrix(
                m1.rows(),
                m2.columns(),
                (i, j) -> IntStream.range(0, m1.columns())
                        .mapToDouble(k -> m1.get(i, k) * m2.get(k, j))
                        .sum()
        );
    }

    public static Matrix add(Matrix m1, Matrix m2) {
        if (m1.rows() != m2.rows() || m1.columns() != m2.columns()) {
            throw new IllegalArgumentException("Incompatible dimensions");
        }
        return new Matrix(
                m1.rows(),
                m2.columns(),
                (i, j) -> m1.get(i, j) + m2.get(i, j)
        );
    }

    public static Matrix scalarMultiply(Matrix m, double multiplier) {
        return new Matrix(
                m.rows(),
                m.columns(),
                (i, j) -> m.get(i, j) * multiplier
        );
    }

    public static Matrix transposeOverMainDiagonal(Matrix m) {
        return new Matrix(
                m.columns(),
                m.rows(),
                (i, j) -> m.get(j, i)
        );
    }

    public static Matrix transposeOverSideDiagonal(Matrix m) {
        return new Matrix(
                m.columns(),
                m.rows(),
                (i, j) -> m.get(m.columns() - j - 1, m.rows() - i - 1)
        );
    }

    public static Matrix transposeByVerticalLine(Matrix m) {
        return new Matrix(
                m.rows(),
                m.columns(),
                (i, j) -> m.get(i, m.columns() - j - 1)
        );
    }

    public static Matrix transposeByHorizontalLine(Matrix m) {
        return new Matrix(
                m.rows(),
                m.columns(),
                (i, j) -> m.get(m.rows() - i - 1, j)
        );
    }

    public static Matrix removeRow(Matrix m, int index) {
        if (index < 0 || index >= m.rows()) {
            throw new IllegalArgumentException("Invalid index " + index);
        }
        return new Matrix(
                m.rows() - 1,
                m.columns(),
                (i, j) -> m.get(i >= index ? i + 1 : i, j)
        );
    }

    public static Matrix removeColumn(Matrix m, int index) {
        if (index < 0 || index >= m.columns()) {
            throw new IllegalArgumentException("Invalid index " + index);
        }
        return new Matrix(
                m.rows(),
                m.columns() - 1,
                (i, j) -> m.get(i, j >= index ? j + 1 : j));
    }

    /**
     * Calculate an inverse matrix using adjoint
     * @param m data matrix
     * @return new inverse matrix
     */
    public static Matrix inverse(Matrix m) {
        double determinant = determinant(m);
        if (Math.abs(determinant - 0.0) < THRESHOLD) {
            throw new IllegalArgumentException("Matrix determinant is equal to 0");
        }
        double inverseDeterminant = 1.0 / determinant;
        return transposeOverMainDiagonal(new Matrix(
                m.rows(),
                m.columns(),
                (i, j) -> cofactor(m, i, j) * inverseDeterminant));
    }

    /**
     * Calculate a cofactor in matrix
     * @param m data matrix
     * @param i row number
     * @param j column number
     * @return cofactor = -1^(i+j) * minor
     */
    public static double cofactor(Matrix m, int i, int j) {
        return Math.pow(-1, i + j) * determinantRecursion(removeColumn(removeRow(m, i), j));
    }

    public static double determinant(Matrix m) {
        if (!m.isSquare()) {
            throw new IllegalArgumentException("Matrix is not square");
        }
        return determinantRecursion(m);
    }

    /**
     * Calculate determinant of given matrix by recursion using adjoint
     * @param m data matrix
     * @return determinant
     */
    private static double determinantRecursion(Matrix m) {
        if (m.rows() == 1) {
            return m.get(0, 0);
        }
        return IntStream.range(0, m.rows())
                .mapToDouble(k -> cofactor(m, 0, k) * m.get(0, k))
                .sum();
    }

}
