package org.ikinsure.bullscows;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    void shouldGetBulls() {
        Game game = Game.create(5, 15);
        String code = Arrays.stream(game.getCode())
                .mapToObj(c -> "" + (char) c)
                .collect(Collectors.joining());
        long actual = game.bulls(code.toCharArray());
        assertEquals(5, actual);
    }

    @Test
    void shouldGetCows() {
        Game game = Game.create(5, 15);
        String code = Arrays.stream(game.getCode())
                .mapToObj(c -> "" + (char) c)
                .collect(Collectors.joining());
        long actual = game.cows(code.toCharArray());
        assertEquals(actual, 0);
    }

    @Test
    void shouldPrepareMessage() {
        Game game = Game.create(5, 15);
        String actual = game.prepare();
        String expect = "The secret is prepared: ***** (0-9, a-e).";
        assertEquals(expect, actual);
    }

    @Test
    void shouldValidateCharsTrue() {
        Game game = Game.create(5, 15);
        boolean actual = game.validateChars("0e123");
        assertTrue(actual);
    }

    @Test
    void shouldValidateCharsFalse() {
        Game game = Game.create(5, 15);
        boolean actual = game.validateChars("0e12f");
        assertFalse(actual);
    }

    @Test
    void shouldThrowException1() {
        Exception exc = assertThrows(
                IllegalArgumentException.class,
                () -> Game.create(10, 9)
        );
        String expect = "Error: it's not possible to generate a code with a length of 10 with 9 unique symbols.";
        assertEquals(expect, exc.getMessage());
    }

    @Test
    void shouldThrowException2() {
        Exception exc = assertThrows(
                IllegalArgumentException.class,
                () -> Game.create(40, 40)
        );
        String expect = "Error: maximum number of possible symbols in the code is 36 (0-9, a-z).";
        assertEquals(expect, exc.getMessage());
    }
}
