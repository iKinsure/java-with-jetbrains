package org.ikinsure.animals.view;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * Class controls menus and views
 */
public class MenuController {

    private final ArrayDeque<Menu> menus;

    public MenuController() {
        this.menus = new ArrayDeque<>();
    }

    public void run(Menu main) {
        menus.offerLast(main);
        while (!menus.isEmpty()) {
            menus.getLast().selectItem().action.execute();
        }
    }

    public void setMenu(Menu menu) {
        menus.offerLast(menu);
    }

    public void exitMenu() {
        menus.pollLast();
    }

    public void exitAll() {
        menus.clear();
    }
}
