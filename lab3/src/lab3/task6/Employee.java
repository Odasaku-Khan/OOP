package lab3.task6;

public class Employee extends Person {
	private String workPlace;
	
	public Employee() {
		
	}
	
	public Employee(String name, int age,int salary, String workPlace) {
		super(name, age,salary);
		this.workPlace = workPlace;
	}
	 public boolean canPay() {
	        return this.salary > 3000;  
	    }
	public String toString() {
		return super.toString() + " " + workPlace;
	}

	@Override
	public void move() {
		
		
	}

	@Override
	public void swim() {
		
		
	}
}
