package org.ikinsure.processor.matrix;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixManagerTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/multiply.csv", lineSeparator = ";")
    void shouldMultiply(
            @ConvertWith(MatrixConverter.class) Matrix m1,
            @ConvertWith(MatrixConverter.class) Matrix m2,
            @ConvertWith(MatrixConverter.class) Matrix expect) {
        Matrix actual = MatrixManager.multiply(m1, m2);
        assertEquals(expect, actual, "Matrices not equal");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/add.csv", lineSeparator = ";")
    void shouldAdd(
            @ConvertWith(MatrixConverter.class) Matrix m1,
            @ConvertWith(MatrixConverter.class) Matrix m2,
            @ConvertWith(MatrixConverter.class) Matrix expect) {
        Matrix actual = MatrixManager.add(m1, m2);
        assertEquals(expect, actual, "Matrices not equal");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/scalar_multiply.csv", lineSeparator = ";")
    void shouldScalarMultiply(
            @ConvertWith(MatrixConverter.class) Matrix m,
            double multiplier,
            @ConvertWith(MatrixConverter.class) Matrix expect) {
        Matrix actual = MatrixManager.scalarMultiply(m, multiplier);
        assertEquals(expect, actual, "Matrices not equal");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/transpose_over_main_diagonal.csv", lineSeparator = ";")
    void shouldTransposeOverMainDiagonal(
            @ConvertWith(MatrixConverter.class) Matrix m,
            @ConvertWith(MatrixConverter.class) Matrix expect) {
        Matrix actual = MatrixManager.transposeOverMainDiagonal(m);
        assertEquals(expect, actual, "Matrices not equal");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/transpose_over_side_diagonal.csv", lineSeparator = ";")
    void shouldTransposeOverSideDiagonal(
            @ConvertWith(MatrixConverter.class) Matrix m,
            @ConvertWith(MatrixConverter.class) Matrix expect) {
        Matrix actual = MatrixManager.transposeOverSideDiagonal(m);
        assertEquals(expect, actual, "Matrices not equal");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/transpose_by_vertical_line.csv", lineSeparator = ";")
    void shouldTransposeByVerticalLine(
            @ConvertWith(MatrixConverter.class) Matrix m,
            @ConvertWith(MatrixConverter.class) Matrix expect) {
        Matrix actual = MatrixManager.transposeByVerticalLine(m);
        assertEquals(expect, actual, "Matrices not equal");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/transpose_by_horizontal_line.csv", lineSeparator = ";")
    void shouldTransposeByHorizontalLine(
            @ConvertWith(MatrixConverter.class) Matrix m,
            @ConvertWith(MatrixConverter.class) Matrix expect) {
        Matrix actual = MatrixManager.transposeByHorizontalLine(m);
        assertEquals(expect, actual, "Matrices not equal");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/remove_row.csv", lineSeparator = ";")
    void shouldRemoveRow(
            @ConvertWith(MatrixConverter.class) Matrix m,
            int index,
            @ConvertWith(MatrixConverter.class) Matrix expect) {
        Matrix actual = MatrixManager.removeRow(m, index);
        assertEquals(expect, actual, "Matrices not equal");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 3, 10})
    void shouldRemoveRowThrowException(int index) {
        Exception exc = assertThrows(
                IllegalArgumentException.class,
                () -> MatrixManager.removeRow(new Matrix(3, 3), index)
        );
        String expect = "Invalid index " + index;
        assertEquals(expect, exc.getMessage());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/remove_column.csv", lineSeparator = ";")
    void shouldRemoveColumn(
            @ConvertWith(MatrixConverter.class) Matrix m,
            int index,
            @ConvertWith(MatrixConverter.class) Matrix expect) {
        Matrix actual = MatrixManager.removeColumn(m, index);
        assertEquals(expect, actual, "Matrices not equal");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 3, 10})
    void shouldRemoveColumnThrowException(int index) {
        Exception exc = assertThrows(
                IllegalArgumentException.class,
                () -> MatrixManager.removeColumn(new Matrix(3, 3), index)
        );
        String expect = "Invalid index " + index;
        assertEquals(expect, exc.getMessage());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inverse.csv", lineSeparator = ";")
    void shouldInverse(
            @ConvertWith(MatrixConverter.class) Matrix m,
            @ConvertWith(MatrixConverter.class) Matrix expect) {
        Matrix actual = MatrixManager.inverse(m);
        assertEquals(expect, actual, "Matrices not equal");
    }

    @Test
    void shouldInverseThrowException() {
        Exception exc = assertThrows(
                IllegalArgumentException.class,
                () -> MatrixManager.inverse(new Matrix(3, 3))
        );
        String expect = "Matrix determinant is equal to 0";
        assertEquals(expect, exc.getMessage());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/determinant.csv", lineSeparator = ";")
    void shouldGetDeterminant(
            @ConvertWith(MatrixConverter.class) Matrix m,
            double expect) {
        double actual = MatrixManager.determinant(m);
        assertEquals(expect, actual, "Values not equal");
    }

}
