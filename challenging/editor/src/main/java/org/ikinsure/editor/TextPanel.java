package org.ikinsure.editor;

import javax.swing.*;

public class TextPanel extends JPanel {

    private final JTextArea area;
    private final JScrollPane scrollPane;

    public TextPanel() {
        area = new JTextArea();
        scrollPane = new JScrollPane(area);
        config();
    }

    private void config() {
        area.setName("TextArea");
        scrollPane.setName("ScrollPane");
        var layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        layout.setHorizontalGroup(layout
                .createParallelGroup()
                .addComponent(scrollPane));
        layout.setVerticalGroup(layout
                .createSequentialGroup()
                .addComponent(scrollPane));
        add(scrollPane);
    }

    public JTextArea getArea() {
        return area;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}
