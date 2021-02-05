package org.ikinsure.editor.view;

import javax.swing.*;
import java.awt.*;

public enum Icons {

    MAIN("main.png", 0, 0),
    NEXT("next.png", 25, 25),
    OPEN("open.png", 25, 25),
    PREV("previous.png",25, 25),
    SAVE("save.png", 25, 25),
    SEARCH("search.png", 25, 25);

    private final ImageIcon icon;
    Icons(String filename, int width, int height) {
        ImageIcon icon = new ImageIcon(Icons.class.getResource("/icons/" + filename));
        if (width != 0 && height != 0) {
            Image image = icon.getImage();
            this.icon = new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
        } else {
            this.icon = icon;
        }
    }

    public ImageIcon getIcon() {
        return icon;
    }
}
