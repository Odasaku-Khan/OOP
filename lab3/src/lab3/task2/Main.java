package lab3.task2;

public class Main {
	public static void main(String[] args) {
		Movable car= new Car();
		car.move();
		Swimable vehicle=new Amphibious_Vehicle();
		vehicle.move();
		vehicle.swim();
	}
}
