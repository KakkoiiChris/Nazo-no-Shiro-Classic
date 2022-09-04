package kakkoiichris.nazonoshiro.castle.puzzle;//Christian Alexander, 5/12/11, Pd. 6

public abstract class Puzzle {
    private final String name;
    
    protected boolean won = false, wonLast = false;
    
    public Puzzle(String name) {
        this.name = name;
    }
    
    public boolean isWon() {
        return won;
    }
    
    public String getName() {
        return name;
    }
    
    public void storeState() {
        wonLast = won;
    }
    
    public void resetState() {
        won = wonLast;
    }
    
    public void victory() {
        System.out.println("""
            You won!
            
            You have earned a key.
            
            Now to figure out which door it unlocks...
            """.stripIndent());
        
        won = true;
    }
    
    public abstract void play();
}