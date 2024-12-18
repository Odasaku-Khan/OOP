package lab2.task1;

public abstract class Shape3D {
	public Shape3D() {
		
	}
	public abstract double volume();
	public abstract double surfaceArea();
	public String toString() {
		return "SA: " + surfaceArea() + " V: " + volume();
	}
	
	public void getHello() {
		System.out.println("Hello " + getClass());
	}
	
	
	
}


