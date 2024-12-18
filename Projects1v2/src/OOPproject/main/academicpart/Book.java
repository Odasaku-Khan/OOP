package OOPproject.main.academicpart;
import java.io.Serializable;
public class Book implements Serializable {
	private static final long serialVersionUID = 361819966361406656L;
	 private int bookID;
	    private String bookName;
	    private String author;
	    private boolean available;

	    public Book(int bookID, String bookName,String author) {
	        this.bookID = bookID;
	        this.bookName = bookName;
	        this.author=author;
	        this.available = true; // By default, a book is available
	    }

	    public boolean isAvailable() {
	        return available;
	    }

	    public void setAvailable(boolean available) {
	        this.available = available;
	    }

	    public int getBookID() {
	        return bookID;
	    }

	    public String getBookName() {
	        return bookName;
	    }

		public String getAuthor() {
			return author;
		}
}
