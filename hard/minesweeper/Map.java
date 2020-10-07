package hard.minesweeper;

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

    public void placeMines(int mines, int x, int y) {
        for (int i = 0; i < mines; i++) {
            while (true) {
                final int xRand = RANDOM.nextInt(array.length);;
                final int yRand = RANDOM.nextInt(array.length);
                if (isGood(x, y, xRand, yRand)) {
                    if (!(array[xRand][yRand] instanceof Mine)) {
                        array[xRand][yRand] = new Mine(Mode.HIDDEN);
                        break;
                    }
                }
            }
        }
    }

    private boolean isGood(int x, int y, int xRand, int yRand) {
        boolean good = true;
        for (Dir dir : Dir.values()) {
            final int X = dir.x + x;
            final int Y = dir.y + y;
            if (X >= 0 && X < array.length && Y >= 0 && Y < array.length) {
                if (xRand == X && yRand == Y) {
                    good = false;
                    break;
                }
            }
        }
        if (xRand == x && yRand == y) {
            good = false;
        }
        return good;
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
        for (Dir dir : Dir.values()) {
            final int X = dir.x + x;
            final int Y = dir.y + y;
            if (X >= 0 && X < array.length && Y >= 0 && Y < array.length) {
                if (isValueZero(X, Y) && array[X][Y].getMode() != Mode.MARKED) {
                    if (array[X][Y].getMode() != Mode.SHOWED) {
                        array[X][Y].setMode(Mode.SHOWED);
                        discover(X, Y);
                    }
                } else {
                    array[X][Y].setMode(Mode.SHOWED);
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

    public boolean checkWinFirst() {
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

    public boolean checkWinSecond() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] instanceof Default) {
                    if (array[i][j].getMode() != Mode.SHOWED) {
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
