package lab2.task4;

public abstract class Piece {
	protected int[] positionA;
	protected final static int size = 2;
	
	public Piece() {
		positionA = new int[size];
	}
	
	public abstract boolean isLegalMove(int[] positionB);
	
	




	public void setPositionA(int[] sPosition) {
		positionA[0] = sPosition[0];
		positionA[1] = sPosition[1];
	}
	
	public abstract char getSymbol();
	
	

	public int[] getPositionA() {
		// TODO Auto-generated method stub
		return positionA;
	}
}
