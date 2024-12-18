package practise6;

public class Animals {
	String species;
	String animalName;
	
	public Animals(String species, String animalName) {
		this.species=species;
		this.animalName=animalName;
	}
	public String toString() {
		return "This animal" + species;
	}
	public void makeSound() {
		System.out.println( "Animal sound");
		
	}
}
