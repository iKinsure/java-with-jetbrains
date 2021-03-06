package org.ikinsure.editor.view.menu;

import javax.swing.*;

public class SearchMenu extends JMenu {

    private final JMenuItem start;
    private final JMenuItem previous;
    private final JMenuItem next;
    private final JMenuItem regex;
    private final JMenuItem ignoreCase;

    public SearchMenu() {
        start = new JMenuItem();
        previous = new JMenuItem();
        next = new JMenuItem();
        regex = new JMenuItem();
        ignoreCase = new JMenuItem();
        config();
    }

    private void config() {
        addItem(start, "MenuStartSearch", "Start search");
        addItem(previous, "MenuPreviousMatch", "Previous match");
        addItem(next, "MenuNextMatch", "Next match");
        addItem(regex, "MenuUseRegExp", "Use regular expressions");
        addItem(ignoreCase, "MenuIgnoreCase", "Ignore case");
    }

    private void addItem(JMenuItem item, String name, String text) {
        item.setName(name);
        item.setText(text);
        add(item);
    }

    public JMenuItem getStart() {
        return start;
    }

    public JMenuItem getPrevious() {
        return previous;
    }

    public JMenuItem getNext() {
        return next;
    }

    public JMenuItem getRegex() {
        return regex;
    }

    public JMenuItem getIgnoreCase() {
        return ignoreCase;
    }
}
