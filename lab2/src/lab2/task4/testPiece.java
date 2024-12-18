package lab2.task4;

import java.util.Scanner;

public class testPiece {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {  
            int x;
            int y;

            while (true) {
                System.out.println("Выбери фигуру");
                System.out.println("1: Пешка");
                System.out.println("2: Конь");
                System.out.println("3: Слон");
                System.out.println("4: Ладья");
                System.out.println("5: Ферзь");
                System.out.println("6: Король");
                System.out.println("0: Выйти!");

                int choice = sc.nextInt();

                if (choice == 1) {
                    x = sc.nextInt();
                    y = sc.next().charAt(0) - 64;

                    int[] startPosition = {x, y};

                    Pawn pawn = new Pawn();
                    pawn.setPositionA(startPosition);
                    x = sc.nextInt();
                    y = sc.next().charAt(0) - 64;

                    int[] newPosition = {x, y};

                    System.out.println(pawn.isLegalMove(newPosition));
                } else if (choice == 2) {
                    x = sc.nextInt();
                    y = sc.next().charAt(0) - 64;

                    int[] startPosition = {x, y};

                    Knight knight = new Knight();
                    knight.setPositionA(startPosition);
                    x = sc.nextInt();
                    y = sc.next().charAt(0) - 64;

                    int[] newPosition = {x, y};

                    System.out.println(knight.isLegalMove(newPosition) + "\n");
                } else if (choice == 3) {
                    x = sc.nextInt();
                    y = sc.next().charAt(0) - 64;

                    int[] startPosition = {x, y};

                    Bishop bishop = new Bishop();
                    bishop.setPositionA(startPosition);
                    x = sc.nextInt();
                    y = sc.next().charAt(0) - 64;

                    int[] newPosition = {x, y};

                    System.out.println(bishop.isLegalMove(newPosition) + "\n");
                } else if (choice == 4) {
                    x = sc.nextInt();
                    y = sc.next().charAt(0) - 64;

                    int[] startPosition = {x, y};

                    Rook rook = new Rook();
                    rook.setPositionA(startPosition);
                    x = sc.nextInt();
                    y = sc.next().charAt(0) - 64;

                    int[] newPosition = {x, y};

                    System.out.println(rook.isLegalMove(newPosition) + "\n");
                } else if (choice == 6) {
                    x = sc.nextInt();
                    y = sc.next().charAt(0) - 64;

                    int[] startPosition = {x, y};

                    King king = new King();
                    king.setPositionA(startPosition);
                    x = sc.nextInt();
                    y = sc.next().charAt(0) - 64;

                    int[] newPosition = {x, y};

                    System.out.println(king.isLegalMove(newPosition) + "\n");
                } else if (choice == 5) {
                    x = sc.nextInt();
                    y = sc.next().charAt(0) - 64;

                    int[] startPosition = {x, y};

                    Queen queen = new Queen();
                    queen.setPositionA(startPosition);
                    x = sc.nextInt();
                    y = sc.next().charAt(0) - 64;

                    int[] newPosition = {x, y};

                    System.out.println(queen.isLegalMove(newPosition) + "\n");
                } else if (choice == 0) {
                    break;
                }
            }
        } 
    }
}
