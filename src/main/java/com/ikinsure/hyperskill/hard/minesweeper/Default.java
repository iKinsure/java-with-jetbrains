package com.ikinsure.hyperskill.hard.minesweeper;

public class Default extends Field {

    private int value;

    public Default(Mode mode, int value) {
        super(mode);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (this.getMode() == Mode.HIDDEN) {
            return ".";
        } else if (this.getMode() == Mode.SHOWED) {
            return value == 0 ? "/" : String.valueOf(value);
        } else if (this.getMode() == Mode.MARKED) {
            return "*";
        }
        return "e";
    }
}
