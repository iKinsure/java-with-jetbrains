package medium.life.view;

import medium.life.model.Universe;

public class Console {
    public static void print(Universe universe, int generation) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Generation #" + (generation + 1));
        System.out.println("Alive #" + universe.getAliveCounter());
        boolean[][] array = universe.getCurrent();
        for (int y = array.length - 1; y >= 0; y--) {
            for (boolean[] booleans : array) {
                System.out.print(booleans[y] ? "O" : "-");
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
