package lab3.task6;

public class Student extends Person{
	private int courseYear;
	
	public Student() {
		
	}
	
	public Student(String name, int age,int salary, int courseYear) {
		super(name, age,salary);
		this.courseYear = courseYear;
	}
	
	public String toString() {
		return super.toString() + " " + courseYear;
	}
	public boolean canPay() {
        return this.salary > 1000;
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
