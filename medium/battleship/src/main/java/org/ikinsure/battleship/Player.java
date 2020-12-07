package org.ikinsure.battleship;

import java.util.ArrayList;

public class Player {

    private final Board shipBoard;
    private final Board shotBoard;
    private final String name;
    private final ArrayList<Ship> ships;

    public Player(int size, String name) {
        this.shipBoard = new Board(size);
        this.shotBoard = new Board(size);
        this.name = name;
        this.ships = new ArrayList<>();
    }

    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public Ship getShipByPosition(int x, int y) {
        for (Ship ship : ships) {
            boolean isX = x >= ship.getXMin() && x <= ship.getXMax();
            boolean isY = y >= ship.getYMin() && y <= ship.getYMax();
            if (isX && isY) {
                return ship;
            }
        }
        return null;
    }

    public Board getShipBoard() {
        return shipBoard;
    }

    public Board getShotBoard() {
        return shotBoard;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return shotBoard + "-".repeat(2 * shotBoard.getSize() + 1) + "\n" + shipBoard;
    }
}
