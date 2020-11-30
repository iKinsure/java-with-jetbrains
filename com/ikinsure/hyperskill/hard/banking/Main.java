package com.ikinsure.hyperskill.hard.banking;

import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Bank bank = new Bank();

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Create an account\n" +
                    "2. Log into account\n" +
                    "0. Exit");
            switch (SCANNER.nextInt()) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    logToAccount();
                    break;
                default:
                    System.out.println("Bye!");
                    return;
            }
        }
    }

    private static void createAccount() {
        Card card = bank.createCard();
        System.out.println("Your card has been created\n" +
                "Your card number:\n" +
                card.getNumber() + "\n" +
                "Your card PIN:\n" +
                card.getPin());
    }

    private static void logToAccount() {
        System.out.println("Enter your card number:");
        String number = SCANNER.next();
        System.out.println("Enter your PIN:");
        String pin = SCANNER.next();
        Card card = bank.logToCard(number, pin);
        if (card != null) {
            System.out.println("You have successfully logged in");
            while (true) {
                System.out.println("1. Balance\n" +
                        "2. Log out\n" +
                        "0. Exit");
                switch (SCANNER.nextInt()) {
                    case 1:
                        System.out.println("Balance: " + card.getBalance());
                        break;
                    case 2:
                        System.out.println("You have successfully logged out!");
                        return;
                    default:
                        return;
                }
            }
        } else {
            System.out.println("Wrong card number or PIN!");
        }
    }
}
