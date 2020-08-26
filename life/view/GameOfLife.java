package life.view;

import life.model.Universe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

public class GameOfLife extends JFrame {

    private JLabel aliveLabel;
    private JLabel generationLabel;
    private Universe universe;
    private GraphPane graphPane;
    private boolean isPausePressed;

    private static final int WIDTH = 815;
    private static final int HEIGHT = 650;
    private static final int PIXEL_SIZE = 5;

    public GameOfLife() {
        setFrame();
        setLabelPane();
        setContentPane();
        setVisible(true);
    }

    private void setFrame() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setName("Game of Life");
        setTitle("Game of Life");
        setLocationByPlatform(true);
        setResizable(false);
        setLayout(null);
    }

    private void setContentPane() {
        graphPane = new GraphPane();
        graphPane.setLayout(null);
        graphPane.setBounds(195, 5, WIDTH - 195, HEIGHT);
        add(graphPane);
    }

    private void setLabelPane() {
        var pane = new JPanel();
        pane.setLayout(null);
        pane.setBounds(0, 0, 195, HEIGHT);


        JToggleButton pauseButton = new JToggleButton();
        pauseButton.setName("PlayToggleButton");
        pauseButton.setBounds(5,5, 90, 50);
        pauseButton.setText("PAUSE");
        pauseButton.addActionListener(e -> {
            isPausePressed = !isPausePressed;
            pauseButton.setText(isPausePressed ? "RESUME" : "PAUSE");
        });
        pane.add(pauseButton);

        JButton resetButton = new JButton();
        resetButton.setName("ResetButton");
        resetButton.setBounds(100,5, 90, 50);
        resetButton.setText("RESET");
        pane.add(resetButton);

        generationLabel = new JLabel();
        generationLabel.setName("GenerationLabel");
        generationLabel.setText("Generation #0");
        generationLabel.setFont(new Font("Arial", Font.BOLD, 24));
        generationLabel.setBounds(5, 60, 250, 25);
        pane.add(generationLabel, BorderLayout.CENTER);

        aliveLabel = new JLabel();
        aliveLabel.setName("AliveLabel");
        aliveLabel.setText("Alive: 0");
        aliveLabel.setFont(new Font("Arial", Font.BOLD, 24));
        aliveLabel.setBounds(5, 90, 250, 25);
        pane.add(aliveLabel,  BorderLayout.CENTER);

        add(pane, BorderLayout.WEST);
    }

    public class GraphPane extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
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
