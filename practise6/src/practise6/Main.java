package practise6;

public class Main {

	public static void main(String[] args) {
		Student student = new Student(20,"Margarita","Azim");
		student.checkPizza("Margarita");
		student.checkPizza("Peperoni");
        student.eatPizza();
        student.takeretake();
        student.move();
        student.dance();
        Restaurant restaurant=new Restaurant();
        restaurant.servePizza(student);

        Cat cat = new Cat("Kuro","Maine Coon","Chicken+Mushroom");
        cat.eatPizza();
        cat.makeSound();
        cat.checkPizza("Chicken+Mushroom");
        cat.checkPizza("4 Seasons of cheese");
        cat.move();

	}

}
