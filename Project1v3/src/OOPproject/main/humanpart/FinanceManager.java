package OOPproject.main.humanpart;

import OOPproject.main.datapart.*;
import OOPproject.main.logs.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class FinanceManager extends Manager {
    private static final long serialVersionUID = -8701053504987113559L;
    private static final Map<Integer, Boolean> paymentStatus = new ConcurrentHashMap<>();

    /**
     * Tracks the payment status of a student.
     * 
     * @param studentId The ID of the student.
     * @param isPaid    Payment status (true if paid, false if not paid).
     */
    public static void trackPayment(int studentId, boolean isPaid) {
        paymentStatus.put(studentId, isPaid);
    }

    /**
     * Checks for unpaid students and blocks their login if payment is not made.
     */
    public static void checkAndBlockUnpaidStudents() {
        paymentStatus.forEach((studentId, isPaid) -> {
            if (!isPaid) {
                Student student = DataRepository.getStudentById(studentId);
                if (student != null) {
                    if (!student.isLoginBlocked()) {
                        student.setLoginBlocked(true);
                        System.out.println("Student " + student.getName() + " has been blocked due to non-payment.");

                        // Log the blocking action
                        LogRecord log = new LogRecord(studentId, "Student blocked due to non-payment", java.time.LocalDateTime.now().toString());
                        LogsSettings.addLogRecord(log);
                    }
                } else {
                    System.out.println("Warning: Student with ID " + studentId + " not found.");
                }
            }
        });
    }

    /**
     * Checks if a student is currently blocked from logging in.
     * 
     * @param studentId The ID of the student.
     * @return true if the student is blocked, false otherwise.
     */
    public static boolean isStudentBlocked(int studentId) {
        Student student = DataRepository.getStudentById(studentId);
        if (student == null) {
            System.out.println("Error: Student with ID " + studentId + " not found.");
            return false;
        }
        return student.isLoginBlocked();
    }

    /**
     * Unblocks a student manually by the Finance Manager.
     * 
     * @param studentId The ID of the student to unblock.
     */
    public static void unblockStudent(int studentId) {
        Student student = DataRepository.getStudentById(studentId);
        if (student != null) {
            if (student.isLoginBlocked()) {
                student.setLoginBlocked(false);
                System.out.println("Student " + student.getName() + " has been unblocked.");

                // Log the unblocking action
                LogRecord log = new LogRecord(studentId, "Student unblocked by Finance Manager", java.time.LocalDateTime.now().toString());
                LogsSettings.addLogRecord(log);
            } else {
                System.out.println("Student " + student.getName() + " is not currently blocked.");
            }
        } else {
            System.out.println("Error: Student with ID " + studentId + " not found.");
        }
    }
}
