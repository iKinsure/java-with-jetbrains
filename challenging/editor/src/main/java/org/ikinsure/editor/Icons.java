package org.ikinsure.editor;

import javax.swing.*;
import java.awt.*;

public enum Icons {

    NEXT("next.png"),
    OPEN("open.png"),
    PREV("previous.png"),
    SAVE("save.png"),
    SEARCH("search.png");

    private final ImageIcon icon;
    Icons(String filename) {
        ImageIcon icon = new ImageIcon(Icons.class.getResource("/icons/" + filename));
        Image image = icon.getImage();
        this.icon = new ImageIcon(image.getScaledInstance(25, 25, Image.SCALE_SMOOTH));
    }

    public ImageIcon getIcon() {
        return icon;
    }
}
