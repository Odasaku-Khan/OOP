package lab3.task5;

public class Chocolate implements Comparable<Chocolate> {
	private double weight;
	private String name;
	public Chocolate(String name,double weight) {
		this.name=name;
		this.weight=weight;
	}
	public double getWeight() {
		return weight;
	}
	public String getName() {
		return name;
	}	
	public String toString() {
		return "Chocolate name= "+name + "weight" +weight;
	}
	public int compareTo(Chocolate other) {
        return Double.compare(this.weight, other.weight);
    }

	
}
