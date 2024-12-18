package lab3.task6;

public class Cat extends Animal implements Comparable<Cat>,Movable,Swimable{
	public Cat() {
		
	}
	
	public Cat(String name, int age) {
		super(name, age);
		this.age=age;
	}

	@Override
	public String getSound() {
		// TODO Auto-generated method stub
		return "Meeeeoooooooooooooooooow!!!!";
	}

	@Override
	public int compareTo(Cat other) {
		return Integer.compare(this.age,other.age);
	}

	@Override
	public void swim() {
		System.out.println("They can swim but they are hate it");
		
	}

	@Override
	public void move() {
		System.out.println("Probably they hate to move to");
	}
	
	
}
