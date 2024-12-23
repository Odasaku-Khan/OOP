package OOPproject.main.humanpart;

import OOPproject.main.datapart.DataRepository;
import OOPproject.main.academicpart.Book;
import OOPproject.main.humanpart.Student;
import java.util.Vector;

public class Librarian extends Employee {
    
    private static final long serialVersionUID = -6033520375374949588L;

    // Constructor for Librarian class, extending Employee
    public Librarian(String name, String email, String password, Boolean isReseacher, int salary) {
        super(name, email, password, isReseacher, salary);
    }

    // Method to borrow a book for a student
    public String borrowBook(int studentId, int bookId) {
        // Retrieve the student and book objects from the DataRepository
        Student student = DataRepository.getStudentById(studentId);
        Book book = DataRepository.getBookById(bookId);
        
        // Check if the student exists
        if (student == null) {
            return "Student not found!";
        }
        
        // Check if the book exists
        if (book == null) {
            return "Book not found!";
        }

        // Check if the book is already borrowed
        if (!book.isAvailable() || book.getQuantity() <= 0) {
            return "This book is currently unavailable!";
        }

        // Add the book to the student's borrowed books and decrease quantity
        student.borrowBook(book);
        book.setQuantity(book.getQuantity() - 1);
        if (book.getQuantity() == 0) {
            book.setAvailable(false);
        }

        // Create a log record for the action
        createLogRecord("Book '" + book.getBookName() + "' borrowed by student ID " + studentId);

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

        // Return the book and increase quantity
        student.returnBook(book);
        book.setQuantity(book.getQuantity() + 1);
        book.setAvailable(true);

        createLogRecord("Book '" + book.getBookName() + "' returned by student ID " + studentId);

        return "Book returned successfully!";
    }

    // Method to add a new book to the repository
    public String addNewBook(String name, String author, int quantity) {
        Book newBook = new Book(DataRepository.getNextId(), name, author, quantity);
        newBook.setAvailable(quantity > 0); // Ensure the book is marked as available based on quantity
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
