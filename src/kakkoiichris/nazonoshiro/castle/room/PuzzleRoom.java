// Christian Alexander, 9/6/2022
package kakkoiichris.nazonoshiro.castle.room;

import kakkoiichris.nazonoshiro.castle.puzzle.Puzzle;

public class PuzzleRoom extends Room {
    private final Puzzle puzzle;
    
    public PuzzleRoom(String name, int key, int lock, boolean locked, Puzzle puzzle) {
        super(name, key, lock, locked);
        this.puzzle = puzzle;
    }
    
    public PuzzleRoom(String name, int key, int lock, boolean locked) {
        super(name, key, lock, locked);
        
        puzzle = Puzzle.random();
    }
    
    public Puzzle getPuzzle() {
        return puzzle;
    }
    
    @Override
    public void storeState() {
        super.storeState();
        
        puzzle.storeState();
    }
    
    @Override
    public void resetState() {
        super.resetState();
        
        puzzle.resetState();
    }
}