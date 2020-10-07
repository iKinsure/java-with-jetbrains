package hard.minesweeper;

import java.util.ArrayDeque;
import java.util.Random;

public class Map {

    private final Field[][] array;
    private static final Random RANDOM =  new Random();

    public Map(final int size) {
        this.array = new Field[size][size];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = new Default(Mode.HIDDEN, 0);
            }
        }
    }

    public void placeMines(int mines) {
        for (int i = 0; i < mines; i++) {
            while (true) {
                final int X = RANDOM.nextInt(array.length);
                final int Y = RANDOM.nextInt(array.length);
                if (!(array[X][Y] instanceof Mine)) {
                    array[X][Y] = new Mine(Mode.HIDDEN);
                    break;
                }
            }
        }
    }

    public void placeDefaults() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] instanceof Default) {
                    ((Default) array[i][j]).setValue(getMinesCounter(i, j));
                }
            }
        }
    }

    private int getMinesCounter(int x, int y) {
        int counter = 0;
        for (Dir dir : Dir.values()) {
            final int X = dir.x + x;
            final int Y = dir.y + y;
            if (X >= 0 && X < array.length && Y >= 0 && Y < array.length) {
                if (array[X][Y] instanceof Mine) {
                    counter++;
                }
            }
        }
        return counter;
    }


    public void discover(int x, int y) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (Dir dir : Dir.values()) {
            final int X = dir.x + x;
            final int Y = dir.y + y;
            if (X >= 0 && X < array.length && Y >= 0 && Y < array.length) {
                if (isValueZero(X, Y) && array[X][Y].getMode() != Mode.MARKED) {

                }
            }
        }
    }

    public boolean isPosMode(int x, int y, Mode mode) {
        return array[x][y].getMode() == mode;
    }

    public void markAsFree(int x, int y) {
        array[x][y].setMode(Mode.SHOWED);
    }

    public void markAsMine(int x, int y) {
        if (array[x][y].getMode() == Mode.HIDDEN) {
            array[x][y].setMode(Mode.MARKED);
        } else if (array[x][y].getMode() == Mode.MARKED) {
            array[x][y].setMode(Mode.HIDDEN);
        }
    }

    public boolean checkWin() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] instanceof Mine) {
                    if (array[i][j].getMode() != Mode.MARKED) {
                        return false;
                    }
                } else if (array[i][j] instanceof Default) {
                    if (array[i][j].getMode() == Mode.MARKED) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public boolean isValueZero(int x, int y) {
        if (array[x][y] instanceof Default) {
            if (((Default) array[x][y]).getValue() == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean checkLose(int x, int y) {
        return array[x][y] instanceof Mine;
    }

    public void setMinesMode(Mode mode) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] instanceof Mine) {
                    array[i][j].setMode(Mode.SHOWED);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(" |");
        for (int i = 0; i < array.length; i++) {
            res.append(i + 1);
        }
        res.append("|\n").append("-|").append("-".repeat(array.length)).append("|\n");
        for (int i = 0; i < array.length; i++) {
            res.append(i + 1).append("|");
            for (int j = 0; j < array.length; j++) {
                res.append(array[i][j]);
            }
            res.append("|\n");
        }
        res.append("-|").append("-".repeat(array.length)).append("|\n");
        return res.toString();
    }
}
