//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.puzzle;

import kakkoiichris.kotoba.Console;
import kakkoiichris.kotoba.Util;
import kakkoiichris.nazonoshiro.Event;

import java.awt.event.KeyEvent;

public class Oboeru extends Puzzle {
    private final char[][] board = new char[5][6];
    private final char[][] key = new char[5][6];
    
    public Oboeru() {
        super("Oboeru","");
    }
    
    @Override
    public void init(Console console) {
    
    }
    
    @Override
    public Event doRound(Console console) {
        setUp();
        
        showBoard(console);
        
        var count = 0;
        
        var tries = 0;
        
        while (count < 15) {
            console.writeLine("   0   1   2   3   4   5");
            
            for (var r = 0; r < 5; r++) {
                console.write("%d ", r);
                
                for (var c = 0; c < 6; c++) {
                    console.write("[%s] ", key[r][c]);
                }
                
                console.newLine();
            }
            
            console.writeLine("Flip two cards:\n");
            
            console.setPrompt("Row 1 > ");
            
            int row1 = Util.toInt(console.readLine().orElseThrow()).orElse(-1);
            
            while (row1 < 0 || row1 > board.length) {
                console.writeLine("That's out of your range. Enter again.");
                
                row1 = Util.toInt(console.readLine().orElseThrow()).orElse(-1);
            }
            
            console.setPrompt("Column 1 > ");
            
            int column1 = Util.toInt(console.readLine().orElseThrow()).orElse(-1);
            
            while (column1 < 0 || column1 > board[0].length) {
                console.writeLine("That's out of your range. Enter again.");
                
                column1 = Util.toInt(console.readLine().orElseThrow()).orElse(-1);
            }
            
            board[row1][column1] = '~';
            
            showBoard(console);
            
            console.setPrompt("Row 2 > ");
            
            int row2 = Util.toInt(console.readLine().orElseThrow()).orElse(-1);
            
            while (row2 < 0 || row2 > board.length) {
                console.writeLine("That's out of your range. Enter again.");
                
                row2 = Util.toInt(console.readLine().orElseThrow()).orElse(-1);
            }
            
            console.setPrompt("Column 2 > ");
            
            int column2 = Util.toInt(console.readLine().orElseThrow()).orElse(-1);
            
            while (column2 < 0 || column2 > board[0].length) {
                console.writeLine("That's out of your range. Enter again.");
                
                column2 = Util.toInt(console.readLine().orElseThrow()).orElse(-1);
            }
            
            board[row2][column2] = '~';
            
            console.newLine();
            
            showBoard(console);
            
            if (key[row1][column1] == key[row2][column2]) {
                console.writeLine("That's a match.");
                
                count++;
                
                tries = 0;
            }
            else {
                console.writeLine("Not a match.");
                
                reset(row1, column1, row2, column2);
                
                tries++;
            }
            
            console.newLine();
            
            if (tries == 3) {
                return Event.FAIL;
            }
        }
        
        victory();
        
        console.setPrompt("> ");
        
        return Event.SUCCESS;
    }
    
    @Override
    public void wrapUp(Console console) {
    
    }
    
    public void setUp() {
        for (var r = 0; r < 5; r++) {
            for (var c = 0; c < 6; c++) {
                board[r][c] = '~';
                key[r][c] = '~';
            }
        }
        
        for (var letter = 'A'; letter < 'O'; letter++) {
            for (var i = 0; i < 2; i++) {
                var r = (int) (Math.random() * 5);
                var c = (int) (Math.random() * 6);
                
                if (key[r][c] == '~') {
                    key[r][c] = letter;
                }
                else {
                    i--;
                }
            }
        }
    }
    
    public void showBoard(Console console) {
        console.writeLine("   0   1   2   3   4   5");
        
        for (var r = 0; r < 5; r++) {
            console.write("%d ", r);
            
            for (var c = 0; c < 6; c++) {
                console.write("[%s] ", board[r][c]);
            }
            
            console.newLine();
        }
    }
    
    public void reset(int r1, int c1, int r2, int c2) {
        board[r1][c1] = key[r1][c1];
        board[r2][c2] = key[r2][c2];
    }
}