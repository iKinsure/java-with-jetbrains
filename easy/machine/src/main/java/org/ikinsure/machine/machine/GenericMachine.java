package org.ikinsure.machine.machine;

import org.ikinsure.machine.Ingredient;
import org.ikinsure.machine.Product;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GenericMachine implements Machine {

    private final String category;
    private final Supplier<String> supplier;
    private final Consumer<String> consumer;
    private final Map<Ingredient, Integer> supplies;
    private final Product[] products;
    private int money;

    public GenericMachine(String category,
                         Supplier<String> supplier,
                         Consumer<String> consumer,
                         Map<Ingredient, Integer> supplies,
                         Product[] products,
                         int money) {
        this.category = category;
        this.supplier = supplier;
        this.consumer = consumer;
        this.supplies = supplies;
        this.products = products;
        this.money = money;
    }

    @Override
    public void remaining() {
        supplies.forEach((k, v) -> consumer(String.format(k.format, v)));
        consumer("$" + money + " of money");
    }

    @Override
    public void take() {
        consumer("I gave you $" + money);
        money = 0;
    }

    @Override
    public void fill() {
        supplies.forEach((k, v) -> {
            consumer(k.addMsg);
            supplies.replace(k, v + intSupplier());
        });
    }

    @Override
    public void buy() {

        consumer("What do you want to buy? " + list() + ", back - to main menu: ");
        Product product = products[intSupplier() - 1];

        // checking if there are enough supplies
        for (var ingredient : product.ingredients.entrySet()) {
            if (supplies.getOrDefault(ingredient.getKey(), 0) < ingredient.getValue()) {
                consumer(ingredient.getKey().emptyMsg);
                return;
            }
        }

        updateSupplies(product);
        consumer("I have enough resources, making you a " + category + "!");
        money += product.cost;
    }

    // ===============================================================================

    @Override
    public String supplier() {
        return supplier.get();
    }

    private int intSupplier() {
        return Integer.parseInt(supplier());
    }

    @Override
    public void consumer(String msg) {
        consumer.accept(msg);
    }

    private void updateSupplies(Product product) {
        for (var entry : supplies.entrySet()) {
            int value = entry.getValue() - product.ingredients.getOrDefault(entry.getKey(), 0);
            entry.setValue(value);
        }
    }

    private String list() {
        return IntStream.range(0, products.length)
                .mapToObj(i -> (i + 1) + " - " + products[i].name().toLowerCase())
                .collect(Collectors.joining(", "));
    }

}
