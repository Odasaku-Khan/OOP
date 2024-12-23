package OOPproject.main.academicpart;

import OOPproject.main.humanpart.*;
import OOPproject.main.datapart.*;
import java.util.Vector;

public class FinanceOffice {
    private static final int CREDIT_COST = 35000;
    private static final int STUDENT_FEE = 25000;
    private static final int MAX_CREDITS = 21;

    /**
     * Calculate the total course cost for a given student.
     * 
     * @param studentId The ID of the student.
     * @return The total cost for the student or -1 if an error occurs.
     */
    public static int calculateCourseCost(int studentId) {
        Student student = DataRepository.getStudentById(studentId);
        
        if (student == null) {
            System.out.println("Error: Student not found.");
            return -1;
        }
        
        Vector<Course> enrolledCourses = student.getEnrolledCourses();
        int totalCredits = 0;

        for (Course course : enrolledCourses) {
            totalCredits += course.getCredits();
        }

        if (totalCredits > MAX_CREDITS) {
            System.out.println("Error: Cannot enroll in more than " + MAX_CREDITS + " credits per semester.");
            return -1;
        }

        int totalCost = (totalCredits * CREDIT_COST) + STUDENT_FEE;
        System.out.println("Total cost for student " + student.getName() + ": " + totalCost);
        
        return totalCost;
    }
}
