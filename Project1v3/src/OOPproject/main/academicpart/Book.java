package OOPproject.main.academicpart;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 361819966361406656L;

    private int bookID;
    private String bookName;
    private String author;
    private boolean available;
    private int quantity;

    /**
     * Constructor to initialize the Book object.
     *
     * @param bookID   The unique ID of the book.
     * @param bookName The name of the book.
     * @param author   The author of the book.
     * @param quantity The quantity of the book available.
     */
    public Book(int bookID, String bookName, String author, int quantity) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.author = author;
        this.available = quantity > 0; // Availability depends on the quantity
        this.quantity = quantity;
    }

    /**
     * Retrieves the availability status of the book.
     *
     * @return true if the book is available, otherwise false.
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets the availability status of the book.
     *
     * @param available The new availability status.
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Retrieves the unique ID of the book.
     *
     * @return The book ID.
     */
    public int getBookID() {
        return bookID;
    }

    /**
     * Retrieves the name of the book.
     *
     * @return The book name.
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * Retrieves the author of the book.
     *
     * @return The author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Retrieves the quantity of the book available.
     *
     * @return The quantity of the book.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Updates the quantity of the book.
     *
     * @param quantity The new quantity of the book.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.available = quantity > 0; // Update availability based on quantity
    }

    /**
     * Provides a string representation of the Book object.
     *
     * @return A string representing the Book.
     */
    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", available=" + available +
                ", quantity=" + quantity +
                '}';
    }
}
