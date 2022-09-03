package kakkoiichris.nazonoshiro.castle.puzzle;//Christian Alexander, 5/12/11, Pd. 6

import java.util.Scanner;

public class Oboeru extends Puzzle {
    public char[][] board = new char[5][6];
    public char[][] key = new char[5][6];
    public int r, c, a, b, x, y, i, count = 0;
    public Scanner input = new Scanner(System.in);
    
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
                r = (int) (Math.random() * 5);
                c = (int) (Math.random() * 6);
                
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
        
        for (r = 0; r < 5; r++) {
            System.out.print(r + " ");
            
            for (c = 0; c < 6; c++) {
                System.out.print("[" + board[r][c] + "] ");
            }
            
            System.out.println();
        }
    }
    
    public void reset(int x, int y, int a, int b) {
        board[x][y] = key[x][y];
        board[a][b] = key[a][b];
    }
    
    public void play() {
        setUp();
        
        showBoard();
        
        while (count < 15) {
            System.out.println("   0   1   2   3   4   5");
        
            for (r = 0; r < 5; r++) {
                System.out.print(r + " ");
        
                for (c = 0; c < 6; c++) {
                    System.out.print("[" + key[r][c] + "] ");
                }
        
                System.out.println();
            }
            
            System.out.println("Flip two cards:\nRow 1:");
            
            x = input.nextInt();
            
            System.out.print("\nColumn 1:");
            
            y = input.nextInt();
            
            board[x][y] = '~';
            
            showBoard();
            
            System.out.print("\nRow 2:");
            
            a = input.nextInt();
            
            System.out.print("\nColumn 2:");
            
            b = input.nextInt();
    
            board[a][b] = '~';
    
            System.out.println();
            
            showBoard();
            
            if (key[x][y] == key[a][b]) {
                System.out.println("That's a match.");
                
                count++;
            }
            else {
                System.out.println("Not a match.");
                
                reset(x, y, a, b);
            }
            
            System.out.println();
        }
        
        victory();
    }
}