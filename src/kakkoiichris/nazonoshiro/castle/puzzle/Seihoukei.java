//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.puzzle;

import java.util.Scanner;

public class Seihoukei extends Puzzle {
    private final char[][] board = new char[6][6];
    private final char[][] key = new char[6][6];
    
    private final Scanner input = new Scanner(System.in);
    
    public Seihoukei() {
        super("Seihoukei");
    }
    
    public void shiftUp(int c) {
        var temp = board[0][c];
        
        for (var i = 0; i < 5; i++) {
            board[i][c] = board[i + 1][c];
        }
        
        board[5][c] = temp;
    }
    
    public void shiftDown(int c) {
        var temp = board[5][c];
        
        for (var i = 5; i > 0; i--) {
            board[i][c] = board[i - 1][c];
        }
        
        board[0][c] = temp;
    }
    
    public void shiftLeft(int r) {
        var temp = board[r][0];
        
        for (var i = 0; i < 5; i++) {
            board[r][i] = board[r][i + 1];
        }
        
        board[r][5] = temp;
    }
    
    public void shiftRight(int r) {
        var temp = board[r][5];
        
        for (var i = 5; i > 0; i--) {
            board[r][i] = board[r][i - 1];
        }
        
        board[r][0] = temp;
    }
    
    public void setUp() {
        for (var r = 0; r < board.length; r++) {
            for (var c = 0; c < board.length; c++) {
                board[r][c] = '~';
            }
        }
        
        for (int i = 0; i < 12; i++) {
            var r = (int) (Math.random() * 6);
            var c = (int) (Math.random() * 6);
            
            if (board[r][c] != '#') {
                board[r][c] = '#';
            }
            else {
                i--;
            }
        }
        
        for (var r = 0; r < key.length; r++) {
            for (var c = 0; c < key.length; c++) {
                key[r][c] = '~';
            }
        }
    }
    
    public void show(char[][] board) {
        System.out.println("  0 1 2 3 4 5");
        
        for (var r = 0; r < board.length; r++) {
            System.out.printf("%d ", r);
            
            for (var c = 0; c < board.length; c++) {
                System.out.printf("%s ", board[r][c]);
            }
            
            System.out.println();
        }
    }
    
    public void setKey() {
        var k = (int) (Math.random() * 20);
        
        switch (k) {
            case 0 -> {
                key[0][0] = '#';
                key[0][5] = '#';
                key[1][1] = '#';
                key[1][4] = '#';
                key[2][2] = '#';
                key[2][3] = '#';
                key[3][3] = '#';
                key[3][2] = '#';
                key[4][4] = '#';
                key[4][1] = '#';
                key[5][5] = '#';
                key[5][0] = '#';
            }
            
            case 1 -> {
                key[0][0] = '#';
                key[0][4] = '#';
                key[0][5] = '#';
                key[1][0] = '#';
                key[1][3] = '#';
                key[2][1] = '#';
                key[3][4] = '#';
                key[4][2] = '#';
                key[4][5] = '#';
                key[5][0] = '#';
                key[5][1] = '#';
                key[5][5] = '#';
            }
            
            case 2 -> {
                key[0][0] = '#';
                key[0][1] = '#';
                key[1][0] = '#';
                key[1][3] = '#';
                key[2][3] = '#';
                key[2][4] = '#';
                key[3][1] = '#';
                key[3][2] = '#';
                key[4][2] = '#';
                key[4][5] = '#';
                key[5][4] = '#';
                key[5][5] = '#';
            }
            
            case 3 -> {
                key[0][0] = '#';
                key[0][5] = '#';
                key[1][2] = '#';
                key[1][3] = '#';
                key[2][1] = '#';
                key[2][4] = '#';
                key[3][1] = '#';
                key[3][4] = '#';
                key[4][2] = '#';
                key[4][3] = '#';
                key[5][0] = '#';
                key[5][5] = '#';
            }
            
            case 4 -> {
                key[0][2] = '#';
                key[0][3] = '#';
                key[1][1] = '#';
                key[1][4] = '#';
                key[2][0] = '#';
                key[2][5] = '#';
                key[3][0] = '#';
                key[3][5] = '#';
                key[4][1] = '#';
                key[4][4] = '#';
                key[5][2] = '#';
                key[5][3] = '#';
            }
            
            case 5 -> {
                key[0][0] = '#';
                key[0][1] = '#';
                key[1][4] = '#';
                key[1][5] = '#';
                key[2][0] = '#';
                key[2][1] = '#';
                key[3][4] = '#';
                key[3][5] = '#';
                key[4][0] = '#';
                key[4][1] = '#';
                key[5][4] = '#';
                key[5][5] = '#';
            }
            
            case 6 -> {
                key[0][0] = '#';
                key[0][5] = '#';
                key[1][0] = '#';
                key[1][2] = '#';
                key[1][3] = '#';
                key[1][5] = '#';
                key[4][0] = '#';
                key[4][2] = '#';
                key[4][3] = '#';
                key[4][5] = '#';
                key[5][0] = '#';
                key[5][5] = '#';
            }
            
            case 7 -> {
                key[1][0] = '#';
                key[1][2] = '#';
                key[1][3] = '#';
                key[2][0] = '#';
                key[2][2] = '#';
                key[2][4] = '#';
                key[3][1] = '#';
                key[3][3] = '#';
                key[3][5] = '#';
                key[4][2] = '#';
                key[4][3] = '#';
                key[4][5] = '#';
            }
            
            case 8 -> {
                key[0][0] = '#';
                key[0][4] = '#';
                key[1][1] = '#';
                key[1][3] = '#';
                key[2][2] = '#';
                key[2][5] = '#';
                key[3][0] = '#';
                key[3][3] = '#';
                key[4][2] = '#';
                key[4][4] = '#';
                key[5][1] = '#';
                key[5][5] = '#';
            }
            
            case 9 -> {
                key[0][0] = '#';
                key[0][3] = '#';
                key[0][5] = '#';
                key[1][1] = '#';
                key[1][4] = '#';
                key[2][0] = '#';
                key[3][5] = '#';
                key[4][1] = '#';
                key[4][4] = '#';
                key[5][0] = '#';
                key[5][2] = '#';
                key[5][5] = '#';
            }
            
            case 10 -> {
                key[0][1] = '#';
                key[0][5] = '#';
                key[1][0] = '#';
                key[1][3] = '#';
                key[1][4] = '#';
                key[2][0] = '#';
                key[3][5] = '#';
                key[4][1] = '#';
                key[4][2] = '#';
                key[4][5] = '#';
                key[5][0] = '#';
                key[5][4] = '#';
            }
            
            case 11 -> {
                key[0][1] = '#';
                key[0][3] = '#';
                key[1][5] = '#';
                key[2][0] = '#';
                key[2][2] = '#';
                key[2][3] = '#';
                key[3][2] = '#';
                key[3][3] = '#';
                key[3][5] = '#';
                key[4][0] = '#';
                key[5][2] = '#';
                key[5][4] = '#';
            }
            
            case 12 -> {
                key[0][1] = '#';
                key[0][4] = '#';
                key[1][0] = '#';
                key[1][1] = '#';
                key[1][4] = '#';
                key[1][5] = '#';
                key[4][0] = '#';
                key[4][1] = '#';
                key[4][4] = '#';
                key[4][5] = '#';
                key[5][1] = '#';
                key[5][4] = '#';
            }
            
            case 13 -> {
                key[0][1] = '#';
                key[0][3] = '#';
                key[1][0] = '#';
                key[2][1] = '#';
                key[2][3] = '#';
                key[2][5] = '#';
                key[3][0] = '#';
                key[3][2] = '#';
                key[3][4] = '#';
                key[4][5] = '#';
                key[5][2] = '#';
                key[5][4] = '#';
            }
            
            case 14 -> {
                key[0][0] = '#';
                key[0][3] = '#';
                key[1][1] = '#';
                key[1][4] = '#';
                key[2][2] = '#';
                key[2][5] = '#';
                key[3][0] = '#';
                key[3][3] = '#';
                key[4][1] = '#';
                key[4][4] = '#';
                key[5][2] = '#';
                key[5][5] = '#';
            }
            
            case 15 -> {
                key[0][0] = '#';
                key[0][2] = '#';
                key[0][5] = '#';
                key[1][3] = '#';
                key[2][1] = '#';
                key[2][5] = '#';
                key[3][0] = '#';
                key[3][4] = '#';
                key[4][2] = '#';
                key[5][0] = '#';
                key[5][3] = '#';
                key[5][5] = '#';
            }
            
            case 16 -> {
                key[0][0] = '#';
                key[0][3] = '#';
                key[1][0] = '#';
                key[1][4] = '#';
                key[2][0] = '#';
                key[2][1] = '#';
                key[3][4] = '#';
                key[3][5] = '#';
                key[4][1] = '#';
                key[4][5] = '#';
                key[5][2] = '#';
                key[5][5] = '#';
            }
            
            case 17 -> {
                key[0][0] = '#';
                key[0][5] = '#';
                key[1][2] = '#';
                key[1][3] = '#';
                key[2][1] = '#';
                key[2][4] = '#';
                key[3][0] = '#';
                key[3][5] = '#';
                key[4][2] = '#';
                key[4][3] = '#';
                key[5][1] = '#';
                key[5][4] = '#';
            }
            
            case 18 -> {
                key[0][0] = '#';
                key[0][2] = '#';
                key[1][3] = '#';
                key[1][5] = '#';
                key[2][1] = '#';
                key[2][3] = '#';
                key[3][2] = '#';
                key[3][4] = '#';
                key[4][0] = '#';
                key[4][2] = '#';
                key[5][3] = '#';
                key[5][5] = '#';
            }
            
            case 19 -> {
                key[0][0] = '#';
                key[0][5] = '#';
                key[1][1] = '#';
                key[1][4] = '#';
                key[2][1] = '#';
                key[2][4] = '#';
                key[3][0] = '#';
                key[3][5] = '#';
                key[4][0] = '#';
                key[4][5] = '#';
                key[5][1] = '#';
                key[5][4] = '#';
            }
        }
    }
    
    public boolean win() {
        for (var r = 0; r < board.length; r++) {
            for (var c = 0; c < board.length; c++) {
                if (board[r][c] != key[r][c]) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    @Override
    public boolean play() {
        System.out.println("<[Seihoukei]>\n");
        
        setUp();
        setKey();
        
        System.out.println("Solve for this pattern:\n");
        
        while (!win()) {
            System.out.println("  [Solution]");
            
            show(key);
            
            System.out.println();
            
            show(board);
            
            System.out.println("\nMovement:\n(Up/ Down/ Left/ Right)");
            
            var choice = input.next().toLowerCase();
            
            switch (choice) {
                case "up" -> {
                    System.out.print("Column: ");
                    
                    var t = input.nextInt();
                    
                    while (t > 5 || t < 0) {
                        System.out.print("Invalid: enter again\nColumn: ");
                        
                        t = input.nextInt();
                    }
                    
                    shiftUp(t);
                }
                
                case "down" -> {
                    System.out.print("Column: ");
                    
                    var t = input.nextInt();
                    
                    while (t > 5 || t < 0) {
                        System.out.print("Invalid: enter again\nColumn: ");
                        
                        t = input.nextInt();
                    }
                    
                    shiftDown(t);
                }
                
                case "left" -> {
                    System.out.print("Row: ");
                    
                    var t = input.nextInt();
                    
                    while (t > 5 || t < 0) {
                        System.out.print("Invalid: enter again\nRow: ");
                        
                        t = input.nextInt();
                    }
                    
                    shiftLeft(t);
                }
                
                case "right" -> {
                    System.out.print("Row: ");
                    
                    var t = input.nextInt();
                    
                    while (t > 5 || t < 0) {
                        System.out.print("Invalid: enter again\nRow: ");
                        
                        t = input.nextInt();
                    }
                    
                    shiftRight(t);
                }
                
                default -> {
                }
            }
        }
        
        victory();
        
        return true;
    }
}