package life;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Scanner SCAN = new Scanner(System.in);

    public enum Direction {
        NORTH(0, 1),
        NORTH_EAST(1, 1),
        EAST(1, 0),
        SOUTH_EAST(1, -1),
        SOUTH(0, -1),
        SOUTH_WEST(-1, -1),
        WEST(-1, 0),
        NORTH_WEST(-1, 1);
        final int dx;
        final int dy;
        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
    }

    public static void main(String[] args) {

        int size = SCAN.nextInt();
        //long seed = SCAN.nextLong();
        //int generations = SCAN.nextInt();
        int generations = 10;

        boolean[][] current = generateRandomMatrix(size);
        boolean[][] next = current.clone();

        print(current, -1);
        for (int generation = 0; generation < generations; generation++) {
            current = Arrays.stream(next).map(boolean[]::clone).toArray(boolean[][]::new); // copy
            for (int x = 0; x < current.length; x++) {
                for (int y = 0; y < current.length; y++) {
                    switch (neighbourCounter(current, x, y)) {
                        case 3:
                            next[x][y] = true; // reborn
                            break;
                        case 2:
                            next[x][y] = current[x][y]; // survive
                            break;
                        default:
                            next[x][y] = false; // dead
                    }
                }
            }
            print(next, generation);
        }

    }

    /** good */
    private static boolean[][] generateRandomMatrix(int size, long seed) {
        boolean[][] array = new boolean[size][size];
        Random random = new Random(seed);
        for (int y = array.length - 1; y >= 0; y--) {
            for (int x = 0; x < array.length; x++) {
                array[x][y] = random.nextBoolean();
            }
        }
        return array;
    }

    private static boolean[][] generateRandomMatrix(int size) {
        boolean[][] array = new boolean[size][size];
        Random random = new Random();
        for (int y = array.length - 1; y >= 0; y--) {
            for (int x = 0; x < array.length; x++) {
                array[x][y] = random.nextBoolean();
            }
        }
        return array;
    }

    /** good */
    private static void print(boolean[][] array, int generation) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }

        System.out.println("Generation #" + (generation + 2));
        System.out.println("Alive #" + getAlive(array));
        for (int y = array.length - 1; y >= 0; y--) {
            for (boolean[] booleans : array) {
                System.out.print(booleans[y] ? "O" : " ");
            }
            System.out.println();
        }
    }

    private static int getAlive(boolean[][] array) {
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j]) {
                    counter++;
                }
            }
        }
        return counter;
    }


    /** good */
    private static int neighbourCounter(boolean[][] array, int x, int y) {
        int counter = 0;
        for (Direction dir: Direction.values()) {
            if (getNeighbour(dir, array, x, y)) {
                counter++;
            }
        }
        return counter;
    }

    /** good */
    private static boolean getNeighbour(Direction dir, boolean[][] array, int x, int y) {
        x += dir.dx;
        y += dir.dy;
        if (y < 0) {
            y = array.length - 1;
        }
        if (x < 0) {
            x = array.length - 1;
        }
        if (y >= array.length) {
            y = 0;
        }
        if (x >= array.length) {
            x = 0;
        }
        return array[x][y];
    }
}
