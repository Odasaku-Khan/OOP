package practise6;

public class Student extends Person implements CanHaveParty,Movable,CanHavePizza,CanHaveRetake,IsItFavouritePizza {
	private String favouritePizza;

	public Student(int age, String favouritePizza, String name) {
		super(age, name);
		this.favouritePizza=favouritePizza;
	}

	@Override
	public void takeretake() {
		System.out.println("Stutent "+ name + " take retake  " );
	}

	@Override
	public void eatPizza() {
		System.out.println("Stutent "+ name + " eat Pizza  " );
	}

	@Override
	public void move() {
		System.out.println("Stutent "+ name + " moving  " );
	}

	@Override
	public void dance() {
		System.out.println("Stutent "+ name + " is dancing " );
	}
	
	@Override
	public boolean isItFavouritePizza(String pizzaType) {
		return pizzaType.equalsIgnoreCase(favouritePizza);
	}
	
	public void checkPizza(String pizzaType) {
		if (isItFavouritePizza(pizzaType)) {
            System.out.println("The Student "+name+ " loves this " + pizzaType + " pizza!");
        } else {
            System.out.println("The Student "+name+ " eats the pizza "+pizzaType +", but it's not their favorite.");
        }
	}
}
