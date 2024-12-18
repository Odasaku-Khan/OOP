package lab2.task2;

public abstract class LibrarySystem {
	public LibrarySystem() {
		
	}

	private String title;
	private String author;
	private int publicateYear;
	
	
	public LibrarySystem(String title, String author, int publicateYear) {
		this.title = title;
		this.author = author;
		this.publicateYear = publicateYear;
	}
	
	
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public int getPublicateYear() {
		return publicateYear;
	}
	
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setPublicateYear(int publicateYear) {
		this.publicateYear = publicateYear;
	}	
	
	
	
	
	public String toString() {
		return "Title is: " + title + " author name is: " + author + " publicate Year is: " + publicateYear;
	}
	
	public int diff() {
		return 2024 - publicateYear;
	}
}
