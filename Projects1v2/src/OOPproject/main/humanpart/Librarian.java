package OOPproject.main.humanpart;

import OOPproject.main.datapart.DataRepository;
import OOPproject.main.academicpart.Book;
import OOPproject.main.humanpart.Student;
import OOPproject.main.humanpart.User;

import java.util.Vector;

public class Librarian extends Employee {
    
    private static final long serialVersionUID = -6033520375374949588L;

	// Constructor for Librarian class, extending Employee
    public Librarian(String name, String email, String password, Boolean isReseacher, int salary) {
        super(name, email, password, isReseacher, salary);
    }

    // Method to borrow a book for a student
    public String borrowBook(int studentId, int bookId) {
        Student student = DataRepository.getStudentById(studentId);
        Book book = DataRepository.getBookById(bookId);
        
        if (student == null) {
            return "Student not found!";
        }
        if (book == null) {
            return "Book not found!";
        }

        // Check if book is already borrowed
        for (Book borrowedBook : student.getBorrowedBooks()) {
            if (borrowedBook.getBookID() == bookId) {
                return "This book is already borrowed by the student!";
            }
        }
        
        student.borrowBook(book);
        return "Book borrowed successfully!";
    }

    // Method to return a borrowed book
    public String returnBook(int studentId, int bookId) {
        Student student = DataRepository.getStudentById(studentId);
        Book book = DataRepository.getBookById(bookId);

        if (student == null) {
            return "Student not found!";
        }
        if (book == null) {
            return "Book not found!";
        }

        if (!student.getBorrowedBooks().contains(book)) {
            return "This book was not borrowed by the student!";
        }

        student.returnBook(book);
        return "Book returned successfully!";
    }

    // Method to add a new book to the repository
    public String addNewBook(String name, String author, String publicationDate) {
        Book newBook = new Book(DataRepository.getNextId(), author, publicationDate);
        DataRepository.addBook(newBook);
        return "New book added successfully!";
    }

    // Method to view the books currently borrowed by a student
    public Vector<Book> viewBorrowedBooks(int studentId) {
        Student student = DataRepository.getStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found!");
            return new Vector<>();
        }
        return student.getBorrowedBooks();
    }

    // Method to remove a book from the library (e.g., if damaged or outdated)
    public String removeBook(int bookId) {
        Book book = DataRepository.getBookById(bookId);
        if (book == null) {
            return "Book not found!";
        }

        DataRepository.removeBook(book);
        return "Book removed successfully!";
    }
}
