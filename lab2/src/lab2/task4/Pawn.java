package lab2.task4;

public class Pawn extends Piece {

    public Pawn() {}

    @Override
    public boolean isLegalMove(int[] positionB) {
        if ((Math.abs(positionB[0] - positionA[0]) == 1) && (positionB[1] == positionA[1]) && positionB[0] >= 1 && positionB[0] <= 8 && positionB[1] >= 1 && positionB[1] <= 8) {
            return true;
        } else {
            return false;
        }
    }
    
    
    public char getSymbol() {
        return 'P'; 
    }
}