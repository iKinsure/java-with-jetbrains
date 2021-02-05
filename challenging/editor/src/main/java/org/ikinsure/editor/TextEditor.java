package org.ikinsure.editor;

import org.ikinsure.editor.menu.MenuBar;
import org.ikinsure.editor.pane.OptionPanel;
import org.ikinsure.editor.pane.TextPanel;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;

public class TextEditor extends JFrame {

    private final OptionPanel optionPanel;
    private final TextPanel textPanel;
    private final MenuBar menu;
    private final JFileChooser chooser;

    public TextEditor() {
        optionPanel = new OptionPanel();
        textPanel = new TextPanel();
        menu = new MenuBar();
        chooser = new JFileChooser();
        config();
    }

    private void config() {
        setJMenuBar(menu);
        setLayout(new BorderLayout());
        add(optionPanel, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
        chooser.setName("FileChooser");
        chooser.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
        setFrame();
    }

    private void setFrame() {
        setBackground(Colors.MAIN);
        setTitle("Text Editor");
        setSize(500, 350);
        setMinimumSize(new Dimension(350, 160));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public OptionPanel getOptionPanel() {
        return optionPanel;
    }

    public TextPanel getTextPanel() {
        return textPanel;
    }

    public MenuBar getMenu() {
        return menu;
    }

    public JFileChooser getChooser() {
        return chooser;
    }
}
