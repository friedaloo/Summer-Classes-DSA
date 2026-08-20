package search;

public class BinarySearch {
    public static Student searchByID(Student[] students, int targetID) {
        int low = 0;
        int high = students.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midID = students[mid].getStudentID();

            if (midID == targetID)
                return students[mid];
            else if (midID < targetID)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return null;
    }

}