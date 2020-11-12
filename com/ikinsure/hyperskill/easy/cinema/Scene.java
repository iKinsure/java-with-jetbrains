package com.ikinsure.hyperskill.easy.cinema;
import java.util.stream.IntStream;

public class Scene {

    enum Code {
        FREE(0, "S"),
        BUSY(1, "B");
        protected final int id;
        protected final String texture;
        Code(int id, String texture) {
            this.id = id;
            this.texture = texture;
        }
    }

    private final int[][] places;
    private final int rows;
    private final int cols;
    private final int seats;

    public Scene(int rows, int cols) {
        this.places = new int[rows][cols];
        this.rows = rows;
        this.cols = cols;
        this.seats = rows * cols;
    }

    public int[][] getPlaces() {
        return places;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getSeats() {
        return seats;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("  ");
        IntStream.rangeClosed(1, cols).forEach(i -> str.append(i).append(" "));
        str.append("\n");
        for (int y = 0; y < rows; y++) {
            str.append(y + 1).append(" ");
            for (int x = 0; x < cols; x++) {
                str.append(Code.values()[places[y][x]].texture).append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }
}
