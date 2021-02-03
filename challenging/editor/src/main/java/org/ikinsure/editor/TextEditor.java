package org.ikinsure.editor;

import javax.swing.*;

public class TextEditor extends JFrame {

    public TextEditor() {
        var area = new JTextArea();
        var pane = new JScrollPane(area);
        area.setName("TextArea");
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        createLayout(pane);
        setFrame();
    }

    private void setFrame() {
        setTitle("The first stage");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createLayout(JComponent... arg) {
        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);
        gl.setHorizontalGroup(gl.createParallelGroup().addComponent(arg[0]));
        gl.setVerticalGroup(gl.createSequentialGroup().addComponent(arg[0]));
        pack();
    }
}
