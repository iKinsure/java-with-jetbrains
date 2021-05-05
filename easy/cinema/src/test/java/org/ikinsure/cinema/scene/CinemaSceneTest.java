package org.ikinsure.cinema.scene;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CinemaSceneTest {

    @ParameterizedTest
    @MethodSource("generateSchemeProvider")
    void shouldGenerateScheme(int rows, int columns, String[] expect) {
        Scene2D scene = new CinemaScene(rows, columns);
        String actual = scene.scheme();
        assertEquals(String.join("\n", expect), actual);
    }

    private static Stream<Arguments> generateSchemeProvider() {
        return Stream.of(
                Arguments.of(3, 2, new String[]{
                        "  1 2",
                        "1 S S",
                        "2 S S",
                        "3 S S",
                }),
                Arguments.of(10, 2, new String[]{
                        "   1 2",
                        " 1 S S",
                        " 2 S S",
                        " 3 S S",
                        " 4 S S",
                        " 5 S S",
                        " 6 S S",
                        " 7 S S",
                        " 8 S S",
                        " 9 S S",
                        "10 S S",
                }),
                Arguments.of(3, 10, new String[]{
                        "   1  2  3  4  5  6  7  8  9 10",
                        "1  S  S  S  S  S  S  S  S  S  S",
                        "2  S  S  S  S  S  S  S  S  S  S",
                        "3  S  S  S  S  S  S  S  S  S  S",
                })
        );
    }

    ////////////////////////////////////////////////////////////////////////////////

    @Test
    void shouldGetPlaces() {
        Scene2D scene = new CinemaScene(4, 10);
        int actual = scene.getPlaces();
        assertEquals(40, actual);
    }

    @Test
    void shouldSetPlace() {
        Scene2D scene = new CinemaScene(4, 10);
        scene.setPlace(3, 8);
        Place actual = scene.getPlace(3, 8);
        assertEquals(Place.BUSY, actual);
    }

}
