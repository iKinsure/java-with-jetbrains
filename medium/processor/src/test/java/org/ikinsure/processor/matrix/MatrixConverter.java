package org.ikinsure.processor.matrix;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import java.util.Arrays;

public class MatrixConverter implements ArgumentConverter {

    private static final String ELEM_SEPARATOR = " ";
    private static final String ROW_SEPARATOR = "\n";

    @Override
    public Object convert(Object obj, ParameterContext ctx) throws ArgumentConversionException {
        if (!(obj instanceof String)) {
            throw new IllegalArgumentException("The argument should be a string: " + obj);
        }

        double[][] array = Arrays.stream(((String) obj).split(ROW_SEPARATOR))
                .map(row -> Arrays.stream(row.split(ELEM_SEPARATOR))
                        .mapToDouble(Double::parseDouble)
                        .toArray())
                .toArray(double[][]::new);

        return new Matrix(array.length, array[0].length, (i, j) -> array[i][j]);
    }
}
