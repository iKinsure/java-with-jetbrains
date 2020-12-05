package org.ikinsure.hard.banking.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Menu represents "take a decision" action with printed list of possibilities
 */
public class Menu {

    private final List<Item> items; // list of options
    private Scanner scanner; // linked input

    public Menu() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Item selectItem() {
        items.forEach(System.out::println); // printing menu, MenuItem.toString()
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println();
        return items.stream()
                .filter(i -> i.id == id)
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

        public Builder setScanner(Scanner scanner) {
            menu.setScanner(scanner);
            return this;
        }

        public Builder addItem(Menu.Item item) {
            menu.addItem(item);
            return this;
        }

        public Builder addItem(int id, String name, MenuAction action) {
            return addItem(new Menu.Item(id, name, action));
        }

        public Menu build() {
            return menu;
        }
    }

    /**
     * Static class provides a row linked with text and special action
     */
    static class Item {

        final int id;
        final String name;
        final MenuAction action;

        public Item(int id, String name, MenuAction action) {
            this.id = id;
            this.name = name;
            this.action = action;
        }

        @Override // method is used when printing items
        public String toString() {
            return id + ". " + name;
        }
    }
}
