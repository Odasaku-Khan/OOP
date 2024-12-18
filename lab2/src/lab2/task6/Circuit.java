package lab2.task6;

public abstract class Circuit {
	public Circuit() {
		
	}
	
	public abstract double getResistance();
	public abstract double getPotential();
	public abstract void applyPotentialDiff(double V);
	
	
	public double getPower() {
		return Math.pow(getPotential(), 2) / getResistance();
	}
	
	public double getCurrent() {
		return getPotential() / getResistance();
		
	}
}
