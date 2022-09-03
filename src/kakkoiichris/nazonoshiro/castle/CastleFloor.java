package kakkoiichris.nazonoshiro.castle;//Christian Alexander, 10/06/2011, Period 8

import kakkoiichris.nazonoshiro.castle.puzzle.Puzzle;

public abstract class CastleFloor {
    private Room[][] floorPlan;
    private Puzzle[][] puzzles; //2-D int array to store puzzle types
    private int[][] puzzleType;
    protected int XSize, YSize;
    protected String name;
    
    public CastleFloor(int xSize, int ySize, String n) {
        setFloorPlan(new Room[xSize][ySize]);
        setPuzzles(new Puzzle[xSize][ySize]);
        setPuzzleType(new int[xSize][ySize]);
        XSize = xSize;
        YSize = ySize;
        name = n;
    }
    
    public abstract void setUpNew();
    
    public abstract void setUpLoad();
    
    public Room getRoom(int r, int c) {
        return getFloorPlan()[r][c];
    }
    
    public int getXSize() {
        return XSize;
    }
    
    public int getYSize() {
        return YSize;
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