package org.ikinsure.machine;

import java.util.Map;

import static org.ikinsure.machine.Ingredient.*;

public enum Product {

    ESPRESSO(Map.of(
                    WATER, 250,
                    MILK, 0,
                    BEANS, 16,
                    CUPS, 1
            ), 4
    ),
    LATTE(Map.of(
                    WATER, 350,
                    MILK, 75,
                    BEANS, 20,
                    CUPS, 1
            ), 7
    ),
    CAPPUCCINO(Map.of(
                    WATER, 250,
                    MILK, 100,
                    BEANS, 12,
                    CUPS, 1
            ), 6
    );

    public final Map<Ingredient, Integer> ingredients;
    public final int cost;

    Product(Map<Ingredient, Integer> ingredients, int cost) {
        this.ingredients = ingredients;
        this.cost = cost;
    }
}
