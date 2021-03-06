package org.ikinsure.banking.model;

import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class Bank {

    private final Database database;
    private final String IIN;
    private final Random random;

    public Bank(String file) {
        this.database = Database.getInstance();
        this.random = new Random();
        this.IIN = "400000";
        database.createTable(file);
    }

    // generate new unique card
    public Card createCard() {
        Set<String> numbers = database.selectAllNumbers();
        while (true) {
            String number = generateNumber();
            if (numbers.add(number)) {
                Card card = new Card(number, generatePin(), 0);
                database.insert(card);
                return card;
            }
        }
    }

    public Optional<Card> logToCard(String number, String pin) {
        Optional<Card> card = database.selectByNumber(number);
        return (card.isPresent() && card.get().getPin().equals(pin)) ? card : Optional.empty();
    }

    // find card by number
    public Optional<Card> getCard(String number) {
        return database.selectByNumber(number);
    }

    public Card addBalance(Card card, int value) {
        card = card.addBalance(value);
        database.updateBalance(card);
        return card;
    }

    public void deleteCard(Card card) {
        database.delete(card);
    }

    // is card with luhn formula
    public boolean isNumberValid(String number) {
        return luhnFormula(number) % 10 == 0;
    }

    // luhn algorithm implemented
    public int luhnFormula(String data) {
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

    public String generateNumber() {
        StringBuilder builder = new StringBuilder(IIN);
        for (int i = 0; i < 9; i++) {
            builder.append(random.nextInt(10));
        }
        builder.append(generateCheckSum(builder.toString()));
        return builder.toString();
    }

    // last number of card
    public int generateCheckSum(String data) {
        int num = luhnFormula(data) % 10;
        return num == 0 ? 0 : 10 - num;
    }

    // 4 digits pin for card
    public String generatePin() {
        return String.valueOf(random.nextInt(10)) + random.nextInt(10) +
                random.nextInt(10) + random.nextInt(10);
    }
}
