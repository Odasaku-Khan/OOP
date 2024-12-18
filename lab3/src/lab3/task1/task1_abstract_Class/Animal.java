package lab3.task1.task1_abstract_Class;

public abstract class Animal {
	String name;
	public Animal(String name) {
		this.name=name;
	}
	abstract void makeSound() ;
	public void sleep() {
		System.out.println("Snoring "+name );
	}
}
