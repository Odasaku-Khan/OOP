package lab2.task6;

public class Resistors extends Circuit {
	public double res;
	
	private double potentialDifference;
	
	
	public Resistors() {
		
	}
	
	public Resistors(double ohm) {
		this.res = ohm;
	}
	
	@Override
	public double getResistance() {
		// TODO Auto-generated method stub
		return res;
	}
	@Override
	public double getPotential() {
		// TODO Auto-generated method stub
		return potentialDifference;
	}
	@Override
    public void applyPotentialDiff(double V) {
        this.potentialDifference = V;
    }
	
}
