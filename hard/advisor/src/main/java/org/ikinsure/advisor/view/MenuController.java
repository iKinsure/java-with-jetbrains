package org.ikinsure.advisor.view;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * Class controls menus and views
 */
public class MenuController {

    private final ArrayDeque<Menu> menus;
    private final String endMessage;
    private final Scanner scanner;
    private String lastInput;

    public MenuController(String endMessage, Scanner scanner) {
        this.menus = new ArrayDeque<>();
        this.endMessage = endMessage;
        this.scanner = scanner;
    }

    // customize run method
    public void run(Menu main) {
        menus.offerLast(main);
        while (!menus.isEmpty()) {
            this.lastInput = scanner.nextLine();
            String id = lastInput.split("\\s+")[0];
            menus.getLast().selectItem(id).action.execute();
        }
        System.out.println(endMessage);
    }

    public String getLastInput() {
        return lastInput;
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
