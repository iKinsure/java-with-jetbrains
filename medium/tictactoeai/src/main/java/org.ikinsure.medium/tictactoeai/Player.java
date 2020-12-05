package org.ikinsure.medium.tictactoeai;

/**
 * The class represents player
 */
public class Player {

    public enum Mode {
        EASY, MEDIUM, HARD, USER;
    }

    public enum Side {
        X('X'), O('O');
        public char sign;
        Side(char sign) {
            this.sign = sign;
        }
    }

    private Mode mode;
    private Side side;

    public Player(Mode mode, Side side) {
        this.mode = mode;
        this.side = side;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public Side getSide() {
        return side;
    }

    public Side getOtherSide() {
        return (this.side == Side.X) ? Side.O : Side.X;
    }
}
