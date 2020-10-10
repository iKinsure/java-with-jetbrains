package medium.battleship;

import java.util.Arrays;

public class Board {

    public static final char FOG_OF_WAR = '~';
    public static final char OUR_SHIP = 'O';
    public static final char MISS = 'M';
    public static final char HIT = 'X';

    private final char[][] array;

    public Board(int size) {
        this.array = new char[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(array[i], FOG_OF_WAR);
        }

    }



    public void setPosition(String pos, char sign) {
        array[getY(pos)][getX(pos)] = sign;
    }

    public void setPosition(int x, int y, char sign) {
        array[y][x] = sign;
    }

    public char getPosition(String pos) {
        return array[getY(pos)][getX(pos)];
    }

    public char getPosition(int x, int y) {
        return array[y][x];
    }

    public int getX(String pos) {
        return Integer.parseInt(pos.substring(1)) - 1;
    }

    public int getY(String pos) {
        return pos.charAt(0) - 65;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(" ");
        for (int i = 0; i < array.length; i++) {
            builder.append(" ").append(i + 1);
        }
        builder.append("\n");
        for (int i = 0; i < array.length; i++) {
            builder.append((char)('A' + i));
            for (int j = 0; j < array.length; j++) {
                builder.append(" ").append(array[i][j]);
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
