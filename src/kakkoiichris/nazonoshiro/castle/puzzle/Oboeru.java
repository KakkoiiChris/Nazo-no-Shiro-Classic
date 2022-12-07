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
                    board[r][c] = letter;
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
            
            Console.writeLine("Flip two cards:\nRow 1:");
            
            var r1 = Console.readInt();
            
            Console.write("\nColumn 1:");
            
            var c1 = Console.readInt();
            
            board[r1][c1] = '~';
            
            showBoard();
            
            Console.write("\nRow 2:");
            
            var r2 = Console.readInt();
            
            Console.write("\nColumn 2:");
            
            var c2 = Console.readInt();
            
            board[r2][c2] = '~';
            
            Console.newLine();
            
            showBoard();
            
            if (key[r1][c1] == key[r2][c2]) {
                Console.writeLine("That's a match.");
                
                count++;
                
                tries = 0;
            }
            else {
                Console.writeLine("Not a match.");
                
                reset(r1, c1, r2, c2);
                
                tries++;
            }
            
            Console.newLine();
            
            if (tries == 3) {
                return false;
            }
        }
        
        victory();
        
        return true;
    }
}