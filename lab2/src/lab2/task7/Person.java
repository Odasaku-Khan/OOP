package lab2.task7;

public abstract class  Person {
	protected String name;
	protected int age;
	protected Animal a;
	

	
	
	public Person() {
		
	}
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	
	public void assignPet(Animal pet) {
		this.a = pet;
	}
	
	public void removePet() {
		this.a = null;
	}
	
	public Animal getPet() {
		return a;
	}
	
	public boolean hasPet() {
		if(a == null) {
			
			return false;
		}
		else {
			return true;
		}
	}
	
	public void leavePetWith(Person p) {
		if (p instanceof PhDStudent && this.a instanceof Dog) {
			System.out.println("False!");
		}
		else {
			p.assignPet(this.a);
			this.a = null;
		}
		
	}
	
	public void retrievePetFrom(Person p) {
		if(p.a != null) {
			this.assignPet(p.getPet());
			p.a = null;
		}
		else {
			System.out.println(p + " doesnt have a pet");
		}
	}
	
	
	public String toString() {
		return "Hello my name is: " + name + " I'am " + age + " years old " + " , my pet's is: " + a;
	}
	
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}
		
		Person p = (Person) o;
		
		return age == p.age && name.equals(o) && a.equals(o);
	}
	
	public int hashCode() {
		int result = 101;
		
		result = result * 91 + age;
		result = result * 91 + name.hashCode();
		
		return result;
	}
	
	
	
	
}
