package com.ikinsure.hyperskill.hard.banking;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Bank {

    private static final Random RANDOM = new Random();
    static final String IIN = "400000";
    private final Set<Card> cards;

    public Bank() {
        this.cards = new HashSet<>();
    }

    public Card createCard() {
        while (true) {
            String number = generateNumber();
            if (cards.stream().anyMatch(c -> c.getNumber().equals(number))) {
                continue;
            }
            String pin = generatePin();
            Card card = new Card(number, pin);
            cards.add(card);
            return card;
        }
    }

    public Card logToCard(String number, String pin) {
        for (Card card : cards) {
            if (card.getNumber().equals(number) && card.getPin().equals(pin)) {
                return card;
            }
        }
        return null;
    }

    private String generateNumber() {
        StringBuilder builder = new StringBuilder(Bank.IIN);
        for (int i = 0; i < 10; i++) {
            builder.append(RANDOM.nextInt(10));
        }
        return builder.toString();
    }

    private String generatePin() {
        return String.valueOf(RANDOM.nextInt(10)) + RANDOM.nextInt(10) +
                RANDOM.nextInt(10) + RANDOM.nextInt(10);
    }
}
