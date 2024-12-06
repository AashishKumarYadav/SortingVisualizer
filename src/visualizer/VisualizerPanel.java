package visualizer;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class VisualizerPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private int[] arr;
    private static final int BAR_Width = 25;
    private static final int ARRAY_Size = 30;
    private JSlider speedSlider;
    
    public VisualizerPanel() {
        generateArray(ARRAY_Size);
    }
    
    public void setSliders(JSlider speedSlider) {
        this.speedSlider = speedSlider;
    }
    
    public void generateArray(int size) {
        Random random = new Random();
        arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(400) + 50; // Random heights between 50 and 450
        }
        repaint();
    }
    
    public void bubbleSort() {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap values
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    // Repaint to show the swap
                    repaint();

                    // Pause for visualization
                    try {
                        Thread.sleep(speedSlider.getValue());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    
    public void selectionSort() {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;

            repaint();

            try {
                Thread.sleep(speedSlider.getValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    
    public void insertionSort() {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;

                repaint();

                try {
                    Thread.sleep(speedSlider.getValue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            arr[j + 1] = key;

            repaint();
        }
    }

    
    public void quickSort() {
        quickSortHelper(0, arr.length - 1);
    }

    private void quickSortHelper(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high);

            quickSortHelper(low, pivotIndex - 1);
            quickSortHelper(pivotIndex + 1, high);
        }
    }

    private int partition(int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        repaint();

        try {
            Thread.sleep(speedSlider.getValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    
    public void mergeSort() {
        mergeSortHelper(0, arr.length - 1);
    }

    private void mergeSortHelper(int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSortHelper(left, mid);
            mergeSortHelper(mid + 1, right);

            merge(left, mid, right);
        }
    }

    private void merge(int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (i = left, k = 0; i <= right; i++, k++) {
            arr[i] = temp[k];

            repaint();

            try {
                Thread.sleep(speedSlider.getValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (arr == null) return;

//        int panelWidth = getWidth();
        int panelHeight = getHeight();
        
        g.setFont(new Font("Arial", Font.BOLD, 10));


        for (int i = 0; i < arr.length; i++) {
            int x = i * BAR_Width;
            int height = arr[i];

            // Draw the bar
            g.setColor(Color.BLUE);
            g.fillRect(x, panelHeight - height, BAR_Width - 2, height);

            // Draw the value above the bar
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(arr[i]), x + BAR_Width / 4, panelHeight - height - 5);
        }
    }

}
