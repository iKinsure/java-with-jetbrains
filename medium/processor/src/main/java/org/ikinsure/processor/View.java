package org.ikinsure.processor;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class View {

    private final Consumer<String> consumer;
    private final Supplier<Integer> integerSupplier;
    private final Supplier<Double> doubleSupplier;

    public View(Consumer<String> consumer,
                Supplier<Integer> integerSupplier,
                Supplier<Double> doubleSupplier) {
        this.consumer = consumer;
        this.integerSupplier = integerSupplier;
        this.doubleSupplier = doubleSupplier;
    }

    public int nextInt() {
        return integerSupplier.get();
    }

    public double nextDouble() {
        return doubleSupplier.get();
    }

    public void out(String text) {
        consumer.accept(text);
    }
}
