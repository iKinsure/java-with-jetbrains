package com.ikinsure.hyperskill.hard.minesweeper;

public enum GameState {
    RUNNING("Its impossible!"),
    LOST("You stepped on a mine and failed!"),
    WON("Congratulations! You found all mines!");
    final String text;
    GameState(String text) {
        this.text = text;
    }
}
