package org.ikinsure.editor;

import org.ikinsure.editor.view.Frame;

import java.awt.*;

public class ApplicationRunner {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            org.ikinsure.editor.view.Frame view = new Frame();
            Controller controller = new Controller(view);
            controller.setListeners();
        });
    }
}
