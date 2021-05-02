package org.ikinsure.tictactoe;

public enum Field {
    X("X"),
    O("O"),
    EMPTY("_");

    private final String sign;

    Field(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}
