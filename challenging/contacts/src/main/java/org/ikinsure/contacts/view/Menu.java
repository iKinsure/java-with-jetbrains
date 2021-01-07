package org.ikinsure.contacts.view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Menu represents "take a decision" action with printed list of possibilities
 */
public class Menu {

    private final List<Item> items; // list of options
    private final String message;

    public Menu(String message) {
        this.items = new ArrayList<>();
        this.message = message;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item selectItem(final String id) {
        return items.stream().filter(i -> i.id.equals(id)).findFirst().orElse(Item.error);
    }

    public String getMessage() {
        return "\n[" + message + "] Enter action (" + items.stream().map(i -> i.id).collect(Collectors.joining(", ")) + "): ";
    }

    /**
     * Static class for building menu, builder pattern
     */
    public static class Builder {

        private final Menu menu;

        public Builder(String message) {
            menu = new Menu(message);
        }

        public Builder addItem(Item item) {
            menu.addItem(item);
            return this;
        }

        public Builder addItem(String id, MenuAction action) {
            return addItem(new Item(id, action));
        }

        public Menu build() {
            return menu;
        }
    }

    /**
     * Static class provides a row linked with text and special action
     */
    static class Item {

        final String id;
        final MenuAction action;

        private static final Item error = new Item("error", () -> System.out.println("[error] Incorrect input! Try again."));

        public Item(String id, MenuAction action) {
            this.id = id;
            this.action = action;
        }

        @Override // method is used when printing items
        public String toString() {
            return id;
        }
    }
}
