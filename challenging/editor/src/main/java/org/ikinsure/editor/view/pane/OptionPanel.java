package org.ikinsure.editor.view.pane;

import org.ikinsure.editor.view.Colors;
import org.ikinsure.editor.view.Icons;

import javax.swing.*;

public class OptionPanel extends JPanel {

    private final JButton open;
    private final JButton save;
    private final JTextField console;
    private final JButton search;
    private final JButton previous;
    private final JButton next;
    private final JCheckBox regex;
    private final JLabel labelRegex;
    private final JCheckBox searchCase;
    private final JLabel labelCase;

    public OptionPanel() {
        open = new JButton();
        save = new JButton();
        console = new JTextField();
        search = new JButton();
        previous = new JButton();
        next = new JButton();
        regex = new JCheckBox();
        labelRegex = new JLabel();
        searchCase = new JCheckBox();
        labelCase = new JLabel();
        config();
    }

    private void config() {
        setBackground(Colors.TOOL);
        setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Colors.LINES));
        var layout = new GroupLayout(this);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        layout.setHorizontalGroup(layout
                .createSequentialGroup()
                .addComponent(open)
                .addComponent(save)
                .addComponent(console, 40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(search)
                .addComponent(previous)
                .addComponent(next)
                .addComponent(regex)
                .addComponent(labelRegex)
                .addComponent(searchCase)
                .addComponent(labelCase)
        );
        layout.setVerticalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(open)
                .addComponent(save)
                .addComponent(console, 25, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(search)
                .addComponent(previous)
                .addComponent(next)
                .addComponent(regex)
                .addComponent(labelRegex)
                .addComponent(searchCase)
                .addComponent(labelCase)
        );
        setLayout(layout);
        addButton(open, "OpenButton", Icons.OPEN.getIcon());
        addButton(save, "SaveButton", Icons.SAVE.getIcon());
        addButton(search, "StartSearchButton", Icons.SEARCH.getIcon());
        addButton(previous, "PreviousMatchButton", Icons.PREV.getIcon());
        addButton(next, "NextMatchButton", Icons.NEXT.getIcon());
        regex.setName("UseRegExCheckbox");
        labelRegex.setText("Use regex");
        labelCase.setText("Ignore case");
        regex.setBackground(Colors.TOOL);
        searchCase.setBackground(Colors.TOOL);
        console.setBorder(BorderFactory.createLineBorder(Colors.LINES, 1, false));
        console.setName("SearchField");
        add(console);
        add(regex);
        add(labelRegex);
        add(searchCase);
        add(labelCase);
    }

    private void addButton(JButton button, String name, ImageIcon icon) {
        button.setName(name);
        button.setIcon(icon);
        button.setBackground(Colors.MAIN);
        button.setBorder(BorderFactory.createEmptyBorder());
        add(button);
    }

    public JButton getOpen() {
        return open;
    }

    public JButton getSave() {
        return save;
    }

    public JTextField getConsole() {
        return console;
    }

    public JButton getSearch() {
        return search;
    }

    public JButton getPrevious() {
        return previous;
    }

    public JButton getNext() {
        return next;
    }

    public JCheckBox getRegex() {
        return regex;
    }

    public JLabel getLabelRegex() {
        return labelRegex;
    }

    public JCheckBox getSearchCase() {
        return searchCase;
    }

    public JLabel getLabelCase() {
        return labelCase;
    }
}
