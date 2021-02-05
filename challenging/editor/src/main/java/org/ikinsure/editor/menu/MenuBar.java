package org.ikinsure.editor.menu;

import org.ikinsure.editor.Colors;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    private final FileMenu fileMenu;
    private final SearchMenu searchMenu;

    public MenuBar() {
        fileMenu = new FileMenu();
        searchMenu = new SearchMenu();
        config();
    }

    private void config() {
        setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Colors.LINES));
        setBackground(Colors.TOOLBAR);
        addMenu(fileMenu, "MenuFile", "File");
        addMenu(searchMenu, "MenuSearch", "Search");
    }

    private void addMenu(JMenu menu, String name, String text) {
        menu.setName(name);
        menu.setText(text);
        add(menu);
    }

    public FileMenu getFileMenu() {
        return fileMenu;
    }

    public SearchMenu getSearchMenu() {
        return searchMenu;
    }
}
