package lab2.task1;

public class Cylinder extends Shape3D {
	private double radius, height;
	public Cylinder(double r, double h) {
		this.height = h;
		this.radius = r;
	}
	public double volume() {
		return Math.PI * height * radius * radius;
	}
	public double surfaceArea() {
		return (2 * Math.PI * radius * radius) + (2 * Math.PI * radius * height);
	}
	
	public String toString() {
		return super.toString()+ " "+ " radius: " + radius + " height: " + height + " of Cylinder";
	}
	
	
}
