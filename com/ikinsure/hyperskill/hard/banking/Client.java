package com.ikinsure.hyperskill.hard.banking;

import com.ikinsure.hyperskill.hard.banking.model.Bank;
import com.ikinsure.hyperskill.hard.banking.model.Card;
import com.ikinsure.hyperskill.hard.banking.view.Menu;
import com.ikinsure.hyperskill.hard.banking.view.MenuController;

import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Client {

    private final Scanner scanner;
    private final MenuController view;
    private final Bank bank;
    private final Database database;

    private Card activeCard;

    public Client(DBController controller) {
        this.scanner = new Scanner(System.in);
        this.view = new MenuController();
        this.bank = controller.getBank();
        this.database = controller.getDatabase();
        run();
    }

    private void run() {
        view.run(new Menu.Builder()
                .setScanner(scanner)
                .addItem(1, "Create an account", this::createAccount)
                .addItem(2, "Log into account", this::logToAccount)
                .addItem(0, "Exit", view::exitMenu)
                .build());
    }


    private void createAccount() {
        Card card = createCard();
        database.insert(card);
        System.out.println("Your card has been created\n" +
                "Your card number:\n" +
                card.getNumber() + "\n" +
                "Your card PIN:\n" +
                card.getPin() + "\n");
    }

    private void logToAccount() {

        System.out.println("Enter your card number:");
        String number = scanner.nextLine();
        System.out.println("Enter your PIN:");
        String pin = scanner.nextLine();
        System.out.println();

        Optional<Card> card = logToCard(number, pin);
        if (card.isPresent()) {
            this.activeCard = card.get();
            System.out.println("You have successfully logged in\n");
            view.setMenu(new Menu.Builder()
                    .setScanner(scanner)
                    .addItem(1, "Balance", this::printBalance)
                    .addItem(2, "Add income", this::addIncome)
                    .addItem(3, "Do transfer", this::doTransfer)
                    .addItem(4, "Close account", this::closeAccount)
                    .addItem(5, "Log out", this::logOut)
                    .addItem(0, "Exit", view::exitAll)
                    .build());
        } else {
            System.out.println("Wrong card number or PIN!\n");
        }
    }

    private void printBalance() {
        System.out.println("Balance: " + activeCard.getBalance() + "\n");
    }

    private void addIncome() {
        System.out.println("Enter income");
        activeCard.addBalance(Integer.parseInt(scanner.nextLine()));
        database.updateBalance(activeCard);
        System.out.println("Income was added!\n");
    }

    private void doTransfer() {
        System.out.println("Transfer");
        System.out.println("Enter card number:");
        String number = scanner.nextLine();
        if (isNumberValid(number)) {
            Optional<Card> cardOptional = database.selectByNumber(number);
            if (cardOptional.isPresent()) {
                Card card = cardOptional.get();
                if (!card.equals(activeCard)) {
                    System.out.println("Enter how much money you want to transfer:");
                    int money = Integer.parseInt(scanner.nextLine());
                    if (activeCard.getBalance() >= money) {
                        activeCard = activeCard.addBalance(-money);
                        card = card.addBalance(money);
                        database.updateBalance(activeCard);
                        database.updateBalance(card);
                        System.out.println("Success!\n");
                    } else {
                        System.out.println("Not enough money!\n");
                    }
                } else {
                    System.out.println("You cannot send money to yourself!\n");
                }
            }
            else {
                System.out.println("Such a card does not exist.\n");
            }
        } else {
            System.out.println("Probably you made mistake in the card number. Please try again!\n");
        }
    }

    private void closeAccount() {
        database.delete(activeCard);
        System.out.println("The account has been closed!\n");
        view.exitMenu();
    }

    private void logOut() {
        System.out.println("You have successfully logged out!\n");
        view.exitAll();
    }

    ///////////////////////////////////////////////////////////////////////////

    public Card createCard() {
        Set<Card> cards = database.selectAll();
        while (true) {
            Card card = new Card(generateNumber(), generatePin(), 0);
            if (cards.add(card)) {
                return card;
            }
        }
    }

    public Optional<Card> logToCard(String number, String pin) {
        Optional<Card> card = database.selectByNumber(number);
        return (card.isPresent() && card.get().getPin().equals(pin))
                ? card : Optional.empty();
    }

    private String generateNumber() {

        Random random = new Random();
        String IIN = "400000";

        StringBuilder builder = new StringBuilder(IIN);
        for (int i = 0; i < 9; i++) {
            builder.append(random.nextInt(10));
        }
        builder.append(generateCheckSum(builder.toString()));
        return builder.toString();
    }

    private int generateCheckSum(String data) {
        int num = luhnFormula(data) % 10;
        return num == 0 ? 0 : 10 - num;
    }

    public boolean isNumberValid(String number) {
        return luhnFormula(number) % 10 == 0;
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
        Random RANDOM = new Random();

        return String.valueOf(RANDOM.nextInt(10)) + RANDOM.nextInt(10) +
                RANDOM.nextInt(10) + RANDOM.nextInt(10);
    }
}
