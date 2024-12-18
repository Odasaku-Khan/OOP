package lab3.task4;

public class Person {
	private String name;


	public Person(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public String toString() {
		return "Person name "+ name;
	}
	public boolean equals(Object obj) {
		if (this==obj) {
			return true;
		}
		if(obj==null || getClass()!=obj.getClass()) {
			return false;
		}
		Person person =(Person) obj;
		return name.equals(person.name);
	}
	public int hashCode() {
		return name.hashCode();
	}
}
