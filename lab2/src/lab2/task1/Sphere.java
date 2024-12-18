package lab2.task1;

public class Sphere extends Shape3D{
	private double radius;
	
	public Sphere(double radius) {
		this.radius = radius;
	}
	
	public double volume() {
		return ((4/3)*Math.PI * radius * radius * radius);
	}
	
	public double surfaceArea() {
		return (4 * Math.PI * radius * radius);
	}
	
	public String toString() {
		return super.toString() + " radius: " + radius + " of Sphere";
	}
	
}
 