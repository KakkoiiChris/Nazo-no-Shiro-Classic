//Christian Alexander, 10/06/2011, Period 8

   import java.io.*;
   import java.util.*;

    public class OriginalCastle extends CastleFloor
   {
       public OriginalCastle()
      {
         super(4, 5, "Original Castle");
      }
      
       public void setUpNew()
      {
         for(int r=0; r<4; r++)
         {
            for(int c=0; c<5; c++)
            {
               puzzleType[r][c] = 3;//(int)(Math.random()*3)+1;
               
               if(puzzleType[r][c] == 1)
                  puzzles[r][c] = new Oboeru();
               else
                  if(puzzleType[r][c] == 2)
                     puzzles[r][c] = new Seihoukei();
                  else
                     if(puzzleType[r][c] == 3)
                        puzzles[r][c] = new Kurobune();
            }
         }
         
         setUpCommon();
      }
      
       public void setUpLoad()
      {
         setUpCommon();
      }
      
       public void setUpCommon()
      {
         floorPlan[0][0] = new Room("North: Annex C",       puzzleType[0][0], 11, 10, 99, false);
         floorPlan[1][0] = new Room("West: Annex A",        4,                4,  3,  0,  false);
         floorPlan[2][0] = new Room("Secret Chamber A",     puzzleType[2][0], 14, 13, 99, false);
         floorPlan[3][0] = new Room("Secret Chamber B",     puzzleType[3][0], 15, 14, 99, false);
         floorPlan[0][1] = new Room("North: Annex A",       puzzleType[0][1], 5,  4,  99, false);
         floorPlan[1][1] = new Room("West Wing",            puzzleType[1][1], 3,  2,  99, false);
         floorPlan[2][1] = new Room("South: Annex A",       puzzleType[2][1], 9,  8,  99, false);
         floorPlan[3][1] = new Room("Secret Chamber C",     puzzleType[3][1], 16, 15, 99, false);
         floorPlan[0][2] = new Room("North Wing",           puzzleType[0][2], 2,  1,  99, false);
         floorPlan[1][2] = new Room("Central Hall",         puzzleType[1][2], 1,  0,  99, true);
         floorPlan[2][2] = new Room("South Wing",           puzzleType[2][2], 7,  6,  99, false);
         floorPlan[3][2] = new Room("Secret Chamber D",     puzzleType[3][2], 17, 16, 99, false);
         floorPlan[0][3] = new Room("North: Annex B",       4,                8,  7,  1,  false);
         floorPlan[1][3] = new Room("East Wing",            puzzleType[1][3], 6,  5,  99, false);
         floorPlan[2][3] = new Room("South: Annex B",       4,                12, 11, 2,  false);
         floorPlan[3][3] = new Room("Secret Chamber E",     puzzleType[3][3], 18, 17, 99, false);
         floorPlan[0][4] = new Room("Foyer",                puzzleType[0][4], 13, 12, 99, false);
         floorPlan[1][4] = new Room("East: Annex A",        puzzleType[1][4], 10, 9,  99, false);
         floorPlan[2][4] = new Room("Secret Treasure Room", 4,                20, 19, 3,  false);
         floorPlan[3][4] = new Room("Secret Chamber F",     puzzleType[3][4], 19, 18, 99, false);
      		
         floorPlan[0][0].setWall(0, 0, 'N');
         floorPlan[0][0].setWall(0, 0, 'W');
         floorPlan[0][0].setWall(0, 0, 'S');
         floorPlan[0][1].setWall(0, 1, 'N');
         floorPlan[0][1].setWall(0, 1, 'S');
         floorPlan[0][2].setWall(0, 2, 'N');
         floorPlan[0][3].setWall(0, 3, 'N');
         floorPlan[0][3].setWall(0, 3, 'E');
         floorPlan[0][3].setWall(0, 3, 'S');
         floorPlan[0][4].setWall(0, 4, 'E');
         floorPlan[0][4].setWall(0, 4, 'W');
         floorPlan[1][0].setWall(1, 0, 'N');
         floorPlan[1][0].setWall(1, 0, 'W');
         floorPlan[1][1].setWall(1, 1, 'N');
         floorPlan[1][1].setWall(1, 1, 'S');
         floorPlan[1][3].setWall(1, 3, 'N');
         floorPlan[1][3].setWall(1, 3, 'S');
         floorPlan[1][4].setWall(1, 4, 'E');
         floorPlan[1][4].setWall(1, 4, 'S');
         floorPlan[2][0].setWall(2, 0, 'E');
         floorPlan[2][0].setWall(2, 0, 'W');
         floorPlan[2][1].setWall(2, 1, 'N');
         floorPlan[2][1].setWall(2, 1, 'S');
         floorPlan[2][1].setWall(2, 1, 'W');
         floorPlan[2][2].setWall(2, 2, 'S');
         floorPlan[2][3].setWall(2, 3, 'N');
         floorPlan[2][3].setWall(2, 3, 'E');
         floorPlan[2][3].setWall(2, 3, 'S');
         floorPlan[2][4].setWall(2, 4, 'N');
         floorPlan[2][4].setWall(2, 4, 'E');
         floorPlan[2][4].setWall(2, 4, 'W');
         floorPlan[3][0].setWall(3, 0, 'W');
         floorPlan[3][0].setWall(3, 0, 'S');
         floorPlan[3][1].setWall(3, 1, 'N');
         floorPlan[3][1].setWall(3, 1, 'S');
         floorPlan[3][2].setWall(3, 2, 'N');
         floorPlan[3][2].setWall(3, 2, 'S');
         floorPlan[3][3].setWall(3, 3, 'N');
         floorPlan[3][3].setWall(3, 3, 'S');
         floorPlan[3][4].setWall(3, 4, 'E');
         floorPlan[3][4].setWall(3, 4, 'S');
      }
   }