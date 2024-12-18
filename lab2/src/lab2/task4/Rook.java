package lab2.task4;

public class Rook extends Piece {
	public Rook() {
		
	}
    @Override
    public boolean isLegalMove(int[] positionB) {
        if (((positionB[0] == positionA[0]  && (positionB[1] != positionA[1])) || (positionB[1] == positionA[1] && (positionB[0] != positionA[0]))) && positionB[0] >= 1 && positionB[0] <= 8 && positionB[1] >= 1 && positionB[1] <= 8) {
            return true;
        }
        else {
        	return false;
        }
    }
	@Override
	public char getSymbol() {
		// TODO Auto-generated method stub
		return 'R';
	}
	
}
