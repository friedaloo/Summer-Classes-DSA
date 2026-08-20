package sortingDemo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class SortingComparisonGUI extends JFrame {

    private DefaultTableModel model;
    private JTable table;
    private java.util.List<Student> students = new ArrayList<>();

    private JLabel bubbleLabel = new JLabel("Bubble Sort: -");
    private JLabel quickLabel = new JLabel("Quick Sort: -");

    public SortingComparisonGUI() {
        setTitle("Student Sorting Comparison - Bubble vs Quick Sort");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton generateBtn = new JButton("Generate 5000 Students");
        JButton compareBtn = new JButton("Compare Sorting");

        JPanel top = new JPanel();
        top.add(generateBtn);
        top.add(compareBtn);
        top.add(bubbleLabel);
        top.add(quickLabel);

        model = new DefaultTableModel(
                new String[]{"Rank", "Roll", "Name", "Marks"}, 0);

        table = new JTable(model);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        generateBtn.addActionListener(e -> generateStudents());
        compareBtn.addActionListener(e -> compareSorting());
    }

    private void generateStudents() {
        students.clear();
        Random rand = new Random();
        String[] name= {"ram", "hari", "sita", "human", "sammy", "sangla","cheparo","bunu","munni","akbar"};

        for(int i = 1;i<50;i++){
            students.add(new Student(i, name[rand.nextInt(name.length)], 20+rand.nextInt(70)));
        }

        }

    private void compareSorting() {

        List<Student> bubbleData = cloneList(students);
        List<Student> quickData = cloneList(students);

        long startBubble = System.nanoTime();
        bubbleSort(bubbleData);
        long endBubble = System.nanoTime();

        long startQuick = System.nanoTime();
        quickSort(quickData,0,quickData.size()-1);
        // quickData.sort((a,b)->Integer.compare(b.marks,a.marks));
        long endQuick = System.nanoTime();

        double bubbleMs = (endBubble-startBubble)/1_000_000.0;
        double quickMs = (endQuick-startQuick)/1_000_000.0;

        bubbleLabel.setText(
                String.format("Bubble Sort: %.3f ms", bubbleMs));

        quickLabel.setText(
                String.format("Quick Sort: %.3f ms", quickMs));

        showStudents(quickData);

        JOptionPane.showMessageDialog(this,
                "Bubble Sort = " + bubbleMs + " ms\n" +
                        "Quick Sort = " + quickMs + " ms");
    }

    private List<Student> cloneList(List<Student> src){
        List<Student> copy = new ArrayList<>();
        for(Student s:src){
            copy.add(new Student(s.roll,s.name,s.marks));
        }
        return copy;
    }

    private void showStudents(List<Student> list){

        model.setRowCount(0);

        int rank=1;

        for(Student s:list){
            model.addRow(new Object[]{
                    rank++,
                    s.roll,
                    s.name,
                    s.marks
            });
        }
    }

    private void bubbleSort(List<Student> arr){
        int noOfPass = arr.size() - 1;
        for(int i=0;i<noOfPass;i++);
        {
            boolean isSwap = false;
            for(int j=0;j<noOfPass-i-1;j++)
            {
                if(students.get(j).marks>students.get(j+1).marks)
                {
                    Student temp = students.get(j);
                    students.set(j, students.get(j+1));
                    students.set(j+1, temp);
                    isSwap=true;
                }
            }
        }
    }

    private void quickSort(List<Student> arr,int low,int high){


    }

    private int partition(List<Student> arr,int low,int high){

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new SortingComparisonGUI().setVisible(true));
    }
}

class Student{

    int roll;
    String name;
    int marks;

    Student(int roll,String name,int marks){
        this.roll=roll;
        this.name=name;
        this.marks=marks;
    }
}
