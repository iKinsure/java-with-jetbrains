package org.ikinsure.editor;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class TextEditor extends JFrame {

    private final OptionPanel optionPanel;
    private final TextPanel textPanel;


    public TextEditor() {
        optionPanel = new OptionPanel();
        textPanel = new TextPanel();
        config();
    }

    private void config() {
        setLayout(new BorderLayout());
        add(optionPanel, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);

        optionPanel.getLoad().addActionListener(e -> {
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
        });

        optionPanel.getSave().addActionListener(e -> {
            String filename = optionPanel.getFile().getText();
            String data = textPanel.getArea().getText();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                writer.write(data);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        setFrame();
    }

    private void setFrame() {
        setTitle("Text Editor");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
