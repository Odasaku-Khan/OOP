package lab2.task3;
public class studentLaptop extends Laptop {
	private int diag;
	private String os;
	
	public studentLaptop(String brand, int year, String cpu) {
		super(brand, year, cpu);
	}
	
	
	public studentLaptop(String brand, int year, String cpu, int diag) {
		super(brand, year, cpu);
		this.diag = diag;
		
	}
	public studentLaptop(String brand, int year, String cpu, String os) {
		super(brand, year, cpu);
		this.os = os;
		
	}	
	
	public studentLaptop(String brand, int year, String cpu, String os, int diag) {
		super(brand, year, cpu);
		this.os = os;
		this.diag = diag;
		
	}	
	
	public String toString() {
		return super.toString() + " diagonal is: " + diag + " system type is: " + os;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!super.equals(o)) {
			return false;
		}
		studentLaptop sL = (studentLaptop) o;
		return diag == sL.diag && os.equals(sL.os);
		
	}
	
	@Override
	public int hashCode() {
		int result = super.hashCode();
		
		result = 7 * result + diag;
		result = 7 * result + os.hashCode();
		return result;

	}
}
