package org.ikinsure.editor.view.menu;

import javax.swing.*;

public class FileMenu extends JMenu {

    private final JMenuItem open;
    private final JMenuItem save;
    private final JMenuItem exit;

    public FileMenu() {
        open = new JMenuItem();
        save = new JMenuItem();
        exit = new JMenuItem();
        config();
    }

    private void config() {
        addItem(open, "MenuOpen", "Load");
        addItem(save, "MenuSave", "Save");
        addSeparator();
        addItem(exit, "MenuExit", "Exit");
    }

    private void addItem(JMenuItem item, String name, String text) {
        item.setName(name);
        item.setText(text);
        add(item);
    }

    public JMenuItem getOpen() {
        return open;
    }

    public JMenuItem getSave() {
        return save;
    }

    public JMenuItem getExit() {
        return exit;
    }
}
