package lab3.task6;

public class Bird extends Animal implements Movable {
	public Bird() {
		
	}
		
	public Bird(String name, int age) {
			super(name, age);
	}
	
	@Override
	public String getSound() {
			// TODO Auto-generated method stub
			return "Chick Chiric";
		}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		System.out.println("Birds can fly can swim, mandatory ther are moving");
	}
	}


