package org.ikinsure.editor.view.pane;

import org.ikinsure.editor.view.Colors;

import javax.swing.*;

public class TextPanel extends JPanel {

    private final JTextArea area;
    private final JScrollPane scrollPane;

    public TextPanel() {
        area = new JTextArea();
        scrollPane = new JScrollPane();
        config();
    }

    private void config() {
        setBackground(Colors.MAIN);
        setBorder(BorderFactory.createEmptyBorder());
        area.setForeground(Colors.TEXT);
        area.setBackground(Colors.AREA);
        area.setBorder(BorderFactory.createLineBorder(Colors.LINES, 1, false));
        area.setName("TextArea");
        scrollPane.setName("ScrollPane");
        scrollPane.setViewportView(area);
        var layout = new GroupLayout(this);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        layout.setHorizontalGroup(layout
                .createParallelGroup()
                .addComponent(scrollPane));
        layout.setVerticalGroup(layout
                .createSequentialGroup()
                .addComponent(scrollPane));
        setLayout(layout);
        add(scrollPane);
    }

    public JTextArea getArea() {
        return area;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}
