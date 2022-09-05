//Christian Alexander, 10/06/2011, Period 8
package kakkoiichris.nazonoshiro.castle;

import kakkoiichris.nazonoshiro.castle.puzzle.Kurobune;
import kakkoiichris.nazonoshiro.castle.puzzle.Oboeru;
import kakkoiichris.nazonoshiro.castle.puzzle.Seihoukei;

public class OriginalCastle extends CastleFloor {
    public OriginalCastle() {
        super(4, 5, "Original Castle");
    }
    
    public void setUpNew() {
        for (var r = 0; r < 4; r++) {
            for (var c = 0; c < 5; c++) {
                getPuzzleType()[r][c] = (int) (Math.random() * 3);
                
                switch (getPuzzleType()[r][c]) {
                    case 0 -> getPuzzles()[r][c] = new Oboeru();
                    
                    case 1 -> getPuzzles()[r][c] = new Seihoukei();
                    
                    case 2 -> getPuzzles()[r][c] = new Kurobune();
                }
            }
        }
        
        setUpCommon();
    }
    
    public void setUpLoad() {
        setUpCommon();
    }
    
    public void setUpCommon() {
        getFloorPlan()[0][0] = new Room("North: Annex C", getPuzzleType()[0][0], 11, 10, 99, false);
        getFloorPlan()[1][0] = new Room("West: Annex A", 4, 4, 3, 0, false);
        getFloorPlan()[2][0] = new Room("Secret Chamber A", getPuzzleType()[2][0], 14, 13, 99, false);
        getFloorPlan()[3][0] = new Room("Secret Chamber B", getPuzzleType()[3][0], 15, 14, 99, false);
        getFloorPlan()[0][1] = new Room("North: Annex A", getPuzzleType()[0][1], 5, 4, 99, false);
        getFloorPlan()[1][1] = new Room("West Wing", getPuzzleType()[1][1], 3, 2, 99, false);
        getFloorPlan()[2][1] = new Room("South: Annex A", getPuzzleType()[2][1], 9, 8, 99, false);
        getFloorPlan()[3][1] = new Room("Secret Chamber C", getPuzzleType()[3][1], 16, 15, 99, false);
        getFloorPlan()[0][2] = new Room("North Wing", getPuzzleType()[0][2], 2, 1, 99, false);
        getFloorPlan()[1][2] = new Room("Central Hall", getPuzzleType()[1][2], 1, 0, 99, true);
        getFloorPlan()[2][2] = new Room("South Wing", getPuzzleType()[2][2], 7, 6, 99, false);
        getFloorPlan()[3][2] = new Room("Secret Chamber D", getPuzzleType()[3][2], 17, 16, 99, false);
        getFloorPlan()[0][3] = new Room("North: Annex B", 4, 8, 7, 1, false);
        getFloorPlan()[1][3] = new Room("East Wing", getPuzzleType()[1][3], 6, 5, 99, false);
        getFloorPlan()[2][3] = new Room("South: Annex B", 4, 12, 11, 2, false);
        getFloorPlan()[3][3] = new Room("Secret Chamber E", getPuzzleType()[3][3], 18, 17, 99, false);
        getFloorPlan()[0][4] = new Room("Foyer", getPuzzleType()[0][4], 13, 12, 99, false);
        getFloorPlan()[1][4] = new Room("East: Annex A", getPuzzleType()[1][4], 10, 9, 99, false);
        getFloorPlan()[2][4] = new Room("Secret Treasure Room", 4, 20, 19, 3, false);
        getFloorPlan()[3][4] = new Room("Secret Chamber F", getPuzzleType()[3][4], 19, 18, 99, false);
        
        getFloorPlan()[0][0].setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getFloorPlan()[0][0].setWall(Direction.WEST, new Wall(Direction.WEST));
        getFloorPlan()[0][0].setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getFloorPlan()[0][1].setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getFloorPlan()[0][1].setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getFloorPlan()[0][2].setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getFloorPlan()[0][3].setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getFloorPlan()[0][3].setWall(Direction.EAST, new Wall(Direction.EAST));
        getFloorPlan()[0][3].setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getFloorPlan()[0][4].setWall(Direction.EAST, new Wall(Direction.EAST));
        getFloorPlan()[0][4].setWall(Direction.WEST, new Wall(Direction.WEST));
        getFloorPlan()[1][0].setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getFloorPlan()[1][0].setWall(Direction.WEST, new Wall(Direction.WEST));
        getFloorPlan()[1][1].setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getFloorPlan()[1][1].setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getFloorPlan()[1][3].setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getFloorPlan()[1][3].setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getFloorPlan()[1][4].setWall(Direction.EAST, new Wall(Direction.EAST));
        getFloorPlan()[1][4].setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getFloorPlan()[2][0].setWall(Direction.EAST, new Wall(Direction.EAST));
        getFloorPlan()[2][0].setWall(Direction.WEST, new Wall(Direction.WEST));
        getFloorPlan()[2][1].setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getFloorPlan()[2][1].setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getFloorPlan()[2][1].setWall(Direction.WEST, new Wall(Direction.WEST));
        getFloorPlan()[2][2].setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getFloorPlan()[2][3].setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getFloorPlan()[2][3].setWall(Direction.EAST, new Wall(Direction.EAST));
        getFloorPlan()[2][3].setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getFloorPlan()[2][4].setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getFloorPlan()[2][4].setWall(Direction.EAST, new Wall(Direction.EAST));
        getFloorPlan()[2][4].setWall(Direction.WEST, new Wall(Direction.WEST));
        getFloorPlan()[3][0].setWall(Direction.WEST, new Wall(Direction.WEST));
        getFloorPlan()[3][0].setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getFloorPlan()[3][1].setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getFloorPlan()[3][1].setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getFloorPlan()[3][2].setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getFloorPlan()[3][2].setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getFloorPlan()[3][3].setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getFloorPlan()[3][3].setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getFloorPlan()[3][4].setWall(Direction.EAST, new Wall(Direction.EAST));
        getFloorPlan()[3][4].setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
    }
}