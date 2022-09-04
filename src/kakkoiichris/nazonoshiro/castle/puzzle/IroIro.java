package kakkoiichris.nazonoshiro.castle.puzzle;//Christian Alexander, 8/8/11, Pd. 6

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IroIro extends Puzzle {
    private final Scanner input = new Scanner(System.in);
    
    private final char[][] board = new char[4][4];
    private final char[][] guide = new char[4][4];
    
    private int l = 0;
    
    private final List<Character> colors = new ArrayList<>();
    private final List<Character> level1 = new ArrayList<>();
    private final List<Character> level2 = new ArrayList<>();
    private final List<Character> level3 = new ArrayList<>();
    private final List<Character> level4 = new ArrayList<>();
    
    public IroIro() {
        super("IroIro");
    }
    
    public void play() {
        var again = "y";
        
        while (again.equals("y") || again.equals("Y")) {
            System.out.println("  <[IROIRO]>\n");
            
            setUp(guide);
            
            show(board);
            
            System.out.print(" > ");
            
            var temp = input.next();
            
            System.out.println();
            
            if (l > 0) {
                System.out.println("Try again? y/n");
                
                again = input.next();
                
                l = 0;
            }
            else {
                victory();
                
                again = "n";
            }
        }
    }
    
    public void show(char[][] guide) {
        for (var r = 0; r < 4; r++) {
            for (var c = 0; c < 4; c++) {
                System.out.printf("%s ", guide[r][c]);
            }
            
            System.out.println();
        }
    }
    
    public void setUp(char[][] board) {
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