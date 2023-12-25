//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.puzzle;

import kakkoiichris.kotoba.Console;
import kakkoiichris.nazonoshiro.ResetValue;
import kakkoiichris.nazonoshiro.Resettable;
import kakkoiichris.nazonoshiro.Util;

public abstract class Puzzle implements Resettable {
    private final String name;
    
    protected final ResetValue<Boolean> solved = new ResetValue<>(false);
    
    public Puzzle(String name) {
        this.name = name;
    }
    
    public static Puzzle random() {
        return switch (Util.random.nextInt(3)) {
            case 0 -> new Kurobune();
            
            case 1 -> new Oboeru();
            
            default -> new Seihoukei();
        };
    }
    
    public boolean isSolved() {
        return solved.get();
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public void storeState() {
        solved.storeState();
    }
    
    @Override
    public void resetState() {
        solved.resetState();
    }
    
    public void victory() {
        solved.set(true);
    }
    
    public abstract boolean play(Console console);
}