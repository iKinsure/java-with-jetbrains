package org.ikinsure.medium.battleship;

public enum ShipType {

    CARRIER("the Aircraft Carrier", 5),
    BATTLESHIP("the Battleship", 4),
    SUBMARINE("the Submarine", 3),
    CRUISER("the Cruiser", 3),
    DESTROYER("the Destroyer", 2);

    final int size;
    final String name;

    ShipType(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public static int getAllSize() {
        int counter = 0;
        for (ShipType type : ShipType.values()) {
            counter += type.size;
        }
        return counter;
    }

    @Override
    public String toString() {
        return name + " (" + size + " cells)";
    }
}
