package com.ikinsure.hyperskill.hard.banking.model;

import java.util.HashSet;
import java.util.Optional;
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
            Card card = new Card(generateNumber(), generatePin(), 0);
            if (cards.add(card)) {
                return card;
            }
        }
    }

    public Optional<Card> logToCard(String number, String pin) {
        if (isCardValid(number)) {
            return cards.stream().filter(c -> c.getNumber().equals(number) && c.getPin().equals(pin)).findFirst();
        }
        return Optional.empty();
    }

    public int getCardsCounter() {
        return cards.size();
    }

    private String generateNumber() {
        StringBuilder builder = new StringBuilder(Bank.IIN);
        for (int i = 0; i < 9; i++) {
            builder.append(RANDOM.nextInt(10));
        }
        builder.append(generateCheckSum(builder.toString()));
        return builder.toString();
    }

    private int generateCheckSum(String data) {
        int num = luhnFormula(data) % 10;
        return num == 0 ? 0 : 10 - num;
    }

    private boolean isCardValid(String data) {
        return luhnFormula(data) % 10 == 0;
    }

    private int luhnFormula(String data) {
        int counter = 0;
        for (int i = 0; i < data.length(); i++) {
            int num = Integer.parseInt(String.valueOf(data.charAt(i)));
            if (i % 2 == 0) {
                num *= 2;
                if (num > 9) {
                    num -= 9;
                }
            }
            counter += num;
        }
        return counter;
    }

    private String generatePin() {
        return String.valueOf(RANDOM.nextInt(10)) + RANDOM.nextInt(10) +
                RANDOM.nextInt(10) + RANDOM.nextInt(10);
    }
}
