package practise6;
class Restaurant {
	boolean servePizza(CanHavePizza eater) {
		eater.eatPizza();
		if (eater instanceof Person) {
			System.out.println("Payment proceed " + ((Person)eater).name);
			return true;
		}
		return false;
	}
}
