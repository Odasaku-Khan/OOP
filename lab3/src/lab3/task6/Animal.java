package lab3.task6;

public abstract class Animal {
	protected String name;
	protected int age;
	
	public Animal() {
		
	}
	
	public Animal(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public abstract String getSound();
}
