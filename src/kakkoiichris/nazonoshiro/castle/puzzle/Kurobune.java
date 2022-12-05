//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.puzzle;

import kakkoiichris.nazonoshiro.Util;

import java.util.Scanner;

public class Kurobune extends Puzzle {
    public Kurobune() {
        super("Kurobune");
    }
    
    @Override
    public boolean play() {
        System.out.println("  <[KUROBUNE]>\n");
        
        var torpedoes = 20;
        
        var hits = 0;
        
        var board = new char[8][8];
        
        for (var r = 0; r < 8; r++) {
            for (var c = 0; c < 8; c++) {
                board[r][c] = '~';
            }
        }
        
        setUp(board);
        
        while (torpedoes > 0 && hits < 4) {
            show(board);
            
            System.out.print("\nRow: ");
            
            var row = Util.input.nextInt();
            
            System.out.print("\nColumn: ");
            
            var column = Util.input.nextInt();
            
            while (row > 7 || row < 0 || column > 7 || column < 0) {
                System.out.println("That's out of your range. Enter again.\nRow: ");
                
                row = Util.input.nextInt();
                
                System.out.print("\nColumn: ");
                
                column = Util.input.nextInt();
            }
            
            System.out.println();
            
            switch (board[row][column]) {
                case 'S' -> {
                    board[row][column] = '!';
                    hits++;
                    torpedoes--;
                    System.out.println("That's a hit!\n");
                }
                
                case '~' -> {
                    board[row][column] = 'm';
                    torpedoes--;
                    System.out.println("That's a miss.\n");
                }
                
                default -> System.out.println("You already hit that spot. Fire again.\n");
            }
            
            if (hits < 4 && torpedoes > 0) {
                if (torpedoes == 1) {
                    System.out.println("You have 1 torpedo left.");
                }
                else {
                    System.out.printf("You have %d torpedoes left.%n", torpedoes);
                }
                
                System.out.println();
            }
        }
        
        if (hits != 4) {
            return false;
        }
        
        victory();
        
        return true;
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