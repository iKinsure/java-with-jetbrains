package org.ikinsure.editor;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    private final JMenu menu;
    private final JMenuItem load;
    private final JMenuItem save;
    private final JMenuItem exit;

    public MenuBar() {
        menu = new JMenu();
        load = new JMenuItem();
        save = new JMenuItem();
        exit = new JMenuItem();
        config();
    }

    private void config() {
        menu.setName("MenuFile");
        menu.setText("File");
        load.setName("MenuLoad");
        load.setText("Load");
        save.setName("MenuSave");
        save.setText("Save");
        exit.setName("MenuExit");
        exit.setText("Exit");

        menu.add(load);
        menu.add(save);
        menu.add(exit);

        add(menu);
    }

    public JMenu getMenu() {
        return menu;
    }

    public JMenuItem getLoad() {
        return load;
    }

    public JMenuItem getSave() {
        return save;
    }

    public JMenuItem getExit() {
        return exit;
    }
}
