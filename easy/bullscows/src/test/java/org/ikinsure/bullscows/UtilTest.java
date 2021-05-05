package org.ikinsure.bullscows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilTest {

    @ParameterizedTest
    @MethodSource("provider")
    void shouldGrade(int bulls, int cows, String expect) {
        String actual = Util.grade(bulls, cows);
        assertEquals(expect, actual);
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of(0, 0, "Grade: None."),
                Arguments.of(1, 0, "Grade: 1 bull."),
                Arguments.of(0, 1, "Grade: 1 cow."),
                Arguments.of(3, 0, "Grade: 3 bulls."),
                Arguments.of(0, 2, "Grade: 2 cows."),
                Arguments.of(4, 5, "Grade: 4 bulls and 5 cows."),
                Arguments.of(4, 1, "Grade: 4 bulls and 1 cow."),
                Arguments.of(1, 3, "Grade: 1 bull and 3 cows.")
        );
    }

}
