package lab3.task6;

public class PhDStudent extends Person{
	private String Science;
	public PhDStudent() {
		
	}
	
	public PhDStudent(String name, int age,int salary, String Science) {
		super(name, age,salary);
		this.Science = Science;
	}
	
	public String toString() {
		return super.toString() +  " " + Science;
	}
	public boolean canPay() {
        return this.salary > 1500;  
    }
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void swim() {
		// TODO Auto-generated method stub
		
	}
	
	
}
