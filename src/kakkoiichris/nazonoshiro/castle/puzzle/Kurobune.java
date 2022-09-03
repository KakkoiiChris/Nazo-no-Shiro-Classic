package kakkoiichris.nazonoshiro.castle.puzzle;//Christian Alexander, 5/12/11, Pd. 6

import java.util.Scanner;

public class Kurobune extends Puzzle {
    
    public Kurobune() {
        super("Kurobune");
    }
    
    public void play() {
        var input = new Scanner(System.in);
        var again = "y";
        var l = 0;
        var t = 0;
        while (again.equals("y") || again.equals("Y")) {
            t = t + 1;
            System.out.println("  <[KUROBUNE]>  ");
            System.out.println();
            var torpedoes = 20;
            var hits = 0;
            int row, column;
            var board = new char[8][8];
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    board[r][c] = '~';
                }
            }
            setUp(board);
            while (torpedoes > 0 && hits < 4) {
                show(board);
                System.out.println();
                System.out.print("Row: ");
                row = input.nextInt();
                System.out.println();
                System.out.print("Column: ");
                column = input.nextInt();
                
                while (row > 7 || row < 0 || column > 7 || column < 0) {
                    System.out.println("That's out of your range. Enter again.");
                    System.out.print("Row: ");
                    row = input.nextInt();
                    System.out.println();
                    System.out.print("Column: ");
                    column = input.nextInt();
                }
                System.out.println();
                if (board[row][column] == 'S') {
                    board[row][column] = '!';
                    hits = hits + 1;
                    torpedoes = torpedoes - 1;
                    System.out.println("That's a hit!");
                    System.out.println();
                }
                else if (board[row][column] == '~') {
                    board[row][column] = 'm';
                    torpedoes = torpedoes - 1;
                    System.out.println("That's a miss.");
                    System.out.println();
                }
                else {
                    System.out.println("You already hit that spot. Fire again.");
                    System.out.println();
                }
                if (hits < 4 && torpedoes > 0) {
                    if (torpedoes == 1)
                        System.out.println("You have 1 torpedo left.");
                    else
                        System.out.println("You have " + torpedoes + " torpedoes left.");
                    System.out.println();
                }
            }
            
            if (hits != 4) {
                System.out.println("You lose.");
                l++;
            }
            
            if (l > 0) {
                System.out.println("Try again? y/n");
                
                again = input.next();
                
                l = 0;
            }
            else {
                victory();
                
                again = "n";
            }
            
            System.out.println();
        }
    }
    
    public void show(char[][] board) {
        System.out.println("   0 1 2 3 4 5 6 7\n");
        
        for (var r = 0; r < 8; r++) {
            System.out.printf("%d  ", r);
            
            for (var c = 0; c < 8; c++) {
                if (board[r][c] == 'S') {
                    System.out.print("~ ");
                }
                else {
                    System.out.print(board[r][c] + " ");
                }
            }
            
            System.out.println();
        }
    }
    
    public void setUp(char[][] board) {
        if (Math.random() > 0.5) {
            var r = (int) (Math.random() * 8);
            var c = (int) (Math.random() * 5);
            
            for (var i = 0; i < 4; i++) {
                board[r][c + i] = 'S';
            }
        }
        else {
            var r = (int) (Math.random() * 5);
            var c = (int) (Math.random() * 8);
            
            for (var i = 0; i < 4; i++) {
                board[r + i][c] = 'S';
            }
        }
    }
}