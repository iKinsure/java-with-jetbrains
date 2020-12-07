package org.ikinsure.life.view;

import org.ikinsure.life.model.Universe;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SimulationPane extends JPanel {

    private Universe universe;
    private static final int PIXEL_SIZE = 5;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graph = (Graphics2D) g;
        boolean[][] array = universe.getCurrent();
        int size = PIXEL_SIZE * (array.length - 1);
        for (int y = 0; y < array.length; y++) {
            for (int x = 0; x < array.length; x++) {
                var square = new Rectangle2D.Double(x * PIXEL_SIZE, size - y * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
                graph.setColor(Color.BLACK);
                if (array[x][y]) {
                    graph.fill(square);
                }
                graph.draw(square);
            }
        }
    }

    public void setUniverse(Universe universe) {
        this.universe = universe;
    }
}
