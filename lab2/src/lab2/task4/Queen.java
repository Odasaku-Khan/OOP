package lab2.task4;

public class Queen extends Piece{
	public Queen() {
		
	}
	
    public boolean isLegalMove(int[] positionB) {
        if ((Math.abs(positionB[0] - positionA[0]) == Math.abs(positionA[1] - positionB[1]) && positionB[0] >= 1 && positionB[0] <= 8 && positionB[1] >= 1 && positionB[1] <= 8)) {
            return true;
        }
        if ((positionB[0] == positionA[0] || positionB[1] == positionA[1]) && positionB[0] <= 8 && positionB[1] <= 8) {
            return true;
        }

        
        else {
            return false;
        }

    }

	@Override
	public char getSymbol() {
		// TODO Auto-generated method stub
		return 'Q';
	}
}
