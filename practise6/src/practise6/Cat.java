package practise6;

public class Cat extends Animals implements CanHavePizza,Movable,IsItFavouritePizza{
	
	private String favouritePizza;

	public Cat(String animalName,String species, String favouritePizza) {
		super(animalName,species);
		
		this.favouritePizza=favouritePizza;
	}

	@Override
	public void eatPizza() {
		System.out.println("The " + species+ " " + animalName+ " is eating pizza.");
	}

	@Override
	public void move() {
		System.out.println("The " + species+" "+animalName + " is moving.");
	}
	
	public void makeSound() {
		 System.out.println("The "+ species+" "+animalName + " is making sound Mrrr  ");
		
	}

	@Override
	public boolean isItFavouritePizza(String pizzaType) {
		return pizzaType.equalsIgnoreCase(favouritePizza);
	}
	
	public void checkPizza(String pizzaType) {
		if (isItFavouritePizza(pizzaType)) {
            System.out.println("The cat "+ species+" "+animalName + " loves this " + pizzaType + " pizza!");
        } else {
            System.out.println("The cat "+ species+" "+animalName + " eats the pizza, but it's not their favorite.");
        }
	}
}
