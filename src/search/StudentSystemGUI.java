package search;

import model.Student;
import service.BinarySearchTree;
import service.LinearSearch;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StudentSystemGUI extends JFrame {
    private BinarySearchTree<Student> bst = new BinarySearchTree<>();
    private List<Student> studentList = new ArrayList<>();   // for linear search

    private JTextField tfId, tfRoll, tfName, tfFee;
    private JTextArea taDisplay;
    private JTextField tfSearchId, tfSearchFee;

    public StudentSystemGUI() {
        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add Student"));
        inputPanel.add(new JLabel("Student ID:"));
        tfId = new JTextField();
        inputPanel.add(tfId);
        inputPanel.add(new JLabel("Roll No:"));
        tfRoll = new JTextField();
        inputPanel.add(tfRoll);
        inputPanel.add(new JLabel("Name:"));
        tfName = new JTextField();
        inputPanel.add(tfName);
        inputPanel.add(new JLabel("Fee:"));
        tfFee = new JTextField();
        inputPanel.add(tfFee);

        JButton btnAdd = new JButton("Add Student");
        inputPanel.add(btnAdd);

        // Search panel
        JPanel searchPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search"));
        searchPanel.add(new JLabel("By ID:"));
        tfSearchId = new JTextField();
        searchPanel.add(tfSearchId);
        JButton btnSearchId = new JButton("Search ID (BST)");
        searchPanel.add(btnSearchId);

        searchPanel.add(new JLabel("By Fee:"));
        tfSearchFee = new JTextField();
        searchPanel.add(tfSearchFee);
        JButton btnSearchFee = new JButton("Search Fee (Linear)");
        searchPanel.add(btnSearchFee);

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(inputPanel);
        topPanel.add(searchPanel);
        add(topPanel, BorderLayout.NORTH);

        // Display area
        taDisplay = new JTextArea(10, 40);
        taDisplay.setEditable(false);
        add(new JScrollPane(taDisplay), BorderLayout.CENTER);

        // Button actions
        btnAdd.addActionListener(e -> addStudent());
        btnSearchId.addActionListener(e -> searchById());
        btnSearchFee.addActionListener(e -> searchByFee());

        setVisible(true);
    }

    private void addStudent() {
        try {
            int id = Integer.parseInt(tfId.getText().trim());
            String roll = tfRoll.getText().trim();
            String name = tfName.getText().trim();
            double fee = Double.parseDouble(tfFee.getText().trim());

            Student s = new Student(id, roll, name, fee);
            bst.insert(s);
            studentList.add(s);
            taDisplay.setText("Added: " + s + "\n");
            clearInputFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format!");
        }
    }

    private void searchById() {
        try {
            int id = Integer.parseInt(tfSearchId.getText().trim());
            // Create a dummy Student for BST search (comparison uses studentId only)
            Student key = new Student(id, "", "", 0.0);
            Student found = bst.search(key);
            if (found != null) {
                taDisplay.setText("Found (BST): " + found);
            } else {
                taDisplay.setText("No student with ID " + id);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid integer ID");
        }
    }

    private void searchByFee() {
        try {
            double fee = Double.parseDouble(tfSearchFee.getText().trim());
            List<Student> result = LinearSearch.findByFee(studentList, fee);
            if (result.isEmpty()) {
                taDisplay.setText("No student with fee " + fee);
            } else {
                StringBuilder sb = new StringBuilder("Students with fee " + fee + ":\n");
                for (Student s : result) {
                    sb.append(s).append("\n");
                }
                taDisplay.setText(sb.toString());
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid fee amount");
        }
    }

    private void clearInputFields() {
        tfId.setText("");
        tfRoll.setText("");
        tfName.setText("");
        tfFee.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentSystemGUI::new);
    }
}