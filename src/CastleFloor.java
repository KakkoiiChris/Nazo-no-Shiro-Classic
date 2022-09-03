//Christian Alexander, 10/06/2011, Period 8

   import java.io.*;
   import java.util.*;

    public abstract class CastleFloor
   {
      protected Room[][] floorPlan;
      protected Puzzle[][] puzzles; //2-D int array to store puzzle types
      protected int[][] puzzleType;
      protected int XSize, YSize;
      protected String name;
   
       public CastleFloor(int xSize, int ySize, String n)
      {
         floorPlan = new Room[xSize][ySize];
         puzzles = new Puzzle[xSize][ySize];
         puzzleType = new int[xSize][ySize];
         XSize = xSize;
         YSize = ySize;
         name = n;
      }
      
       public abstract void setUpNew();
   	 
       public abstract void setUpLoad();
       
       public Room getRoom(int r, int c)
      {
         return floorPlan[r][c];
      }
      
       public int getXSize()
      {
         return XSize;
      }
   	
       public int getYSize()
      {
         return YSize;
      }
   	
       public String getName()
      {
         return name;
      }
   
       public void playPuzzle(int r, int c)
      {
         puzzles[r][c].play();
      }
   }