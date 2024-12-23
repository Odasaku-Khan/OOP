package OOPproject.main.humanpart;

import java.util.Vector;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import OOPproject.main.logs.*;
import OOPproject.main.datapart.*;
import OOPproject.main.academicpart.News;
import OOPproject.main.academicpart.Book;
import OOPproject.main.academicpart.Message;

public abstract class User implements Serializable {
	private static final long serialVersionUID = 3956715672433486862L;
	/**
     * Unique identifier of the user.
     */
	private int userId;
	/**
	 *User's name.
	 */
	private String name;
	/**
	 * User's email address.
	 */
	private String email;
	/**
	 * User's password.
	 */
	private String password;
	/**
	 * Flag indicating whether the user is a researcher (true if the user is a researcher, false otherwise).
	 */
	private Boolean isResearcher;
	/**
	 * Default constructor
	 */
	public User() {
	}
	public User(String name, String email, String password, Boolean isReseacher) {
		this.userId = DataRepository.getNextId();
		this.email = email;
		this.isResearcher = isReseacher;
		this.name = name;
		this.password = password;
	}
	public int getUserId() {
		return userId;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public Boolean getIsResearcher() {
		return isResearcher;
	}
	public User login(String enteredName, String enteredPassword) {
        User user = DataRepository.login(enteredName, enteredPassword);
        if (user != null) {
            createLogRecord("User logged in");
            return user;
        } else {
            System.out.println("Login failed");
        }
        return null;
    }
	public void logout() {
        createLogRecord("User logged out");
        DataRepository.logout();
    }
	
	/**
	 * Update user profile.
	 * 
	 * @param name         New user name 
	 * @param email        New email address
	 * @param password     New user password
	 * @param isResearcher New researcher flag
	 * @return true if the profile is successfully updated, otherwise false
	 */
	public boolean updateProfile(String name, String email, String password, Boolean isResearcher) {
        // Валидация email
        if (!isValidEmail(email)) {
            return false;
        }

        this.name = name;
        this.email = email;
        this.password = password;

        // Log the profile update
        createLogRecord("User profile updated");

        return true;
    }
	/**
	 * Check the validity of the email format.
	 * 
	 * @param email Email to be validated.
	 * @return true if the format is correct, otherwise false.
	 */
    private boolean isValidEmail(String email) {
        // Проверка формата email с использованием регулярного выражения
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
    /**
     * Create a log record for the user's actions.
     *
     * @param text Text of the log record.
     */
    public void createLogRecord(String text){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        LogRecord logRecord = new LogRecord(this.userId, formattedDateTime, text);
        LogsSettings.addLogRecord(logRecord);
    }
    /**
     * View news.
     */
    public void viewNews() {
        Vector<News> news = DataRepository.getNews();
        for (News n : news) {
            System.out.println(n);
        }
    }
    /**
     * Send a message to another user.
     *
     * @param receiver ID of the message recipient.
     * @param content  Message content.
     */
    public void sendMessage(int receiver, String content) {
        Message message = new Message(this.userId, receiver, content);
        DataRepository.addMessage(message);
        createLogRecord("Message sent");
    }
    /**
     * View user messages.
     */
    public void viewMessages() {
        Vector<Message> messages = DataRepository.getMessages();
        for (Message m : messages) {
            if (m.getReceiverUser().getUserId() == this.userId) {
                System.out.println(m);
            }
        }
    }
    /**
     * Overridden method for comparing users.
     *
     * @param obj Object for comparison.
     * @return true if the users are identical, otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return userId == other.userId;
    }
    /**
     * Overridden method to represent the User object as a string.
     *
     * @return String representation of the User object.
     */
    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
            + ", isResearcher=" + isResearcher + "]";
    }
    private Vector<Book> borrowedBooks = new Vector<>(); // Track borrowed books

    // Method to add borrowed book
    public void addBorrowedBook(Book book) {
        borrowedBooks.add(book);
    }

    // Method to remove borrowed book
    public void removeBorrowedBook(Book book) {
        borrowedBooks.remove(book);
    }

    // Method to get borrowed books
    public Vector<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
