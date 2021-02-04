package org.ikinsure.editor;

import javax.swing.*;

public class OptionPanel extends JPanel {

    private final JTextField file;
    private final JButton save;
    private final JButton load;

    public OptionPanel() {
        file = new JTextField(10);
        save = new JButton("Save");
        load = new JButton("Load");
        config();
    }

    private void config() {
        var layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(layout
                .createSequentialGroup()
                .addComponent(file)
                .addComponent(save)
                .addComponent(load));
        layout.setVerticalGroup(layout
                .createParallelGroup()
                .addComponent(file)
                .addComponent(save)
                .addComponent(load));

        file.setName("FilenameField");
        save.setName("SaveButton");
        load.setName("LoadButton");

        add(file);
        add(save);
        add(load);
    }

    public JTextField getFile() {
        return file;
    }

    public JButton getSave() {
        return save;
    }

    public JButton getLoad() {
        return load;
    }
}
