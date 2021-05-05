package org.ikinsure.cinema;

import org.ikinsure.cinema.scene.CinemaScene;
import org.ikinsure.cinema.scene.Place;
import org.ikinsure.cinema.scene.Scene2D;
import java.text.DecimalFormat;

public class Cinema {

    private static final int LOW_PRICE = 8;
    private static final int HIGH_PRICE = 10;
    private static final int MAX_SMALL_SCENE = 60;

    private final Scene2D scene;
    private int tickets;
    private int income;

    public Cinema(int rows, int columns) {
        this.scene = new CinemaScene(rows, columns);
        this.tickets = 0;
        this.income = 0;
    }

    public String statistics() {
        String percentage = new DecimalFormat("#0.00").format(percent());
        return "Number of purchased tickets: " + tickets + "\n" +
                "Percentage: " + percentage + "%\n" +
                "Current income: $" + income + "\n" +
                "Total income: $" + totalIncome();
    }

    public String scheme() {
        return "Cinema:\n" + scene.scheme();
    }

    /**
     * validate and buy ticket (reserve tickets)
     */
    public int buy(int i, int j) {

        if (j < 0 || i < 0 || j >= scene.getColumns() || i >= scene.getRows()) {
            throw new IllegalArgumentException("Wrong input!");
        }

        if (scene.getPlace(i, j) != Place.FREE) {
            throw new IllegalArgumentException("That ticket has already been purchased!");
        }

        // set place, add price to income, increment tickets
        scene.setPlace(i, j);
        int price = ticketPrice(i + 1);
        income += price;
        tickets++;
        return price;
    }

    /**
     * calculate ticket price in row
     */
    private int ticketPrice(int row) {
        return scene.getPlaces() <= MAX_SMALL_SCENE
                ? HIGH_PRICE
                : row > scene.getRows() / 2 ? LOW_PRICE : HIGH_PRICE;
    }

    /**
     * percent of total sold tickets
     */
    private double percent() {
        return 100.0 * tickets / scene.getPlaces();
    }

    /**
     * total possible income
     */
    private int totalIncome() {
        int places = scene.getPlaces();
        return places <= MAX_SMALL_SCENE
                ? HIGH_PRICE * places
                : ((LOW_PRICE + HIGH_PRICE) / 2) * places;
    }

}
