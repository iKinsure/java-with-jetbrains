package org.ikinsure.bullscows.range;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ASCIIRangeTest {

    @ParameterizedTest
    @MethodSource("getProvider")
    void shouldGet(char start, char end, int[] expect) {
        Range range = new ASCIIRange(start, end);
        int[] actual = range.get();
        assertArrayEquals(expect, actual);
    }

    private static Stream<Arguments> getProvider() {
        return Stream.of(
                Arguments.of('a', 'e', new int[]{'a', 'b', 'c', 'd', 'e'}),
                Arguments.of('0', '4', new int[]{'0', '1', '2', '3', '4'}),
                Arguments.of('g', 'g', new int[]{'g'})
        );
    }

    ////////////////////////////////////////////////////////////////////////////////////

    @ParameterizedTest
    @MethodSource("getWithLimitProvider")
    void shouldGetWithLimit(char start, char end, int[] expect, int limit) {
        Range range = new ASCIIRange(start, end);
        int[] actual = range.get(limit);
        assertArrayEquals(expect, actual);
    }

    private static Stream<Arguments> getWithLimitProvider() {
        return Stream.of(
                Arguments.of('a', 'z', new int[]{'a', 'b', 'c', 'd', 'e'}, 5),
                Arguments.of('0', '9', new int[]{'0', '1', '2', '3', '4'}, 5),
                Arguments.of('0', '9', new int[]{'0'}, 1),
                Arguments.of('0', '3', new int[]{'0', '1', '2', '3'}, 99)
        );
    }

    ////////////////////////////////////////////////////////////////////////////////////

    @ParameterizedTest
    @MethodSource("joinRangeProvider")
    void shouldJoinRange(char start, char end, String expect) {
        Range range = new ASCIIRange(start, end);
        String actual = range.joinedRange();
        assertEquals(expect, actual);
    }
    private static Stream<Arguments> joinRangeProvider() {
        return Stream.of(
                Arguments.of('a', 'z', "(a-z)"),
                Arguments.of('a', 'a', "(a-a)"),
                Arguments.of('0', '9', "(0-9)")
        );
    }

    ////////////////////////////////////////////////////////////////////////////////////

    @ParameterizedTest
    @MethodSource("joinRangeWithLimitProvider")
    void shouldJoinRangeWithLimit(char start, char end, String expect, int limit) {
        Range range = new ASCIIRange(start, end);
        String actual = range.joinedRange(limit);
        assertEquals(expect, actual);
    }

    private static Stream<Arguments> joinRangeWithLimitProvider() {
        return Stream.of(
                Arguments.of('a', 'z', "(a-e)", 5),
                Arguments.of('a', 'z', "(a-a)", 1),
                Arguments.of('0', '9', "(0-4)", 5),
                Arguments.of('a', 'z', "(a-z)", 99)
        );
    }

    ////////////////////////////////////////////////////////////////////////////////////

    @Test
    void shouldThrowInvalidArgument() {
        Exception exc = assertThrows(IllegalArgumentException.class, () -> new ASCIIRange('z', 'a'));
        assertEquals("start index cannot be greater than end", exc.getMessage());
    }

}
