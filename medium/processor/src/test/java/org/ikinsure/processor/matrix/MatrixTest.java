package org.ikinsure.processor.matrix;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {

    @ParameterizedTest
    @CsvSource({"2,2,true", "2,3,false", "1,1,true", "3,6,false", "5,5,true"})
    void shouldBeSquare(int rows, int columns, boolean expect) {
        boolean actual = new Matrix(rows, columns).isSquare();
        assertEquals(expect, actual, () -> "Matrix should " + (expect ? "" : "not ") + "be square");
    }

    @Test
    void shouldApplyAsDouble() {
        Matrix matrix = new Matrix(1, 3, (i, j) -> 2.0);
        matrix.set(0, 1, 3.0);
        List<Double> expect = List.of(
                2.0, 3.0, 2.0
        );
        List<Double> actual = List.of(
                matrix.get(0, 0), matrix.get(0, 1), matrix.get(0, 2)
        );
        assertEquals(expect, actual);
    }

    @Test
    void shouldThrowException1() {
        Exception exc = assertThrows(
                IllegalArgumentException.class,
                () -> new Matrix(0, 1)
        );
        String expect = "Invalid matrix dimensions";
        assertEquals(expect, exc.getMessage());
    }

    @Test
    void shouldThrowException2() {
        Exception exc = assertThrows(
                IllegalArgumentException.class,
                () -> new Matrix(1, 0, (i, j) -> 1.0)
        );
        String expect = "Invalid matrix dimensions";
        assertEquals(expect, exc.getMessage());
    }

    @Test
    void shouldIterateAndShow() {
        Matrix matrix = new Matrix(2, 3);
        AtomicInteger counter = new AtomicInteger(1);
        matrix.iterate((i, j) -> matrix.set(i, j, counter.getAndIncrement()));
        String actual = matrix.show();
        String expect = "1.0 2.0 3.0\n" + "4.0 5.0 6.0";
        assertEquals(expect, actual);
    }
}
