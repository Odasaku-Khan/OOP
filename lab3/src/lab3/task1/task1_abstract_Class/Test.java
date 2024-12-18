package lab3.task1.task1_abstract_Class;

public class Test {

	public static void main(String[] args) {
		 Animal dog = new Dog("Buddy");
	        Animal cat = new Cat("Whiskers");

	        dog.makeSound();
	        cat.makeSound();
	        dog.sleep();
	        cat.sleep();

	}

}
