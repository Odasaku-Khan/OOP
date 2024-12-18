package lab2.task4;

import java.util.Vector;

public class Board {
	private Vector<Piece> pieces;
	public int[] startPosition;
	
	public Board() {
		
		pieces = new Vector<>();
      
        startPosition = new int[2];  
        startPosition[0] = 1;
        startPosition[1] = 1;
	}
	
	
	public void newGame() {
        Rook rook1 = new Rook();
        rook1.setPositionA(startPosition);  
        pieces.add(rook1);
        
        startPosition[1]++;
        Knight whiteLeft = new Knight();
        whiteLeft.setPositionA(startPosition);
        pieces.add(whiteLeft);
        
        startPosition[1]++;
        Bishop whiteLeftB = new Bishop();
        whiteLeftB.setPositionA(startPosition);
        pieces.add(whiteLeftB);
        
        startPosition[1]++;
        Queen whiteQ = new Queen();
        whiteQ.setPositionA(startPosition);
        pieces.add(whiteQ);
        
        startPosition[1]++;
        King whiteK = new King();
        whiteK.setPositionA(startPosition);
        pieces.add(whiteK);
        
        
        startPosition[1]++;
        Bishop whiteRightB = new Bishop();
        whiteRightB.setPositionA(startPosition);
        pieces.add(whiteRightB);
        
        startPosition[1]++;
        Knight whiteRight = new Knight();
        whiteRight.setPositionA(startPosition);
        pieces.add(whiteRight);
        
        startPosition[1]++;
        Rook whiteRookR = new Rook();
        whiteRookR.setPositionA(startPosition);  
        pieces.add(whiteRookR);
        
        startPosition[0]++;
        startPosition[1] = 1;

        for (int i = 0; i < 8; i++) {
            Pawn pawn = new Pawn();
            pawn.setPositionA(startPosition);
            pieces.add(pawn);
            startPosition[1]++;
        }
        
        
        startPosition[0] = 7;
        startPosition[1] = 1;

        for (int i = 0; i < 8; i++) {
            Pawn blackPawn = new Pawn();
            blackPawn.setPositionA(startPosition);
            pieces.add(blackPawn);
            startPosition[1]++;
        }

        startPosition[0] = 8;
        startPosition[1] = 1;

        Rook blackRookL = new Rook();
        blackRookL.setPositionA(startPosition);
        pieces.add(blackRookL);

        startPosition[1]++;
        Knight blackLeft = new Knight();
        blackLeft.setPositionA(startPosition);
        pieces.add(blackLeft);

        startPosition[1]++;
        Bishop blackLeftB = new Bishop();
        blackLeftB.setPositionA(startPosition);
        pieces.add(blackLeftB);

        startPosition[1]++;
        Queen blackQ = new Queen();
        blackQ.setPositionA(startPosition);
        pieces.add(blackQ);

        startPosition[1]++;
        King blackK = new King();
        blackK.setPositionA(startPosition);
        pieces.add(blackK);

        startPosition[1]++;
        Bishop blackRightB = new Bishop();
        blackRightB.setPositionA(startPosition);
        pieces.add(blackRightB);

        startPosition[1]++;
        Knight blackRight = new Knight();
        blackRight.setPositionA(startPosition);
        pieces.add(blackRight);

        startPosition[1]++;
        Rook blackRookR = new Rook();
        blackRookR.setPositionA(startPosition);
        pieces.add(blackRookR);
        
        
        
        
	}
	
	
	public void printBoard() {
        String[][] board = new String[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = ".";
            }
        }

        for (Piece piece : pieces) {
            int[] pos = piece.getPositionA();
            char symbol = piece.getSymbol(); 
            board[pos[0] - 1][pos[1] - 1] = Character.toString(symbol);
        }

        for (int i = 8 - 1; i >= 0; i--) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("  A B C D E F G H");
    }
	
	 public boolean movePiece(int[] from, int[] to) {
	      
	        Piece pieceToMove = null;
	        for (Piece piece : pieces) {
	            if (piece.getPositionA()[0] == from[0] && piece.getPositionA()[1] == from[1]) {
	                pieceToMove = piece;
	                break;
	            }
	        }

	        if (pieceToMove != null && pieceToMove.isLegalMove(to)) {
	            Piece pieceOnTarget = null;
	            for (Piece piece : pieces) {
	                if (piece.getPositionA()[0] == to[0] && piece.getPositionA()[1] == to[1]) {
	                    pieceOnTarget = piece;
	                    break;
	                }
	            }

	            if (pieceOnTarget != null) {
	                pieces.remove(pieceOnTarget);
	            }

	            pieceToMove.setPositionA(to);
	            return true;
	        }
	        
	        return false; 
	    }
	}
	
	

