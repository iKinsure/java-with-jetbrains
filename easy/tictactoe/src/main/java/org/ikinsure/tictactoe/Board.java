package org.ikinsure.tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.ikinsure.tictactoe.Main.*;

public class Board {

    private final Field[][] matrix;
    private final Checker checker;

    public Board(int size) {
        this.matrix = new Field[size][size];
        this.checker = new Checker(matrix);
        Arrays.stream(matrix).forEach(row -> Arrays.fill(row, Field.EMPTY));
    }

    public void set(int i, int j, Field field) {
        matrix[i][j] = field;
    }

    public Field get(int i, int j) {
        return matrix[i][j];
    }

    public int size() {
        return matrix.length;
    }

    public State checkState(Field field) {
        if (checker.isWinner(field)) {
            return field == Field.X ? State.WIN_X : State.WIN_O;
        } else if (checker.isDraw()) {
            return State.DRAW;
        }
        return State.PLAY;
    }

    public String getBoard() {
        final int size = 2 * matrix.length + 3;
        String board = Arrays.stream(matrix)
                .map(row -> SEPARATOR + DELIMITER + Arrays.stream(row)
                        .map(Field::getSign)
                        .collect(Collectors.joining(DELIMITER)) + DELIMITER + SEPARATOR)
                .collect(Collectors.joining("\n"));
        return BORDER.repeat(size) + "\n" + board + "\n" + BORDER.repeat(size);
    }

}
