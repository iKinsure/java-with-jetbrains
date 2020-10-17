package com.ikinsure.hyperskill.medium.tictactoe;

/**
 * All possible states of Tic-Tac-Toe game
 */
public enum State {
    START("Game is starting"),
    EXIT("Game is closing"),
    PLAYING("Game not finished"),
    DRAW("Draw"),
    X_WIN("X wins"),
    O_WIN("O wins");

    private final String description;
    State(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
