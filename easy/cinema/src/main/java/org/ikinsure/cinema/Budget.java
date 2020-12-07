package org.ikinsure.cinema;

public class Budget {

    int soldTickets;
    int currentIncome;
    private final Scene scene;

    public Budget(Scene scene) {
        this.scene = scene;
        this.soldTickets = 0;
        this.currentIncome = 0;
    }

    double getPercent() {
        return (1.0 * soldTickets / scene.getSeats()) * 100.0;
    }

    int getTotalIncome() {
        if (scene.getSeats() <= 60) {
            return 10 * scene.getSeats();
        } else {
            int half = scene.getRows() / 2;
            return  (10 * scene.getCols()  * half) + (8 * scene.getCols() * (scene.getRows() - half));
        }
    }
}
