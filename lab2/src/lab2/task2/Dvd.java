package lab2.task2;

public class Dvd extends LibrarySystem{
	private double cost;
	private float size;
	
	public Dvd(String title, String author, int publicateYear ,double cost, float size) {
		super(title, author, publicateYear);
		this.cost = cost;
		this.size = size;
	}
	
	public Dvd(String title, String author, int publicateYear, double cost) {
        super(title, author, publicateYear);
        this.cost = cost;
        this.size = 0.0f; 
    }
	

    public Dvd(String title, String author, int publicateYear, float size) {
        super(title, author, publicateYear);
        this.size = size;
        this.cost = 0.0;
    }
    
	public double getPrice() {
		return cost;
	}
	
	public float getSize() {
		return size;
	}
	
	public void setSize(float size) {
		this.size = size;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	

	public String toString() {
		return super.toString() + " cost: " + cost + " size: " + size;
	}
	
}
