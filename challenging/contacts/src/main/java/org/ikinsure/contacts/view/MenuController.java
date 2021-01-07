package org.ikinsure.contacts.view;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * Class controls menus and views
 */
public class MenuController {

    private final ArrayDeque<Menu> menus;
    private final Scanner scanner;
    private String lastId;

    public MenuController(Scanner scanner) {
        this.menus = new ArrayDeque<>();
        this.scanner = scanner;
    }

    // customize run method
    public void run(Menu main) {
        menus.offerLast(main);
        while (!menus.isEmpty()) {
            System.out.print(menus.getLast().getMessage());
            lastId = scanner.nextLine();
            if (lastId.matches("-?\\d+")) {
                menus.getLast().selectItem("[number]").action.execute();
                continue;
            }
            menus.getLast().selectItem(lastId).action.execute();
        }
    }

    public String getLastId() {
        return lastId;
    }

    public void setMenu(Menu menu) {
        menus.pollLast();
        menus.offerLast(menu);
    }

    public void addMenu(Menu menu) {
        menus.offerLast(menu);
    }

    public void exitMenu() {
        menus.pollLast();
    }

    public void exitAll() {
        menus.clear();
    }
}
