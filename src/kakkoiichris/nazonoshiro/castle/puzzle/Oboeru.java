//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.puzzle;

import kakkoiichris.nazonoshiro.Console;

public class Oboeru extends Puzzle {
    private final char[][] board = new char[5][6];
    private final char[][] key = new char[5][6];
    
    public Oboeru() {
        super("Oboeru");
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
    
    public void showBoard() {
        Console.writeLine("   0   1   2   3   4   5");
        
        for (var r = 0; r < 5; r++) {
            Console.write("%d ", r);
            
            for (var c = 0; c < 6; c++) {
                Console.write("[%s] ", board[r][c]);
            }
            
            Console.newLine();
        }
    }
    
    public void reset(int r1, int c1, int r2, int c2) {
        board[r1][c1] = key[r1][c1];
        board[r2][c2] = key[r2][c2];
    }
    
    @Override
    public boolean play() {
        setUp();
        
        showBoard();
        
        var count = 0;
        
        var tries = 0;
        
        while (count < 15) {
            Console.writeLine("   0   1   2   3   4   5");
            
            for (var r = 0; r < 5; r++) {
                Console.write("%d ", r);
                
                for (var c = 0; c < 6; c++) {
                    Console.write("[%s] ", key[r][c]);
                }
                
                Console.newLine();
            }
            
            Console.writeLine("Flip two cards:\n");
            
            Console.setPrompt("Row 1 > ");
            
            var row1 = Console.readInt();
            
            while (row1 < 0 || row1 > board.length) {
                Console.writeLine("That's out of your range. Enter again.");
                
                row1 = Console.readInt();
            }
            
            Console.setPrompt("Column 1 > ");
            
            var column1 = Console.readInt();
            
            while (column1 < 0 || column1 > board[0].length) {
                Console.writeLine("That's out of your range. Enter again.");
                
                column1 = Console.readInt();
            }
            
            board[row1][column1] = '~';
            
            showBoard();
            
            Console.setPrompt("Row 2 > ");
            
            var row2 = Console.readInt();
            
            while (row2 < 0 || row2 > board.length) {
                Console.writeLine("That's out of your range. Enter again.");
                
                row2 = Console.readInt();
            }
            
            Console.setPrompt("Column 2 > ");
            
            var column2 = Console.readInt();
            
            while (column2 < 0 || column2 > board[0].length) {
                Console.writeLine("That's out of your range. Enter again.");
                
                column2 = Console.readInt();
            }
            
            board[row2][column2] = '~';
            
            Console.newLine();
            
            showBoard();
            
            if (key[row1][column1] == key[row2][column2]) {
                Console.writeLine("That's a match.");
                
                count++;
                
                tries = 0;
            }
            else {
                Console.writeLine("Not a match.");
                
                reset(row1, column1, row2, column2);
                
                tries++;
            }
            
            Console.newLine();
            
            if (tries == 3) {
                return false;
            }
        }
        
        victory();
        
        Console.setPrompt("> ");
        
        return true;
    }
}