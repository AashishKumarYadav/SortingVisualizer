package visualizer;

import javax.swing.*;

public class SortingVisualizer {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VisualizerFrame frame = new VisualizerFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 700);
            frame.setVisible(true);
        });
    }
}