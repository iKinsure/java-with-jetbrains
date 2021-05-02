package org.ikinsure.machine;

public enum Ingredient {

    WATER(
            "Write how many ml of water do you want to add: ",
            "Sorry, not enough water!",
            "%d of water"
    ),
    MILK(
            "Write how many ml of milk do you want to add: ",
            "Sorry, not enough milk!",
            "%d of milk"
    ),
    BEANS(
            "Write how many grams of coffee beans do you want to add: ",
            "Sorry, not enough coffee beans!",
            "%d of coffee beans"
    ),
    CUPS(
            "Write how many disposable cups of coffee do you want to add: ",
            "Sorry, not enough cups!",
            "%d of disposable cups"
    );

    public final String addMsg;
    public final String emptyMsg;
    public final String format;

    Ingredient(String addMsg, String emptyMsg, String format) {
        this.addMsg = addMsg;
        this.emptyMsg = emptyMsg;
        this.format = format;
    }

}
