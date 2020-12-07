package org.ikinsure.machine;
import java.util.Scanner;

public class CoffeeMachine {

    static Scanner scan = new Scanner(System.in);
    static int water = 400;
    static int milk = 540;
    static int beans = 120;
    static int cups = 9;
    static int money = 550;

    private static boolean action() {
        System.out.println("Write action (buy, fill, take, remaining, exit): ");
        switch (scan.next()) {
            case "buy":
                buy();
                break;
            case "fill":
                fill();
                break;
            case "take":
                take();
                break;
            case "remaining":
                remaining();
                break;
            case "exit":
                return false;
        }
        return true;
    }

    private static void remaining() {
        System.out.println("The coffee machine has: ");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println("$" + money + " of money");
    }

    private static void take() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    private static void fill() {
        System.out.println("Write how many ml of water do you want to add: ");
        water += scan.nextInt();
        System.out.println("Write how many ml of milk do you want to add: ");
        milk += scan.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add: ");
        beans += scan.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add: ");
        cups += scan.nextInt();
    }

    private static void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        switch (scan.next()) {
            case "1": // espresso
                makeCoffee(250, 0, 16, 4, 1);
                break;
            case "2": // latte
                makeCoffee(350, 75, 20, 7 , 1);
                break;
            case "3": // cappuccino
                makeCoffee(200, 100, 12, 6, 1);
                break;
            default: // back
                break;
        }
    }

    private static void makeCoffee(int water, int milk, int beans, int money, int cups) {
        if (CoffeeMachine.water >= water && CoffeeMachine.milk >= milk && CoffeeMachine.beans >= beans && CoffeeMachine.cups >= cups) {
            CoffeeMachine.water -= water;
            CoffeeMachine.milk -= milk;
            CoffeeMachine.beans -= beans;
            CoffeeMachine.money += money;
            CoffeeMachine.cups -= cups;
            System.out.println("I have enough resources, making you a coffee!");
        }
        else {
            if (CoffeeMachine.water < water) {
                System.out.println("Sorry, not enough water!");
            }
            else if (CoffeeMachine.milk < milk) {
                System.out.println("Sorry, not enough milk!");
            }
            else if (CoffeeMachine.beans < beans) {
                System.out.println("Sorry, not enough coffee beans!");
            }
            else {
                System.out.println("Sorry, not enough cups!");
            }
        }
    }

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            running = action();
        }
    }
}
