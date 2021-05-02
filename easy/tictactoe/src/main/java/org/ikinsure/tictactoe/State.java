package org.ikinsure.tictactoe;

public enum State {
    PLAY("Game not finished"),
    DRAW("Draw"),
    WIN_X("X wins"),
    WIN_O("O wins");

    private final String msg;

    State(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

}
