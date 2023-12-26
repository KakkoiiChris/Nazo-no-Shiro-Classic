//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.puzzle;

import kakkoiichris.kotoba.Console;
import kakkoiichris.nazonoshiro.Event;

import java.awt.event.KeyEvent;
import java.util.Arrays;

public class Kurobune extends Puzzle {
    public Kurobune() {
        super("Kurobune","");
    }
    
    @Override
    public void init(Console console) {
    
    }
    
    @Override
    public Event doRound(Console console) {
        console.writeLine("  <[KUROBUNE]>\n");
        
        var torpedoes = 20;
        
        var hits = 0;
        
        var board = new char[8][8];
        
        for (var r = 0; r < 8; r++) {
            Arrays.fill(board[r], '~');
        }
        
        setUp(board);
        
        while (torpedoes > 0 && hits < 4) {
            show(console, board);
            
            console.setPrompt("Row > ");
            
            var row = Integer.parseInt(console.readLine().orElseThrow());
            
            while (row < 0 || row > board.length) {
                console.writeLine("That's out of your range. Enter again.");
                
                row = Integer.parseInt(console.readLine().orElseThrow());
            }
            
            console.setPrompt("Column > ");
            
            var column = Integer.parseInt(console.readLine().orElseThrow());
            
            while (column < 0 || column > board[0].length) {
                console.writeLine("That's out of your range. Enter again.");
                
                column = Integer.parseInt(console.readLine().orElseThrow());
            }
            
            console.newLine();
            
            switch (board[row][column]) {
                case 'S' -> {
                    board[row][column] = '!';
                    hits++;
                    torpedoes--;
                    console.writeLine("That's a hit!\n");
                }
                
                case '~' -> {
                    board[row][column] = 'm';
                    torpedoes--;
                    console.writeLine("That's a miss.\n");
                }
                
                default -> console.writeLine("You already hit that spot. Fire again.\n");
            }
            
            if (hits < 4 && torpedoes > 0) {
                var plural = (torpedoes == 1) ? "" : "es";
                
                console.writeLine("You have %d torpedo%s left.%n", torpedoes, plural);
            }
        }
        
        if (hits != 4) {
            return Event.FAIL;
        }
        
        victory();
        
        console.setPrompt("> ");
        
        return Event.SUCCESS;
    }
    
    @Override
    public void wrapUp(Console console) {
    
    }
    
    public void show(Console console, char[][] board) {
        console.writeLine("   0 1 2 3 4 5 6 7\n");
        
        for (var r = 0; r < 8; r++) {
            console.write("%d  ", r);
            
            for (var c = 0; c < 8; c++) {
                if (board[r][c] == 'S') {
                    console.write("~ ");
                }
                else {
                    console.write("%s ", board[r][c]);
                }
            }
            
            console.newLine();
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