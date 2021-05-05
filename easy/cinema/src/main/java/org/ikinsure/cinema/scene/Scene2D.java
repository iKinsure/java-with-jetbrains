package org.ikinsure.cinema.scene;

public interface Scene2D extends Scene {

    int getRows();
    int getColumns();
    void setPlace(int i, int j);
    Place getPlace(int i, int j);

    @Override
    default int getPlaces() {
        return getRows() * getColumns();
    }
}
