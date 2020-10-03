package hard.minesweeper;

import java.util.Random;

public class Map {

    private boolean[][] array;
    public static final Random RANDOM =  new Random();

    public Map(int size) {
        this.array = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i][j] = false;
            }
        }
    }

    public void placeMines(int mines) {
        for (int i = 0; i < mines; i++) {
            boolean isSuccess = false;
            while (!isSuccess) {
                final int x = RANDOM.nextInt(array.length);
                final int y = RANDOM.nextInt(array.length);
                if (!array[y][x]) {
                    array[y][x] = true;
                    isSuccess = true;
                }
            }
        }
    }

    public int getMinesCounter(int x, int y) {
        int counter = 0;
        for (Dir dir : Dir.values()) {
            final int X = dir.x + x;
            final int Y = dir.y + y;
            if (X >= 0 && X < array.length && Y >= 0 && Y < array.length) {
                if (array[X][Y]) {
                    counter++;
                }
            }
        }
        return counter;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j]) {
                    res.append('X');
                } else {
                    final int count = getMinesCounter(i, j);
                    res.append(count == 0 ? "." : count);
                }
            }
            res.append("\n");
        }
        return res.toString();
    }
}
