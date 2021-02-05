package org.ikinsure.editor;

import java.awt.*;

public class ApplicationRunner {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            TextEditor view = new TextEditor();
            Controller controller = new Controller(view);
            controller.setListeners();
        });
    }
}
