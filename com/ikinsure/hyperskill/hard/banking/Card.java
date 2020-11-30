package com.ikinsure.hyperskill.hard.banking;

import java.util.Objects;

public class Card {


    private final String number;
    private final String pin;
    private final double balance;

    public Card(String number, String pin) {
        this.number = number;
        this.pin = pin;
        this.balance = 0.0;
    }

    public String getNumber() {
        return number;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(number, card.number) && Objects.equals(pin, card.pin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, pin);
    }
}
