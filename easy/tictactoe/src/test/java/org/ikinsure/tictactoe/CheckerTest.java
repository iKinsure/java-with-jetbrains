package org.ikinsure.tictactoe;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.ikinsure.tictactoe.Field.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckerTest {

    @ParameterizedTest
    @MethodSource("drawGenerator")
    void shouldTestDraw(Field[][] fields, boolean expected) {
        Checker checker = new Checker(fields);
        assertEquals(checker.isDraw(), expected);
    }

    @ParameterizedTest
    @MethodSource("winnerGenerator")
    void shouldTestWinner(Field field, Field[][] fields, boolean expected) {
        Checker checker = new Checker(fields);
        assertEquals(checker.isWinner(field), expected);
    }


    private static Stream<Arguments> drawGenerator() {
        return Stream.of(
              Arguments.of(new Field[][]{
                              {X, O, X},
                              {X, O, X},
                              {O, X, O}}, true),
              Arguments.of(new Field[][]{
                              {X, X, X},
                              {O, O, O},
                              {O, X, O}}, true),
              Arguments.of(new Field[][]{
                              {EMPTY, X, X},
                              {O, EMPTY, O},
                              {O, X, O}}, false),
              Arguments.of(new Field[][]{
                              {EMPTY, EMPTY, EMPTY},
                              {EMPTY, EMPTY, EMPTY},
                              {EMPTY, EMPTY, EMPTY}}, false)
        );
    }

    private static Stream<Arguments> winnerGenerator() {
        return Stream.of(
                Arguments.of(X, new Field[][]{
                                {X, O, X},
                                {X, O, X},
                                {O, X, O}}, false),
                Arguments.of(X, new Field[][]{
                                {X, O, X},
                                {X, X, X},
                                {O, O, O}}, true),
                Arguments.of(O, new Field[][]{
                                {X, X, O},
                                {X, X, O},
                                {O, EMPTY, O}}, true),
                Arguments.of(O, new Field[][]{
                                {X, EMPTY, O},
                                {EMPTY, O, X},
                                {O, EMPTY, X}}, true),
                Arguments.of(X, new Field[][]{
                                {X, EMPTY, O},
                                {EMPTY, X, O},
                                {O, EMPTY, X}}, true)
        );
    }
}
