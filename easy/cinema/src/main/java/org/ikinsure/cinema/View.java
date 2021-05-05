package org.ikinsure.cinema;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class View {

    private final Consumer<String> consumer;
    private final Supplier<Integer> supplier;

    public View(Consumer<String> consumer, Supplier<Integer> supplier) {
        this.consumer = consumer;
        this.supplier = supplier;
    }

    public int inOut(String text) {
        out(text);
        return in();
    }

    public int in() {
        return supplier.get();
    }

    public void out(String text) {
        consumer.accept(text);
    }
}
