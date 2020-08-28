package life.controller;

import life.model.Universe;
import life.view.Console;
import life.view.GameOfLife;

import java.util.EventListener;

public class App {

    private final int generations;
    private int generation;
    private Universe universe;
    private GameOfLife game;
    private int size;

    public App(int size, int generations) {
        this.generations = generations;
        this.size = size;
        game = new GameOfLife();
        prepare();
        simulateWorld();
    }

    public void prepare() {
        this.universe = new Universe(size);
        universe.shallowCopyCurrentToNext();
    }

    public void simulateWorld() {
        for (generation = 0; generation < generations; generation++) {
            game.getSimulationPane().setUniverse(universe);
            universe.deepCopyNextToCurrent();
            simulateGeneration();
            game.getGenerationLabel().setText("Generation #" + (generation + 1));
            game.getAliveLabel().setText("Alive: " + universe.getAliveCounter());
            game.getSimulationPane().repaint();
            try {
                Thread.sleep(game.getSlider().getValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void simulateGeneration() {
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
    }
}
