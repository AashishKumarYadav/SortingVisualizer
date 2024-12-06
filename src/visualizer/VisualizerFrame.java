package visualizer;
import javax.swing.*;
import java.awt.*;

public class VisualizerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private VisualizerPanel visualizerPanel;
    private JSlider arraySizeSlider;
    private JSlider speedSlider;

    public VisualizerFrame() {
        setTitle("Sorting Visualizer");
        visualizerPanel = new VisualizerPanel();
        add(visualizerPanel, BorderLayout.CENTER);

        // Create control panel
        JPanel controlPanel = new JPanel();
        JButton generateButton = new JButton("Generate Array");
        JButton bubbleSortButton = new JButton("Bubble Sort");
        JButton selectionSortButton = new JButton("Selection Sort");
        JButton insertionSortButton = new JButton("Insertion Sort");
        JButton quickSortButton = new JButton("Quick Sort");
        JButton mergeSortButton = new JButton("Merge Sort");
      
      

        // Add sliders
        arraySizeSlider = new JSlider(10, 100, 30); // Min 10ms, Max 100ms, Initial 30
        speedSlider = new JSlider(5, 500, 100); // Max 10ms, Min 1000ms, Initial 100ms


        // Label the sliders
        JLabel arraySizeLabel = new JLabel("Array Size (Min - Max) ");
        JLabel speedLabel = new JLabel("Speed (ms) - (Max - Min)");

        // Adjust slider properties
        arraySizeSlider.setMajorTickSpacing(10);
        arraySizeSlider.setPaintTicks(true);
        arraySizeSlider.setPaintLabels(true);

        speedSlider.setMajorTickSpacing(200);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        
        controlPanel.setBorder(BorderFactory.createEmptyBorder(30, 5, 30, 5)); // Add padding
        
        visualizerPanel.setSliders(speedSlider);

        // Add actions
        generateButton.addActionListener(e -> visualizerPanel.generateArray(arraySizeSlider.getValue()));
        bubbleSortButton.addActionListener(e -> new Thread(() -> visualizerPanel.bubbleSort()).start());
        selectionSortButton.addActionListener(e -> new Thread(() -> visualizerPanel.selectionSort()).start());
        insertionSortButton.addActionListener(e -> new Thread(() -> visualizerPanel.insertionSort()).start());
        quickSortButton.addActionListener(e -> new Thread(() -> visualizerPanel.quickSort()).start());
        mergeSortButton.addActionListener(e -> new Thread(() -> visualizerPanel.mergeSort()).start());

        // Add components to control panel
        controlPanel.add(generateButton);
        controlPanel.add(bubbleSortButton);
        controlPanel.add(selectionSortButton);
        controlPanel.add(insertionSortButton);
        controlPanel.add(quickSortButton);
        controlPanel.add(mergeSortButton);
      
        controlPanel.add(Box.createVerticalStrut(10)); 
        controlPanel.add(arraySizeLabel);
        controlPanel.add(arraySizeSlider);
        controlPanel.add(speedLabel);
        controlPanel.add(speedSlider);

        add(controlPanel, BorderLayout.SOUTH);
    }
}
