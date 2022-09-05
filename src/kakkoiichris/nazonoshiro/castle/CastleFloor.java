//Christian Alexander, 10/06/2011, Period 8
package kakkoiichris.nazonoshiro.castle;

import kakkoiichris.nazonoshiro.castle.puzzle.Puzzle;

public abstract class CastleFloor {
    protected final int xSize, ySize;
    protected final String name;
    
    private Room[][] floorPlan;
    private Puzzle[][] puzzles; //2-D int array to store puzzle types
    private int[][] puzzleType;
    
    public CastleFloor(int xSize, int ySize, String name) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.name = name;
        
        floorPlan = new Room[xSize][ySize];
        puzzles = new Puzzle[xSize][ySize];
        puzzleType = new int[xSize][ySize];
    }
    
    public abstract void setUpNew();
    
    public abstract void setUpLoad();
    
    public Room getRoom(int r, int c) {
        return getFloorPlan()[r][c];
    }
    
    public int getColumns() {
        return xSize;
    }
    
    public int getRows() {
        return ySize;
    }
    
    public String getName() {
        return name;
    }
    
    public void playPuzzle(int r, int c) {
        getPuzzles()[r][c].play();
    }
    
    public int[][] getPuzzleType() {
        return puzzleType;
    }
    
    public void setPuzzleType(int[][] puzzleType) {
        this.puzzleType = puzzleType;
    }
    
    public Room[][] getFloorPlan() {
        return floorPlan;
    }
    
    public void setFloorPlan(Room[][] floorPlan) {
        this.floorPlan = floorPlan;
    }
    
    public Puzzle[][] getPuzzles() {
        return puzzles;
    }
    
    public void setPuzzles(Puzzle[][] puzzles) {
        this.puzzles = puzzles;
    }
}