//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.puzzle;

import kakkoiichris.kotoba.Console;
import kakkoiichris.nazonoshiro.Event;
import kakkoiichris.nazonoshiro.Resources;
import kakkoiichris.nazonoshiro.Util;

import java.awt.event.KeyEvent;

public class Seihoukei extends Puzzle {
    private static final int SIZE = 6;
    private static final char NONE = ' ';
    private static final char PIECE = 'O';
    private final char[][] board = new char[SIZE][SIZE];
    private final char[][] key = new char[SIZE][SIZE];
    
    private int row, col;
    
    public Seihoukei() {
        super(
            "Seihoukei",
            """
                SeiHouKei
                =========
                
                Slide the rows and columns around
                to solve the board back to the
                original pattern.
                
                Controls
                ========
                
                   [W]     Move
                [A][S][D] Cursor
                   
                   [△]      Slide
                [◁][▽][▷] Row/Column"""
        );
    }
    
    @Override
    public void init(Console console) {
        setBoardAndKey();
        
        row = 0;
        col = 0;
        
        console.writeLine("Solve for this pattern:\n");
        
        show(console, key, -1, -1);
        
        console.newLine();
    }
    
    public void setBoardAndKey() {
        for (var r = 0; r < board.length; r++) {
            for (var c = 0; c < board[0].length; c++) {
                board[r][c] = NONE;
            }
        }
        
        for (var i = 0; i < 12; ) {
            var r = (int) (Math.random() * board.length);
            var c = (int) (Math.random() * board[0].length);
            
            if (board[r][c] != PIECE) {
                board[r][c] = PIECE;
                
                i++;
            }
        }
        
        setKey();
    }
    
    public void setKey() {
        var keyLine = Util.getRandom(Resources.getLines("seihoukei.txt"));
        
        var i = 0;
        
        for (var r = 0; r < SIZE; r++) {
            for (var c = 0; c < SIZE; c++) {
                key[r][c] = keyLine.charAt(i++);
            }
        }
    }
    
    @Override
    public Event doRound(Console console) {
        show(console, board, row, col);
        
        console.newLine();
        
        var input = console.readKey(true).orElseThrow();
        
        switch (input.getKeyCode()) {
            case KeyEvent.VK_W -> row = Math.max(row - 1, 0);
            
            case KeyEvent.VK_S -> row = Math.min(row + 1, 5);
            
            case KeyEvent.VK_A -> col = Math.max(col - 1, 0);
            
            case KeyEvent.VK_D -> col = Math.min(col + 1, 5);
            
            case KeyEvent.VK_UP -> shiftUp(col);
            
            case KeyEvent.VK_DOWN -> shiftDown(col);
            
            case KeyEvent.VK_LEFT -> shiftLeft(row);
            
            case KeyEvent.VK_RIGHT -> shiftRight(row);
            
            case KeyEvent.VK_SLASH -> {
                show(console, key, -1, -1);
                
                console.newLine();
            }
            
            default -> console.writeLine("'%s' is not a valid key!", input.getKeyChar());
        }
        
        if (wasSolved()) {
            return Event.SUCCESS;
        }
        
        return Event.CONTINUE;
    }
    
    public void show(Console console, char[][] board, int row, int col) {
        console.writeLine("#".repeat((SIZE * 3) + 4));
        
        for (var r = 0; r < SIZE; r++) {
            console.write("# ");
            
            for (var c = 0; c < SIZE; c++) {
                var format = (r == row && c == col) ? "[%s]" : " %s ";
                
                console.write(format, board[r][c]);
            }
            
            console.writeLine(" #");
        }
        console.write("#".repeat((SIZE * 3) + 4));
    }
    
    private void shiftUp(int c) {
        var temp = board[0][c];
        
        for (var i = 0; i < SIZE - 1; i++) {
            board[i][c] = board[i + 1][c];
        }
        
        board[5][c] = temp;
    }
    
    private void shiftDown(int c) {
        var temp = board[SIZE - 1][c];
        
        for (var i = SIZE - 1; i > 0; i--) {
            board[i][c] = board[i - 1][c];
        }
        
        board[0][c] = temp;
    }
    
    private void shiftLeft(int r) {
        var temp = board[r][0];
        
        for (var i = 0; i < SIZE - 1; i++) {
            board[r][i] = board[r][i + 1];
        }
        
        board[r][5] = temp;
    }
    
    private void shiftRight(int r) {
        var temp = board[r][SIZE - 1];
        
        for (var i = SIZE - 1; i > 0; i--) {
            board[r][i] = board[r][i - 1];
        }
        
        board[r][0] = temp;
    }
    
    public boolean wasSolved() {
        for (var r = 0; r < SIZE; r++) {
            for (var c = 0; c < SIZE; c++) {
                if (board[r][c] != key[r][c]) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    @Override
    public void wrapUp(Console console) {
        show(console, board, -1, -1);
        
        console.newLine();
    }
}