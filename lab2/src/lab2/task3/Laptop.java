package lab2.task3;


public class Laptop {
	private String brand;
	private int year;
	private String cpu;
	
	public Laptop(String brand, int year, String cpu) {
		this.brand = brand;
		this.year = year;
		this.cpu = cpu;
	}
	
	public String toString() {
		return "Laptop is: " + brand + " year of produce: " + year + " cpu is: " + cpu;
	}
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}
		Laptop laptop = (Laptop) o;
		
		return year == laptop.year && cpu.equals(laptop.cpu) && brand.equals(laptop.brand);
	}
	@Override
	public int hashCode() {
		int result = 13;
		result = 7 * result + brand.hashCode();
		result = 7 * result + year;
		result = 7 * result + cpu.hashCode();
		
		return result;
//		return Objects.hash(brand, year, cpu); 
	
	}
	
	
}
