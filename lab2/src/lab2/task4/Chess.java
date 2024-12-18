package lab2.task4;

import java.util.Scanner;

public class Chess {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Board board = new Board();
        board.newGame();
        board.printBoard();

        while (true) {
            System.out.println("Введите ваш ход (например, e2 e4):");
            String move = sc.nextLine().toLowerCase();

            if (move.equals("exit")) {
                break;  
            }

          
            int fromX = move.charAt(1) - '1' + 1;
            int fromY = move.charAt(0) - 'a' + 1;
            int toX = move.charAt(4) - '1' + 1;
            int toY = move.charAt(3) - 'a' + 1;

            int[] from = {fromX, fromY};
            int[] to = {toX, toY};

            
            if (board.movePiece(from, to)) {
                System.out.println("Ход успешен!");
            } else {
                System.out.println("Неверный ход, попробуйте снова.");
            }

            
            board.printBoard();
        }

        sc.close();
    }
}
