package lab2.task6;

public class Parallel extends Circuit {
	private Circuit c1;
	private Circuit c2;
	public Parallel() {
		
	}
	
	public Parallel(Circuit c1, Circuit c2) {
		this.c1 = c1;
		this.c2 = c2;
	}
	
	
	@Override
	public double getResistance() {
		// TODO Auto-generated method stub
		return 1 / (1/c1.getResistance() + 1/c2.getResistance());
	}

	@Override
	public double getPotential() {
		// TODO Auto-generated method stub
		return c1.getPotential();
	}

	@Override
	public void applyPotentialDiff(double V) {
		// TODO Auto-generated method stub
		c1.applyPotentialDiff(V);
		c2.applyPotentialDiff(V);
	}

}
