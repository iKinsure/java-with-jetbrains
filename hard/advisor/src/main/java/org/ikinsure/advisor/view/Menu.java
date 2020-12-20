package org.ikinsure.advisor.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Menu represents "take a decision" action with printed list of possibilities
 */
public class Menu {

    private final List<Item> items; // list of options

    public Menu() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item selectItem(final String id) {
        return items.stream()
                .filter(i -> i.id.equals(id))
                .findFirst()
                .orElse(items.get(items.size() - 1));
    }

    /**
     * Static class for building menu, builder pattern
     */
    public static class Builder {

        private final Menu menu;

        public Builder() {
            menu = new Menu();
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
