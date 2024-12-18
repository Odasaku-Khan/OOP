package lab2.task6;

public class Series extends Circuit {
	private Circuit c1;
	private Circuit c2;
	
	public Series() {
		
	}
	
	public Series(Circuit c1, Circuit c2) {
		this.c1 = c1;
		this.c2 = c2;
	}
	
	
	@Override
	public double getResistance() {
		// TODO Auto-generated method stub
		return c1.getResistance() + c2.getResistance();
	}

	@Override
	public double getPotential() {
		// TODO Auto-generated method stub
		return c1.getPotential() + c2.getPotential();
	}

	@Override
	public void applyPotentialDiff(double V) {
		// TODO Auto-generated method stub
		double sumRes = getResistance();
		double Icur = V / sumRes;
		
		c1.applyPotentialDiff(Icur * c1.getResistance());
		c2.applyPotentialDiff(Icur * c2.getResistance());
		
	}
	
}
