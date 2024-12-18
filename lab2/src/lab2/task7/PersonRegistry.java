package lab2.task7;

import java.util.Vector;

public class PersonRegistry {
	private Vector<Person> clients;
	
	public PersonRegistry() {
		clients = new Vector<>();
	}
	
	public void addPerson(Person p) {
		clients.add(p);
	}
	public void removePerson(Person p) {
		clients.remove(p);
	}
	

	
	public String toString() {
		return "the pet was succesfully taked!";
	}
	
	
}
