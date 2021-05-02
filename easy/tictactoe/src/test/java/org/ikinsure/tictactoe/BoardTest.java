package org.ikinsure.tictactoe;

import org.junit.jupiter.api.Test;

import static org.ikinsure.tictactoe.Field.*;
import static org.ikinsure.tictactoe.Main.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    @Test
    void shouldGetBoard() {
        Board board = new Board(3);
        board.set(0, 0, X);
        board.set(0, 1, O);
        String expected = BORDER.repeat(9) + "\n" +
                SEPARATOR + DELIMITER + X.getSign() + DELIMITER + O.getSign() + DELIMITER + EMPTY.getSign() + DELIMITER + SEPARATOR + "\n" +
                SEPARATOR + DELIMITER + EMPTY.getSign() + DELIMITER + EMPTY.getSign() + DELIMITER + EMPTY.getSign() + DELIMITER + SEPARATOR + "\n" +
                SEPARATOR + DELIMITER + EMPTY.getSign() + DELIMITER + EMPTY.getSign() + DELIMITER + EMPTY.getSign() + DELIMITER + SEPARATOR + "\n" +
                BORDER.repeat(9);
        assertEquals(board.getBoard(), expected);
    }

    @Test
    void shouldStatePlay() {
        Board board = new Board(3);
        board.set(0, 0, X);
        board.set(0, 1, O);
        board.set(1, 1, X);
        assertEquals(board.checkState(X), State.PLAY);
    }

    @Test
    void shouldStateWinner() {
        Board board = new Board(2);
        board.set(0, 0, O);
        board.set(0, 1, O);
        board.set(1, 1, X);
        assertEquals(board.checkState(O), State.WIN_O);
    }

    @Test
    void shouldStateDraw() {
        Board board = new Board(3);
        board.set(0, 0, X);
        board.set(0, 1, X);
        board.set(0, 2, O);
        board.set(1, 0, O);
        board.set(1, 1, O);
        board.set(1, 2, X);
        board.set(2, 0, X);
        board.set(2, 1, X);
        board.set(2, 2, O);
        assertEquals(board.checkState(X), State.DRAW);
    }
}
