package life;

import java.util.Random;

public class UniverseManager {

    public static boolean[][] generateRandomMatrix(int size, long seed) {
        return generateRandomMatrix(new Random(seed), size);
    }

    public static boolean[][] generateRandomMatrix(int size) {
        return generateRandomMatrix(new Random(), size);
    }

    private static boolean[][] generateRandomMatrix(Random random, int size) {
        boolean[][] matrix = new boolean[size][size];
        for (int y = matrix.length - 1; y >= 0; y--) {
            for (int x = 0; x < matrix.length; x++) {
                matrix[x][y] = random.nextBoolean();
            }
        }
        return matrix;
    }

    public static void print(Universe universe, int generation) {
        sleep(1000);
        System.out.println("Generation #" + (generation + 1));
        System.out.println("Alive #" + universe.getAliveCounter());
        boolean[][] array = universe.getCurrent();
        for (int y = array.length - 1; y >= 0; y--) {
            for (boolean[] booleans : array) {
                System.out.print(booleans[y] ? "O" : " ");
            }
            System.out.println();
        }
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
