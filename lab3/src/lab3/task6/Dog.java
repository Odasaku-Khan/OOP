package lab3.task6;

public class Dog extends Animal implements Movable,Swimable{
	public Dog() {
		
	}
	
	public Dog(String name, int age) {
		super(name, age);
	}
	@Override
	public String getSound() {
		// TODO Auto-generated method stub
		return "Woof Woof";
	}

	@Override
	public void swim() {
		System.out.println("They like to swim but not the shower");
	}

	@Override
	public void move() {
		System.out.println("They like to move");
		
	}
}
