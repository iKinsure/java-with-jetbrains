package org.ikinsure.processor;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOutNormalized;
import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ProcessorTest {

    @Test
    void shouldRun() throws Exception {

        String[] expect = {
                "1. Add matrices",
                "2. Multiply matrix by a constant",
                "3. Multiply matrices",
                "4. Transpose matrix",
                "5. Calculate a determinant",
                "6. Inverse matrix",
                "0. Exit",
                "Your choice: ",
                "Enter matrix size: ",
                "Enter matrix: ",
                "Enter matrix size: ",
                "Enter matrix: ",
                "7.0 7.0 7.0",
                "7.0 7.0 7.0",
                "1. Add matrices",
                "2. Multiply matrix by a constant",
                "3. Multiply matrices",
                "4. Transpose matrix",
                "5. Calculate a determinant",
                "6. Inverse matrix",
                "0. Exit",
                "Your choice: "
        };
        String[] inputs = {
                "1",
                "2 3",
                "1 2 3 4 5 6",
                "2 3",
                "6 5 4 3 2 1",
                "0"
        };
        withTextFromSystemIn(inputs).execute(() -> {
            Scanner scanner = new Scanner(System.in);
            String[] actual = tapSystemOutNormalized(() -> new Processor(new View(
                    System.out::println,
                    scanner::nextInt,
                    scanner::nextDouble
            )).run()).split("\n");
            assertArrayEquals(expect, actual);
        });

    }
}
