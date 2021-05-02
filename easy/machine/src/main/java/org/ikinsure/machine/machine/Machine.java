package org.ikinsure.machine.machine;

public interface Machine {

    void buy();
    void fill();
    void take();
    void remaining();

    String supplier();
    void consumer(String msg);

    default void run() {
        while (action()) {}
    }

    private boolean action() {
        consumer("Write action (buy, fill, take, remaining, exit): ");
        switch (supplier()) {
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
}
