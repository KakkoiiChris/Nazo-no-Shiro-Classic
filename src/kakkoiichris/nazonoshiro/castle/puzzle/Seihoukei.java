//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.puzzle;

import kakkoiichris.kotoba.Console;
import kakkoiichris.kotoba.util.Util;

public class Seihoukei extends Puzzle {
    private final char[][] board = new char[6][6];
    private final char[][] key = new char[6][6];
    
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
            for (var c = 0; c < board[0].length; c++) {
                board[r][c] = '~';
            }
        }
        
        for (int i = 0; i < 12; i++) {
            var r = (int) (Math.random() * board.length);
            var c = (int) (Math.random() * board[0].length);
            
            if (board[r][c] != '#') {
                board[r][c] = '#';
            }
            else {
                i--;
            }
        }
        
        for (var r = 0; r < key.length; r++) {
            for (var c = 0; c < key[0].length; c++) {
                key[r][c] = '~';
            }
        }
    }
    
    public void show(Console console, char[][] board) {
        console.writeLine("  0 1 2 3 4 5");
        
        for (var r = 0; r < board.length; r++) {
            console.write("%d ", r);
            
            for (var c = 0; c < board[0].length; c++) {
                console.write("%s ", board[r][c]);
            }
            
            console.newLine();
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
    public boolean play(Console console) {
        console.writeLine("<[Seihoukei]>\n");
        
        setUp();
        setKey();
        
        console.writeLine("Solve for this pattern:\n");
        
        while (!win()) {
            console.writeLine("  [Solution]");
            
            show(console, key);
            
            console.newLine();
            
            show(console, board);
            
            console.newLine();
            
            console.setPrompt("Up / Down / Left / Right > ");
            
            var direction = console.readLine().orElseThrow().trim().toLowerCase();
            
            switch (direction) {
                case "up" -> {
                    console.setPrompt("Column > ");
                    
                    int column = Util.toInt(console.readLine().orElseThrow()).orElse(-1);
                    
                    while (column < 0 || column > board[0].length) {
                        console.write("Invalid: enter again\nColumn: ");
                        
                        column = Util.toInt(console.readLine().orElseThrow()).orElse(-1);
                    }
                    
                    shiftUp(column);
                }
                
                case "down" -> {
                    console.setPrompt("Column > ");
                    
                    int column = Util.toInt(console.readLine().orElseThrow()).orElse(-1);
                    
                    while (column < 0 || column > board[0].length) {
                        console.write("Invalid: enter again\nColumn: ");
                        
                        column = Util.toInt(console.readLine().orElseThrow()).orElse(-1);
                    }
                    
                    shiftDown(column);
                }
                
                case "left" -> {
                    console.setPrompt("Row > ");
                    
                    int row = Util.toInt(console.readLine().orElseThrow()).orElse(-1);
                    
                    while (row < 0 || row > board.length) {
                        console.write("Invalid: enter again\nRow: ");
                        
                        row = Util.toInt(console.readLine().orElseThrow()).orElse(-1);
                    }
                    
                    shiftLeft(row);
                }
                
                case "right" -> {
                    console.setPrompt("Row > ");
                    
                    int row = Util.toInt(console.readLine().orElseThrow()).orElse(-1);
                    
                    while (row < 0 || row > board.length) {
                        console.write("Invalid: enter again\nRow: ");
                        
                        row = Util.toInt(console.readLine().orElseThrow()).orElse(-1);
                    }
                    
                    shiftRight(row);
                }
                
                default -> console.writeLine("%s is not a valid direction!", direction);
            }
        }
        
        victory();
        
        console.setPrompt("> ");
        
        return true;
    }
}