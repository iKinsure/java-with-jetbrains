package org.ikinsure.editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor extends JFrame {

    private final OptionPanel optionPanel;
    private final TextPanel textPanel;
    private final MenuBar menuBar;

    public TextEditor() {
        optionPanel = new OptionPanel();
        textPanel = new TextPanel();
        menuBar = new MenuBar();
        config();
    }

    private void config() {
        setJMenuBar(menuBar);
        setLayout(new BorderLayout());
        add(optionPanel, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);

        ActionListener loadAction = e -> {
            String filename = optionPanel.getFile().getText();
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                StringBuilder builder = new StringBuilder();
                int sign;
                while ((sign = reader.read()) != -1) {
                    builder.append((char) sign);
                }
                textPanel.getArea().setText(builder.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
                textPanel.getArea().setText("");
            }
        };

        ActionListener saveAction = e -> {
            String filename = optionPanel.getFile().getText();
            String data = textPanel.getArea().getText();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                writer.write(data);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        };

        optionPanel.getLoad().addActionListener(loadAction);
        optionPanel.getSave().addActionListener(saveAction);
        menuBar.getLoad().addActionListener(loadAction);
        menuBar.getSave().addActionListener(saveAction);
        menuBar.getExit().addActionListener(e -> System.exit(0));

        setFrame();
    }

    private void setFrame() {
        setTitle("Text Editor");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
