package life.view;

import life.model.Universe;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GameOfLife extends JFrame {

    private JLabel aliveLabel;
    private JLabel generationLabel;

    public GameOfLife() {
        this.setSize(420, 475);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setName("Game of Life");
        this.setLocationByPlatform(true);
        this.setResizable(false);

        var pane = new Draw();
        this.add(pane);
        init(pane);


        this.setVisible(true);
    }

    public void init(JPanel pane) {
        pane.setLayout(null);
        generationLabel = new JLabel();
        generationLabel.setName("GenerationLabel");
        generationLabel.setText("Generation #");
        generationLabel.setBounds(10, 5, 80, 25);
        pane.add(generationLabel);
        aliveLabel = new JLabel();
        aliveLabel.setName("AliveLabel");
        aliveLabel.setText("Alive: ");
        aliveLabel.setBounds(120, 5, 80, 25);
        pane.add(aliveLabel);
    }

    public static class Draw extends JPanel {

        private static Universe universe;

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graph = (Graphics2D) g;
            boolean[][] array = universe.getCurrent();
            for (int y = array.length - 1; y >= 0; y--) {
                for (int x = 0; x < array.length; x++) {
                    var square = new Rectangle2D.Double(x * 20, y * 20 + 30, 20, 20);
                    graph.setColor(Color.BLACK);
                    if (array[x][y]) {
                        graph.fill(square);
                    }
                    graph.draw(square);
                }
            }
        }

        public static void setUniverse(Universe u) {
            universe = u;
        }
    }

    public JLabel getAliveLabel() {
        return aliveLabel;
    }

    public JLabel getGenerationLabel() {
        return generationLabel;
    }
}
