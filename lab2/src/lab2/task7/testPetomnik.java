package lab2.task7;

public class testPetomnik {

	public static void main(String[] args) {
		
		Person john = new Employee("John", 30, "Engineer");
		Person alice = new PhDStudent("Alice", 26, "Comp. Science");
		Person lize = new PhDStudent("Lize", 29, "Comp. Science");
		Animal murka = new Cat("Murka", 5);
		Animal Rex=new Dog("Rex",7);
		john.assignPet(murka); 
		alice.assignPet(Rex);
		
		
		System.out.println(murka.getSound());
		System.out.println(Rex.getSound());
		PersonRegistry registry = new PersonRegistry();
		registry.addPerson(john);
		registry.addPerson(alice);
		
		
		System.out.println(john.getPet());
		System.out.println(alice.getPet());
		john.leavePetWith(lize);
		// Registry reflects that Alice is taking care of Rex
		System.out.println(john.getPet());
		System.out.println(alice.getPet());
		System.out.println(lize.getPet());
		// John returns from vacation and retrieves Rex
		john.retrievePetFrom(lize);
		System.out.println(john.getPet());
		System.out.println(alice.getPet());
		System.out.println(lize.getPet());



	}

}
