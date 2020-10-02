package medium.life.view;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {

    private JLabel aliveLabel;
    private JLabel generationLabel;
    private JSlider slider;
    private SimulationPane simulationPane;

    private boolean isPausePressed;
    private boolean isResetPressed;

    private static final int WIDTH = 500; //815;
    private static final int HEIGHT = 400; //650;


    public GameOfLife() {
        setFrame();
        setLabelPane();
        setSimulationPane();
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

    private void setSimulationPane() {
        simulationPane = new SimulationPane();
        simulationPane.setLayout(null);
        simulationPane.setBounds(195, 5, WIDTH - 195, HEIGHT);
        add(simulationPane);
    }

    private void setLabelPane() {
        // set pane
        var pane = new JPanel();
        pane.setLayout(null);
        pane.setBounds(0, 0, 195, HEIGHT);

        // toggle button
        JToggleButton pauseButton = new JToggleButton();
        pauseButton.setName("PlayToggleButton");
        pauseButton.setText("PAUSE");
        pauseButton.setBounds(5,5, 90, 50);
        pauseButton.addActionListener(e -> {
            isPausePressed = !isPausePressed;
            pauseButton.setText(isPausePressed ? "RESUME" : "PAUSE");
        });
        pane.add(pauseButton);

        // reset button
        JButton resetButton = new JButton();
        resetButton.setName("ResetButton");
        resetButton.setText("RESET");
        resetButton.setBounds(100,5, 90, 50);
        resetButton.addActionListener(e -> isResetPressed = true);
        pane.add(resetButton);

        // generation label
        generationLabel = new JLabel();
        generationLabel.setName("GenerationLabel");
        generationLabel.setText("Generation #0");
        generationLabel.setFont(new Font("Arial", Font.BOLD, 24));
        generationLabel.setBounds(5, 60, 195, 25);
        pane.add(generationLabel);

        // alive label
        aliveLabel = new JLabel();
        aliveLabel.setName("AliveLabel");
        aliveLabel.setText("Alive: 0");
        aliveLabel.setFont(new Font("Arial", Font.BOLD, 24));
        aliveLabel.setBounds(5, 90, 195, 25);
        pane.add(aliveLabel);

        // slider
        slider = new JSlider(50, 5000);
        slider.setValue(1000);
        slider.setToolTipText("Speed mode");
        slider.setBounds(5, 120, 190, 25);
        pane.add(slider);

        add(pane);
    }

    public JLabel getAliveLabel() {
        return aliveLabel;
    }

    public JLabel getGenerationLabel() {
        return generationLabel;
    }

    public SimulationPane getSimulationPane() {
        return simulationPane;
    }

    public JSlider getSlider() {
        return slider;
    }

    public boolean isPausePressed() {
        return isPausePressed;
    }

    public void setPausePressed(boolean pausePressed) {
        isPausePressed = pausePressed;
    }

    public boolean isResetPressed() {
        return isResetPressed;
    }

    public void setResetPressed(boolean resetPressed) {
        isResetPressed = resetPressed;
    }
}
