package org.ikinsure.banking.model;

import java.util.Objects;

/**
 * immutable class represents card
 */
public class Card {

    private final String number;
    private final String pin;
    private final int balance;

    public Card(String number, String pin, int balance) {
        this.number = number;
        this.pin = pin;
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public String getPin() {
        return pin;
    }

    public int getBalance() {
        return balance;
    }

    public Card addBalance(int value) {
        return new Card(number, pin, balance + value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(number, card.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
