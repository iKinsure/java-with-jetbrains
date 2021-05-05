package org.ikinsure.cinema;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        View view = new View(
                System.out::println,
                new Scanner(System.in)::nextInt
        );

        Cinema cinema = new Cinema(
                view.inOut("Enter the number of rows:"),
                view.inOut("Enter the number of seats in each row:")
        );

        while (true) {
            switch (view.inOut(
                    "1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit")) {
                case 1:
                    view.out(cinema.scheme());
                    break;
                case 2:
                    buyTicket(view, cinema);
                    break;
                case 3:
                    view.out(cinema.statistics());
                    break;
                default:
                    return;
            }
        }
    }

    private static void buyTicket(View view, Cinema cinema) {
        while (true) {
            try {
                int price = cinema.buy(
                        view.inOut("Enter a row number:") - 1,
                        view.inOut("Enter a seat number in that row:") - 1
                );
                view.out("Ticket price: $" + price);
                break;
            } catch (Exception e) {
                view.out(e.getMessage());
            }
        }
    }
}
