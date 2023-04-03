//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.puzzle;

import kakkoiichris.nazonoshiro.Console;

import java.util.Arrays;

public class Kurobune extends Puzzle {
    public Kurobune() {
        super("Kurobune");
    }
    
    @Override
    public boolean play() {
        Console.writeLine("  <[KUROBUNE]>\n");
        
        var torpedoes = 20;
        
        var hits = 0;
        
        var board = new char[8][8];
        
        for (var r = 0; r < 8; r++) {
            Arrays.fill(board[r], '~');
        }
        
        setUp(board);
        
        while (torpedoes > 0 && hits < 4) {
            show(board);
            
            Console.setPrompt("Row > ");
            
            var row = Console.readInt();
            
            while (row < 0 || row > board.length) {
                Console.writeLine("That's out of your range. Enter again.");
                
                row = Console.readInt();
            }
            
            Console.setPrompt("Column > ");
            
            var column = Console.readInt();
            
            while (column < 0 || column > board[0].length) {
                Console.writeLine("That's out of your range. Enter again.");
                
                column = Console.readInt();
            }
            
            Console.newLine();
            
            switch (board[row][column]) {
                case 'S' -> {
                    board[row][column] = '!';
                    hits++;
                    torpedoes--;
                    Console.writeLine("That's a hit!\n");
                }
                
                case '~' -> {
                    board[row][column] = 'm';
                    torpedoes--;
                    Console.writeLine("That's a miss.\n");
                }
                
                default -> Console.writeLine("You already hit that spot. Fire again.\n");
            }
            
            if (hits < 4 && torpedoes > 0) {
                var plural = (torpedoes == 1) ? "" : "es";
                
                Console.writeLine("You have %d torpedo%s left.%n", torpedoes, plural);
            }
        }
        
        if (hits != 4) {
            return false;
        }
        
        victory();
        
        Console.setPrompt("> ");
        
        return true;
    }
    
    public void show(char[][] board) {
        Console.writeLine("   0 1 2 3 4 5 6 7\n");
        
        for (var r = 0; r < 8; r++) {
            Console.write("%d  ", r);
            
            for (var c = 0; c < 8; c++) {
                if (board[r][c] == 'S') {
                    Console.write("~ ");
                }
                else {
                    Console.write("%s ", board[r][c]);
                }
            }
            
            Console.newLine();
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