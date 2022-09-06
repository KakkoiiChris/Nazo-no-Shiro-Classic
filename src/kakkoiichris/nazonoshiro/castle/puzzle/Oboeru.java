//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.puzzle;

import java.util.Scanner;

public class Oboeru extends Puzzle {
    private final char[][] board = new char[5][6];
    private final char[][] key = new char[5][6];
    
    private final Scanner input = new Scanner(System.in);
    
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
        System.out.println("   0   1   2   3   4   5");
        
        for (var r = 0; r < 5; r++) {
            System.out.printf("%d ", r);
            
            for (var c = 0; c < 6; c++) {
                System.out.printf("[%s] ", board[r][c]);
            }
            
            System.out.println();
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
            System.out.println("   0   1   2   3   4   5");
            
            for (var r = 0; r < 5; r++) {
                System.out.printf("%d ", r);
                
                for (var c = 0; c < 6; c++) {
                    System.out.printf("[%s] ", key[r][c]);
                }
                
                System.out.println();
            }
            
            System.out.println("Flip two cards:\nRow 1:");
            
            var r1 = input.nextInt();
            
            System.out.print("\nColumn 1:");
            
            var c1 = input.nextInt();
            
            board[r1][c1] = '~';
            
            showBoard();
            
            System.out.print("\nRow 2:");
            
            var r2 = input.nextInt();
            
            System.out.print("\nColumn 2:");
            
            var c2 = input.nextInt();
            
            board[r2][c2] = '~';
            
            System.out.println();
            
            showBoard();
            
            if (key[r1][c1] == key[r2][c2]) {
                System.out.println("That's a match.");
                
                count++;
                
                tries = 0;
            }
            else {
                System.out.println("Not a match.");
                
                reset(r1, c1, r2, c2);
                
                tries++;
            }
            
            System.out.println();
            
            if (tries == 3) {
                return false;
            }
        }
        
        victory();
        
        return true;
    }
}