package lab2.task4;

public class Knight extends Piece {
	public Knight() {
	}
    public boolean isLegalMove(int[] positionB) {
        if ((((Math.abs(positionB[0] - positionA[0])) + (Math.abs(positionB[1] - positionA[1]))) == 3) && positionB[0] >= 1 && positionB[0] <= 8 && positionB[1] >= 1 && positionB[1] <= 8) {
            return true;
        } else {
            return false;
        }
    }
	@Override
	public char getSymbol() {
		// TODO Auto-generated method stub
		return 'k';
	}
}

