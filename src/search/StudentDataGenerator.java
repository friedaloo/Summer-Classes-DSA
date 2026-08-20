package sortingDemo_1;   // adjust if your package name differs

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class SortingComparisonGUI extends JFrame {
    private JTextArea outputArea;
    private JButton generateBtn, bubbleBtn, selectionBtn, insertionBtn;
    private int[] data;

    public SortingComparisonGUI() {
        setTitle("Sorting Comparison (Simplified)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ---- Output area ----
        outputArea = new JTextArea(20, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        // ---- Control panel ----
        JPanel controlPanel = new JPanel(new FlowLayout());
        generateBtn = new JButton("Generate Random Array");
        bubbleBtn = new JButton("Bubble Sort");
        selectionBtn = new JButton("Selection Sort");
        insertionBtn = new JButton("Insertion Sort");

        controlPanel.add(generateBtn);
        controlPanel.add(bubbleBtn);
        controlPanel.add(selectionBtn);
        controlPanel.add(insertionBtn);
        add(controlPanel, BorderLayout.NORTH);

        // ---- Button actions ----
        generateBtn.addActionListener(e -> generateArray());
        bubbleBtn.addActionListener(e -> sortAndDisplay("Bubble"));
        selectionBtn.addActionListener(e -> sortAndDisplay("Selection"));
        insertionBtn.addActionListener(e -> sortAndDisplay("Insertion"));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        generateArray(); // initial data
    }

    private void generateArray() {
        data = new int[20];  // size 20 for clarity
        for (int i = 0; i < data.length; i++) {
            data[i] = (int) (Math.random() * 100);
        }
        outputArea.setText("Generated array:\n" + Arrays.toString(data) + "\n\n");
    }

    private void sortAndDisplay(String algorithm) {
        if (data == null || data.length == 0) {
            outputArea.append("No data! Generate first.\n");
            return;
        }

        int[] copy = Arrays.copyOf(data, data.length);
        long start = System.nanoTime();

        switch (algorithm) {
            case "Bubble":
                bubbleSort(copy);
                break;
            case "Selection":
                selectionSort(copy);
                break;
            case "Insertion":
                insertionSort(copy);
                break;
        }

        long end = System.nanoTime();
        double timeMs = (end - start) / 1_000_000.0;

        outputArea.append(algorithm + " sort result:\n");
        outputArea.append(Arrays.toString(copy) + "\n");
        outputArea.append(String.format("Time: %.3f ms\n\n", timeMs));
    }

    // ---------- Sorting algorithms ----------
    private void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    private void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SortingComparisonGUI::new);
    }
}