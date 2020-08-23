package life;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) {

        int size = SCAN.nextInt();
        //long seed = SCAN.nextLong();
        //int generations = SCAN.nextInt();
        int generations = 100;

        Universe universe = new Universe();
        universe.setCurrent(UniverseManager.generateRandomMatrix(size));
        universe.shallowCopyCurrentToNext();

        for (int generation = 0; generation < generations; generation++) {
            universe.deepCopyNextToCurrent();
            for (int x = 0; x < universe.getSize(); x++) {
                for (int y = 0; y < universe.getSize(); y++) {
                    switch (universe.getNeighbourCounter(x, y)) {
                        case 3:
                            universe.setNext(x, y, true); // reborn
                            break;
                        case 2:
                            universe.setNext(x, y, universe.getCurrent(x, y)); // survive
                            break;
                        default:
                            universe.setNext(x, y, false); // dead
                    }
                }
            }
            UniverseManager.print(universe, generation);
        }
    }
}
