package search;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentSystemGUI extends JFrame {
    private final List<Student> studentList = new ArrayList<>();

    // Input fields
    private JTextField tfStudentId, tfRollNo, tfName, tfFee;
    private JTextField tfSearchFee, tfSearchId;
    private JTextArea taOutput;

    public StudentSystemGUI() {
        setTitle("Student Search System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(new BorderLayout());

        // Input panel (top)
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add Student"));

        inputPanel.add(new JLabel("Student ID:"));
        tfStudentId = new JTextField();
        inputPanel.add(tfStudentId);

        inputPanel.add(new JLabel("Roll No:"));
        tfRollNo = new JTextField();
        inputPanel.add(tfRollNo);

        inputPanel.add(new JLabel("Name:"));
        tfName = new JTextField();
        inputPanel.add(tfName);

        inputPanel.add(new JLabel("Tuition Fee:"));
        tfFee = new JTextField();
        inputPanel.add(tfFee);

        JButton btnAdd = new JButton("Add Student");
        inputPanel.add(btnAdd);
        inputPanel.add(new JLabel("")); // placeholder

        add(inputPanel, BorderLayout.NORTH);

        // Search panel (center)
        JPanel searchPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search"));

        searchPanel.add(new JLabel("Fee to search (linear):"));
        tfSearchFee = new JTextField();
        searchPanel.add(tfSearchFee);
        JButton btnLinearSearch = new JButton("Linear Search (Fee)");
        searchPanel.add(btnLinearSearch);
        searchPanel.add(new JLabel(""));

        searchPanel.add(new JLabel("Student ID to search (binary):"));
        tfSearchId = new JTextField();
        searchPanel.add(tfSearchId);
        JButton btnBinarySearch = new JButton("Binary Search (ID)");
        searchPanel.add(btnBinarySearch);
        searchPanel.add(new JLabel(""));

        add(searchPanel, BorderLayout.CENTER);

        // Output area (bottom)
        taOutput = new JTextArea(10, 40);
        taOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taOutput);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Results"));
        add(scrollPane, BorderLayout.SOUTH);

        // --- Event handlers ---
        btnAdd.addActionListener(this::addStudent);
        btnLinearSearch.addActionListener(this::doLinearSearch);
        btnBinarySearch.addActionListener(this::doBinarySearch);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addStudent(ActionEvent e) {
        try {
            int id = Integer.parseInt(tfStudentId.getText().trim());
            int roll = Integer.parseInt(tfRollNo.getText().trim());
            String name = tfName.getText().trim();
            double fee = Double.parseDouble(tfFee.getText().trim());

            if (name.isEmpty()) {
                showError("Name cannot be empty.");
                return;
            }

            studentList.add(new Student(id, roll, name, fee));
            taOutput.setText("Student added:\n" + studentList.get(studentList.size() - 1));
            clearInputFields();
        } catch (NumberFormatException ex) {
            showError("Invalid number input. Please check ID, roll, and fee.");
        }
    }

    private void doLinearSearch(ActionEvent e) {
        if (studentList.isEmpty()) {
            taOutput.setText("No students in the list.");
            return;
        }
        try {
            double fee = Double.parseDouble(tfSearchFee.getText().trim());
            List<Student> found = SearchUtils.linearSearchByFee(studentList, fee);
            if (found.isEmpty()) {
                taOutput.setText("No student with fee = " + fee);
            } else {
                StringBuilder sb = new StringBuilder("Students with fee = " + fee + ":\n");
                for (Student s : found) {
                    sb.append(s).append("\n");
                }
                taOutput.setText(sb.toString());
            }
        } catch (NumberFormatException ex) {
            showError("Invalid fee value.");
        }
    }

    private void doBinarySearch(ActionEvent e) {
        if (studentList.isEmpty()) {
            taOutput.setText("No students in the list.");
            return;
        }
        try {
            int id = Integer.parseInt(tfSearchId.getText().trim());

            // Sort the list by studentId (binary search prerequisite)
            List<Student> sortedList = new ArrayList<>(studentList);
            Collections.sort(sortedList);   // uses Student.compareTo

            Student found = SearchUtils.binarySearchById(sortedList, id);
            if (found == null) {
                taOutput.setText("Student with ID " + id + " not found.");
            } else {
                taOutput.setText("Found:\n" + found);
            }
        } catch (NumberFormatException ex) {
            showError("Invalid Student ID.");
        }
    }

    private void clearInputFields() {
        tfStudentId.setText("");
        tfRollNo.setText("");
        tfName.setText("");
        tfFee.setText("");
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentSystemGUI::new);
    }
}