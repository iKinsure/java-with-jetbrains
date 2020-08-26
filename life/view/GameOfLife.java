package life.view;

import life.model.Universe;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GameOfLife extends JFrame {

    private JLabel aliveLabel;
    private JLabel generationLabel;
    private Universe universe;
    private GraphPane graphPane;

    public GameOfLife() {
        setFrame();
        setLabelPane();
        setContentPane();
        setVisible(true);
    }

    private void setFrame() {
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setName("Game of Life");
        setTitle("Game of Life");
        setLocationByPlatform(true);
        setResizable(false);
        setLayout(new BorderLayout());
    }

    private void setContentPane() {
        graphPane = new GraphPane();
        add(graphPane, BorderLayout.CENTER);
    }

    private void setLabelPane() {
        var pane = new JPanel();
        pane.setLayout(new GridLayout(2,1));

        generationLabel = new JLabel();
        generationLabel.setName("GenerationLabel");
        generationLabel.setText("Generation #0");
        generationLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        pane.add(generationLabel);

        aliveLabel = new JLabel();
        aliveLabel.setName("AliveLabel");
        aliveLabel.setText("Alive: 0");
        aliveLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 0));
        pane.add(aliveLabel);

        add(pane, BorderLayout.NORTH);
    }

    public class GraphPane extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graph = (Graphics2D) g;
            boolean[][] array = universe.getCurrent();
            for (int y = array.length - 1; y >= 0; y--) {
                for (int x = 0; x < array.length; x++) {
                    var square = new Rectangle2D.Double(x * 20, y * 20, 20, 20);
                    graph.setColor(Color.BLACK);
                    if (array[x][y]) {
                        graph.fill(square);
                    }
                    graph.draw(square);
                }
            }
        }
    }

    public void setUniverse(Universe universe) {
        this.universe = universe;
    }

    public JLabel getAliveLabel() {
        return aliveLabel;
    }

    public JLabel getGenerationLabel() {
        return generationLabel;
    }

    public GraphPane getGraphPane() {
        return graphPane;
    }
}
