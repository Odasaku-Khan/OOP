package lab3.task2;

public class Amphibious_Vehicle implements Swimable {

	@Override
	public void move() {
		System.out.println("This vehicle can move on the ground");
	}

	@Override
	public void swim() {
		System.out.println("Also it can swim. Amazing");
		
	}

}
