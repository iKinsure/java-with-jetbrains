package org.ikinsure.bullscows.range;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ASCIIRangeExtendedTest {

    @ParameterizedTest
    @MethodSource("getProvider")
    void shouldGet(List<ASCIIRange> ranges, int[] expect) {
        Range range = new ASCIIRangeExtended(ranges);
        int[] actual = range.get();
        assertArrayEquals(expect, actual);
    }

    private static Stream<Arguments> getProvider() {
        return Stream.of(
                Arguments.of(List.of(
                        new ASCIIRange('a', 'c'),
                        new ASCIIRange('0', '2')),
                        new int[]{'0', '1', '2', 'a', 'b', 'c'}),
                Arguments.of(List.of(
                        new ASCIIRange('e', 'f'),
                        new ASCIIRange('a', 'b'),
                        new ASCIIRange('0', '1')),
                        new int[]{'0', '1', 'a', 'b', 'e', 'f'})
        );
    }

    ////////////////////////////////////////////////////////////////////////////////////

    @ParameterizedTest
    @MethodSource("getWithLimitProvider")
    void shouldGetWithLimit(List<ASCIIRange> ranges, int[] expect, int limit) {
        Range range = new ASCIIRangeExtended(ranges);
        int[] actual = range.get(limit);
        assertArrayEquals(expect, actual);
    }

    private static Stream<Arguments> getWithLimitProvider() {
        return Stream.of(
                Arguments.of(List.of(
                        new ASCIIRange('a', 'z'),
                        new ASCIIRange('0', '9')),
                        new int[]{'0', '1', '2', '3', '4'}, 5),
                Arguments.of(List.of(
                        new ASCIIRange('0', '1'),
                        new ASCIIRange('a', 'b'),
                        new ASCIIRange('e', 'z')),
                        new int[]{'0', '1', 'a', 'b', 'e', 'f', 'g'}, 7),
                Arguments.of(List.of(
                        new ASCIIRange('0', '2'),
                        new ASCIIRange('a', 'c')),
                        new int[]{'0', '1', '2', 'a', 'b', 'c'}, 99)
        );
    }

    ////////////////////////////////////////////////////////////////////////////////////

    @ParameterizedTest
    @MethodSource("joinRangeProvider")
    void shouldJoinRange(List<ASCIIRange> ranges, String expect) {
        Range range = new ASCIIRangeExtended(ranges);
        String actual = range.joinedRange();
        assertEquals(expect, actual);
    }

    private static Stream<Arguments> joinRangeProvider() {
        return Stream.of(
                Arguments.of(List.of(
                        new ASCIIRange('a', 'z'),
                        new ASCIIRange('0', '9')),
                        "(0-9, a-z)"),
                Arguments.of(List.of(
                        new ASCIIRange('a', 'z')),
                        "(a-z)"),
                Arguments.of(List.of(
                        new ASCIIRange('a', 'z'),
                        new ASCIIRange('A', 'Z'),
                        new ASCIIRange('0', '9')),
                        "(0-9, A-Z, a-z)")
        );
    }

    ////////////////////////////////////////////////////////////////////////////////////

    @ParameterizedTest
    @MethodSource("joinRangeWithLimitProvider")
    void shouldJoinRangeWithLimit(List<ASCIIRange> ranges, String expect, int limit) {
        Range range = new ASCIIRangeExtended(ranges);
        String actual = range.joinedRange(limit);
        assertEquals(expect, actual);
    }

    private static Stream<Arguments> joinRangeWithLimitProvider() {
        return Stream.of(
                Arguments.of(List.of(
                        new ASCIIRange('a', 'z'),
                        new ASCIIRange('0', '9')),
                        "(0-9, a-a)", 11),
                Arguments.of(List.of(
                        new ASCIIRange('a', 'z'),
                        new ASCIIRange('0', '9')),
                        "(0-9)", 10),
                Arguments.of(List.of(
                        new ASCIIRange('a', 'z'),
                        new ASCIIRange('0', '9')),
                        "(0-9, a-e)", 15),
                Arguments.of(List.of(
                        new ASCIIRange('a', 'z')),
                        "(a-e)", 5),
                Arguments.of(List.of(
                        new ASCIIRange('a', 'z'),
                        new ASCIIRange('A', 'Z'),
                        new ASCIIRange('0', '9')),
                        "(0-9, A-Z, a-e)", 10 + 26 + 5),
                Arguments.of(List.of(
                        new ASCIIRange('a', 'z'),
                        new ASCIIRange('0', '9')),
                        "(0-9, a-z)", 99)
        );
    }

}
