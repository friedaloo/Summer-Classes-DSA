package search;

public class LinearSearch {

    public static Student searchByFee(Student[] students, double fee) {
        for (Student s : students) {
            if (s.getTuitionFee() == fee) {
                return s;
            }
        }
        return null;
    }
}