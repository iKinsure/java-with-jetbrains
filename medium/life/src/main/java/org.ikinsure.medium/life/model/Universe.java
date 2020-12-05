package org.ikinsure.medium.life.model;

import java.util.Arrays;
import java.util.Random;

public class Universe {

    private boolean[][] current;
    private boolean[][] next;

    public Universe(int size, long seed) {
        this.current = set(new Random(seed), size);
    }

    public Universe(int size) {
        this.current = set(new Random(), size);
    }

    private boolean[][] set(Random random, int size) {
        boolean[][] matrix = new boolean[size][size];
        for (int y = matrix.length - 1; y >= 0; y--) {
            for (int x = 0; x < matrix.length; x++) {
                matrix[x][y] = random.nextBoolean();
            }
        }
        return matrix;
    }


    /** deep copy next to current */
    public void deepCopyNextToCurrent() {
        current = Arrays.stream(next).map(boolean[]::clone).toArray(boolean[][]::new);
    }

    public void shallowCopyCurrentToNext() {
        next = current.clone();
    }

    public int getNeighbourCounter(int x, int y) {
        int counter = 0;
        for (Direction direction : Direction.values()) {
            if (getNeighbour(direction, x, y)) {
                counter++;
            }
        }
        return counter;
    }

    private boolean getNeighbour(Direction direction, int x, int y) {
        x += direction.dx;
        y += direction.dy;
        if (y < 0) {
            y = current.length - 1;
        }
        if (x < 0) {
            x = current.length - 1;
        }
        if (y >= current.length) {
            y = 0;
        }
        if (x >= current.length) {
            x = 0;
        }
        return current[x][y];
    }

    public int getAliveCounter() {
        int counter = 0;
        for (boolean[] booleans : current) {
            for (int i = 0; i < current.length; i++) {
                if (booleans[i]) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public int getSize() {
        return current.length;
    }

    public boolean[][] getCurrent() {
        return current;
    }

    public boolean[][] getNext() {
        return next;
    }

    public boolean getCurrent(int x, int y) {
        return current[x][y];
    }

    public boolean getNext(int x, int y) {
        return next[x][y];
    }

    public void setCurrent(boolean[][] current) {
        this.current = current;
    }

    public void setNext(boolean[][] next) {
        this.next = next;
    }

    public void setCurrent(int x, int y, boolean value) {
        current[x][y] = value;
    }

    public void setNext(int x, int y, boolean value) {
        next[x][y] = value;
    }
}
