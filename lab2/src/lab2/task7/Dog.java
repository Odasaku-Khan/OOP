package lab2.task7;

public class Dog extends Animal{
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
}
