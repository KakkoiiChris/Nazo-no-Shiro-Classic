package kakkoiichris.nazonoshiro.castle.puzzle;//Christian Alexander, 5/12/11, Pd. 6

public abstract class Puzzle {
    private String name;
    protected boolean won = false, won1 = false;
    
    public Puzzle(String n) {
        name = n;
    }
    
    public boolean getWon() {
        return won;
    }
    
    public String getName() {
        return name;
    }
    
    public void storeState() {
        won1 = won;
    }
    
    public void resetState() {
        won = won1;
    }
    
    public void victory() {
        System.out.println("You won!");
        System.out.println();
        System.out.println("You have earned a key.");
        System.out.println();
        System.out.println("Now to figure out which door it unlocks...");
        System.out.println();
        won = true;
    }
    
    public abstract void play();
}