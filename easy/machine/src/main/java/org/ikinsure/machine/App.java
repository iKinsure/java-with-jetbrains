package org.ikinsure.machine;

import org.ikinsure.machine.machine.GenericMachine;
import org.ikinsure.machine.machine.Machine;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static org.ikinsure.machine.Ingredient.*;
import static org.ikinsure.machine.Product.*;

public class App {

    public static void main(String[] args) {

        Map<Ingredient, Integer> supplies = new LinkedHashMap<>();
        supplies.put(WATER, 400);
        supplies.put(MILK, 540);
        supplies.put(BEANS, 120);
        supplies.put(CUPS, 9);

        Machine machine = new GenericMachine(
                "coffee",
                new Scanner(System.in)::nextLine,
                System.out::println,
                supplies,
                new Product[]{CAPPUCCINO, LATTE, ESPRESSO},
                550);
        machine.run();
    }
}
