package life.controller;

import life.model.Universe;
import life.view.Console;
import life.view.GameOfLife;

public class App {

    private final int generations;
    private final Universe universe;

    public App(int size, int generations) {
        this.generations = generations;
        this.universe = new Universe(size);
        universe.shallowCopyCurrentToNext();
    }

    public void simulateWorld() {

        GameOfLife gui = new GameOfLife();
        gui.setUniverse(universe);

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

            gui.setUniverse(universe);
            gui.getAliveLabel().setText("Alive: " + universe.getAliveCounter());
            gui.getGenerationLabel().setText("Generation #" + (generation + 1));
            gui.getGraphPane().repaint();
            Console.print(universe, generation);
        }
    }
}
