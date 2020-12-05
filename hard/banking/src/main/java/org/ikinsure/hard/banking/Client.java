package org.ikinsure.hard.banking;

import org.ikinsure.hard.banking.model.Bank;
import org.ikinsure.hard.banking.model.Card;
import org.ikinsure.hard.banking.view.Menu;
import org.ikinsure.hard.banking.view.MenuController;

import java.util.Optional;
import java.util.Scanner;

public class Client {

    private final Scanner scanner;
    private final MenuController view;
    private final Bank bank;
    private Card activeCard;

    public Client(String file) {
        this.scanner = new Scanner(System.in);
        this.view = new MenuController();
        this.bank = new Bank(file);

        // initialize
        view.run(new Menu.Builder()
                .setScanner(scanner)
                .addItem(1, "Create an account", this::createAccount)
                .addItem(2, "Log into account", this::logToAccount)
                .addItem(0, "Exit", view::exitMenu)
                .build());
    }

    private void createAccount() {
        Card card = bank.createCard();
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
        Optional<Card> card = bank.logToCard(number, pin);
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
        activeCard = bank.addBalance(activeCard, Integer.parseInt(scanner.nextLine()));
        System.out.println("Income was added!\n");
    }

    private void doTransfer() {
        System.out.println("Transfer");
        System.out.println("Enter card number:");
        String number = scanner.nextLine();
        if (bank.isNumberValid(number)) {
            Optional<Card> card = bank.getCard(number);
            if (card.isPresent()) {
                if (!card.get().equals(activeCard)) {
                    System.out.println("Enter how much money you want to transfer:");
                    int money = Integer.parseInt(scanner.nextLine());
                    if (activeCard.getBalance() >= money) {
                        bank.addBalance(card.get(), money);
                        activeCard = bank.addBalance(activeCard, -money);
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
        bank.deleteCard(activeCard);
        view.exitMenu();
        System.out.println("The account has been closed!\n");
    }

    private void logOut() {
        view.exitMenu();
        System.out.println("You have successfully logged out!\n");
    }
}
