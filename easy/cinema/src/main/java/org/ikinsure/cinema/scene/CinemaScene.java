package org.ikinsure.cinema.scene;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CinemaScene implements Scene2D {

    private final Place[][] places;
    private final int rows;
    private final int columns;

    public CinemaScene(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.places = new Place[rows][columns];
        Arrays.stream(places).forEach(r -> Arrays.fill(r, Place.FREE));
    }

    @Override
    public Place getPlace(int i, int j) {
        return places[i][j];
    }

    @Override
    public void setPlace(int i, int j) {
        places[i][j] = Place.BUSY;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public String scheme() {

        // determine scaling size
        final int rowLength = (int) (Math.log10(rows) + 1);
        final int columnLength = (int) (Math.log10(columns) + 1);

        // first row filled with indices
        String numeration = IntStream.rangeClosed(1, columns)
                .mapToObj(i -> extend("" + i, columnLength))
                .collect(Collectors.joining(" "));

        // rows with starting index
        String scheme = IntStream.rangeClosed(1, rows)
                .mapToObj(i -> extend("" + i, rowLength) + " " + Arrays.stream(places[i - 1])
                        .map(j -> extend(j.sign, columnLength))
                        .collect(Collectors.joining(" ")))
                .collect(Collectors.joining("\n"));

        // join indices with rows
        return " " + " ".repeat(rowLength) + numeration + "\n" + scheme;
    }

    private String extend(String elem, int length) {
        return " ".repeat(length - elem.length()) + elem;
    }

}
