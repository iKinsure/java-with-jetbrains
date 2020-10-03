package hard.minesweeper;

import java.util.Random;

public class Map {

    private static final int DEFAULT = 0;
    private static final int MINE = -1;
    private static final int MARK_DEFAULT = -2;
    private static final int MARK_MINE = -3;

    private final int[][] array;
    private static final Random RANDOM =  new Random();

    public Map(int size) {
        this.array = new int[size][size];
    }

    public void placeMines(int mines) {
        for (int i = 0; i < mines; i++) {
            boolean isSuccess = false;
            while (!isSuccess) {
                final int X = RANDOM.nextInt(array.length);
                final int Y = RANDOM.nextInt(array.length);
                if (array[X][Y] == DEFAULT) {
                    array[X][Y] = MINE;
                    isSuccess = true;
                }
            }
        }
    }

    public void placeMinesCounters() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] != MINE) {
                    array[i][j] = getMinesCounter(i, j);
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
                if (array[X][Y] == MINE) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public boolean mark(int x, int y) {
        if (array[x][y] == MINE) {
            array[x][y] = MARK_MINE;
            return true;
        } else if (array[x][y] == DEFAULT) {
            array[x][y] = MARK_DEFAULT;
            return true;
        } else if (array[x][y] == MARK_MINE) {
            array[x][y] = MINE;
            return true;
        } else if (array[x][y] == MARK_DEFAULT) {
            array[x][y] = DEFAULT;
            return true;
        }
        return false;
    }

    public boolean checkWin() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] == MINE || array[i][j] == MARK_DEFAULT) {
                    return false;
                }
            }
        }
        return true;
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
                if (array[i][j] == MARK_DEFAULT || array[i][j] == MARK_MINE) {
                    res.append("*");
                } else if (array[i][j] == DEFAULT || array[i][j] == MINE) {
                    res.append(".");
                } else {
                    res.append(array[i][j]);
                }
            }
            res.append("|\n");
        }
        res.append("-|").append("-".repeat(array.length)).append("|\n");
        return res.toString();
    }
}
