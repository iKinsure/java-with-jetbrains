package org.ikinsure.tictactoe;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Scanner;
import java.util.stream.Stream;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOutNormalized;
import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class GameTest {

    @ParameterizedTest
    @MethodSource("generator")
    void shouldRun(String[] expected, String[] inputs) throws Exception {
        withTextFromSystemIn(inputs).execute(() -> {
            Game game = new Game(new Scanner(System.in), 2);
            String[] actual = tapSystemOutNormalized(game::run).split("\n");
            assertArrayEquals(actual, expected);
        });
    }

    private static Stream<Arguments> generator() {
        return Stream.of(
                Arguments.of(
                        new String[]{
                                "-------",
                                "| _ _ |",
                                "| _ _ |",
                                "-------",
                                "Enter the coordinates: " +
                                "-------",
                                "| X _ |",
                                "| _ _ |",
                                "-------",
                                "Enter the coordinates: " +
                                "-------",
                                "| X O |",
                                "| _ _ |",
                                "-------",
                                "Enter the coordinates: " +
                                "You should enter numbers!",
                                "Enter the coordinates: " +
                                "Coordinates should be from 1 to 2!",
                                "Enter the coordinates: " +
                                "This cell is occupied! Choose another one!",
                                "Enter the coordinates: " +
                                "-------",
                                "| X O |",
                                "| _ X |",
                                "-------",
                                "X wins"
                        },
                        new String[]{"1 1", "2 1", "hello", "3 1", "2 1", "2 2"}
                )
        );
    }
}
