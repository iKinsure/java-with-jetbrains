package org.ikinsure.cinema;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CinemaTest {

    @Test
    void shouldGetStatisticsFromSmallScene() {
        Cinema cinema = new Cinema(3, 5);
        cinema.buy(0, 0);
        cinema.buy(2, 1);
        cinema.buy(1, 1);
        String actual = cinema.statistics();
        String[] expect = {
                "Number of purchased tickets: 3",
                "Percentage: 20.00%",
                "Current income: $30",
                "Total income: $150",
        };
        assertEquals(String.join("\n", expect), actual);
    }

    @Test
    void shouldGetStatisticsFromBigScene() {
        Cinema cinema = new Cinema(11, 11);
        cinema.buy(0, 0);
        cinema.buy(2, 1);
        cinema.buy(10, 10);
        String actual = cinema.statistics();
        String[] expect = {
                "Number of purchased tickets: 3",
                "Percentage: 2.48%",
                "Current income: $28",
                "Total income: $1089",
        };
        System.out.println(actual);
        assertEquals(String.join("\n", expect), actual);
    }

    @Test
    void shouldThrowException1() {
        Cinema cinema = new Cinema(2, 2);
        cinema.buy(0, 0);
        Exception exc = assertThrows(
                IllegalArgumentException.class,
                () -> cinema.buy(0, 0)
        );
        String expect = "That ticket has already been purchased!";
        assertEquals(expect, exc.getMessage());
    }

    @Test
    void shouldThrowException2() {
        Cinema cinema = new Cinema(2, 2);
        Exception exc = assertThrows(
                IllegalArgumentException.class,
                () -> cinema.buy(2, 2)
        );
        String expect = "Wrong input!";
        assertEquals(expect, exc.getMessage());
    }
}
