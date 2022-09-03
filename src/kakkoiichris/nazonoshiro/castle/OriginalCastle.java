package kakkoiichris.nazonoshiro.castle;//Christian Alexander, 10/06/2011, Period 8

   import kakkoiichris.nazonoshiro.castle.puzzle.Kurobune;
   import kakkoiichris.nazonoshiro.castle.puzzle.Oboeru;
   import kakkoiichris.nazonoshiro.castle.puzzle.Seihoukei;

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
               getPuzzleType()[r][c] = 3;//(int)(Math.random()*3)+1;
               
               if(getPuzzleType()[r][c] == 1)
                  getPuzzles()[r][c] = new Oboeru();
               else
                  if(getPuzzleType()[r][c] == 2)
                     getPuzzles()[r][c] = new Seihoukei();
                  else
                     if(getPuzzleType()[r][c] == 3)
                        getPuzzles()[r][c] = new Kurobune();
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
         getFloorPlan()[0][0] = new Room("North: Annex C",       getPuzzleType()[0][0], 11, 10, 99, false);
         getFloorPlan()[1][0] = new Room("West: Annex A",        4,                4,  3,  0,  false);
         getFloorPlan()[2][0] = new Room("Secret Chamber A",     getPuzzleType()[2][0], 14, 13, 99, false);
         getFloorPlan()[3][0] = new Room("Secret Chamber B",     getPuzzleType()[3][0], 15, 14, 99, false);
         getFloorPlan()[0][1] = new Room("North: Annex A",       getPuzzleType()[0][1], 5,  4,  99, false);
         getFloorPlan()[1][1] = new Room("West Wing",            getPuzzleType()[1][1], 3,  2,  99, false);
         getFloorPlan()[2][1] = new Room("South: Annex A",       getPuzzleType()[2][1], 9,  8,  99, false);
         getFloorPlan()[3][1] = new Room("Secret Chamber C",     getPuzzleType()[3][1], 16, 15, 99, false);
         getFloorPlan()[0][2] = new Room("North Wing",           getPuzzleType()[0][2], 2,  1,  99, false);
         getFloorPlan()[1][2] = new Room("Central Hall",         getPuzzleType()[1][2], 1,  0,  99, true);
         getFloorPlan()[2][2] = new Room("South Wing",           getPuzzleType()[2][2], 7,  6,  99, false);
         getFloorPlan()[3][2] = new Room("Secret Chamber D",     getPuzzleType()[3][2], 17, 16, 99, false);
         getFloorPlan()[0][3] = new Room("North: Annex B",       4,                8,  7,  1,  false);
         getFloorPlan()[1][3] = new Room("East Wing",            getPuzzleType()[1][3], 6,  5,  99, false);
         getFloorPlan()[2][3] = new Room("South: Annex B",       4,                12, 11, 2,  false);
         getFloorPlan()[3][3] = new Room("Secret Chamber E",     getPuzzleType()[3][3], 18, 17, 99, false);
         getFloorPlan()[0][4] = new Room("Foyer",                getPuzzleType()[0][4], 13, 12, 99, false);
         getFloorPlan()[1][4] = new Room("East: Annex A",        getPuzzleType()[1][4], 10, 9,  99, false);
         getFloorPlan()[2][4] = new Room("Secret Treasure Room", 4,                20, 19, 3,  false);
         getFloorPlan()[3][4] = new Room("Secret Chamber F",     getPuzzleType()[3][4], 19, 18, 99, false);
      		
         getFloorPlan()[0][0].setWall(0, 0, 'N');
         getFloorPlan()[0][0].setWall(0, 0, 'W');
         getFloorPlan()[0][0].setWall(0, 0, 'S');
         getFloorPlan()[0][1].setWall(0, 1, 'N');
         getFloorPlan()[0][1].setWall(0, 1, 'S');
         getFloorPlan()[0][2].setWall(0, 2, 'N');
         getFloorPlan()[0][3].setWall(0, 3, 'N');
         getFloorPlan()[0][3].setWall(0, 3, 'E');
         getFloorPlan()[0][3].setWall(0, 3, 'S');
         getFloorPlan()[0][4].setWall(0, 4, 'E');
         getFloorPlan()[0][4].setWall(0, 4, 'W');
         getFloorPlan()[1][0].setWall(1, 0, 'N');
         getFloorPlan()[1][0].setWall(1, 0, 'W');
         getFloorPlan()[1][1].setWall(1, 1, 'N');
         getFloorPlan()[1][1].setWall(1, 1, 'S');
         getFloorPlan()[1][3].setWall(1, 3, 'N');
         getFloorPlan()[1][3].setWall(1, 3, 'S');
         getFloorPlan()[1][4].setWall(1, 4, 'E');
         getFloorPlan()[1][4].setWall(1, 4, 'S');
         getFloorPlan()[2][0].setWall(2, 0, 'E');
         getFloorPlan()[2][0].setWall(2, 0, 'W');
         getFloorPlan()[2][1].setWall(2, 1, 'N');
         getFloorPlan()[2][1].setWall(2, 1, 'S');
         getFloorPlan()[2][1].setWall(2, 1, 'W');
         getFloorPlan()[2][2].setWall(2, 2, 'S');
         getFloorPlan()[2][3].setWall(2, 3, 'N');
         getFloorPlan()[2][3].setWall(2, 3, 'E');
         getFloorPlan()[2][3].setWall(2, 3, 'S');
         getFloorPlan()[2][4].setWall(2, 4, 'N');
         getFloorPlan()[2][4].setWall(2, 4, 'E');
         getFloorPlan()[2][4].setWall(2, 4, 'W');
         getFloorPlan()[3][0].setWall(3, 0, 'W');
         getFloorPlan()[3][0].setWall(3, 0, 'S');
         getFloorPlan()[3][1].setWall(3, 1, 'N');
         getFloorPlan()[3][1].setWall(3, 1, 'S');
         getFloorPlan()[3][2].setWall(3, 2, 'N');
         getFloorPlan()[3][2].setWall(3, 2, 'S');
         getFloorPlan()[3][3].setWall(3, 3, 'N');
         getFloorPlan()[3][3].setWall(3, 3, 'S');
         getFloorPlan()[3][4].setWall(3, 4, 'E');
         getFloorPlan()[3][4].setWall(3, 4, 'S');
      }
   }