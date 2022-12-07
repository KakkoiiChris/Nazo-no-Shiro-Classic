//Christian Alexander, 8/8/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.puzzle;

import kakkoiichris.nazonoshiro.Console;

import java.util.ArrayList;
import java.util.List;

public class IroIro extends Puzzle {
    private final char[][] board = new char[4][4];
    private final char[][] guide = new char[4][4];
    
    private final List<Character> colors = new ArrayList<>();
    private final List<Character> level1 = new ArrayList<>();
    private final List<Character> level2 = new ArrayList<>();
    private final List<Character> level3 = new ArrayList<>();
    private final List<Character> level4 = new ArrayList<>();
    
    public IroIro() {
        super("IroIro");
    }
    
    @Override
    public boolean play() {
    
        Console.writeLine("  <[IROIRO]>\n");
        
        setUp();
        
        show(board);
        
        Console.write(" > ");
        
        var temp = Console.read();
        
        Console.newLine();
    
        victory();
        
        return true;
    }
    
    public void show(char[][] guide) {
        for (var r = 0; r < 4; r++) {
            for (var c = 0; c < 4; c++) {
                Console.write("%s ", guide[r][c]);
            }
            
            Console.newLine();
        }
    }
    
    public void setUp() {
        colors.add('R');
        colors.add('Y');
        colors.add('G');
        colors.add('B');
        
        for (var i = 0; i < 4; i++) {
            var random = (int) (Math.random() * colors.size());
            
            level1.add(colors.get(random));
            colors.remove(random);
        }
        
        colors.add('R');
        colors.add('Y');
        colors.add('G');
        colors.add('B');
        
        for (var i = 0; i < 4; i++) {
            var random = (int) (Math.random() * colors.size());
            
            level2.add(colors.get(random));
            colors.remove(random);
        }
        
        colors.add('R');
        colors.add('Y');
        colors.add('G');
        colors.add('B');
        
        for (var i = 0; i < 4; i++) {
            var random = (int) (Math.random() * colors.size());
            
            level3.add(colors.get(random));
            colors.remove(random);
        }
        
        colors.add('R');
        colors.add('Y');
        colors.add('G');
        colors.add('B');
        
        for (var i = 0; i < 4; i++) {
            var random = (int) (Math.random() * colors.size());
            
            level4.add(colors.get(random));
            colors.remove(random);
        }
        
        for (var i = 0; i < 4; i++) {
            guide[0][i] = level1.get(i);
        }
        
        for (var i = 0; i < 4; i++) {
            guide[1][i] = level2.get(i);
        }
        
        for (var i = 0; i < 4; i++) {
            guide[2][i] = level3.get(i);
        }
        
        for (var i = 0; i < 4; i++) {
            guide[3][i] = level4.get(i);
        }
    }
}