//Christian Alexander, 10/06/2011, Period 8
package kakkoiichris.nazonoshiro.castle;

import kakkoiichris.nazonoshiro.castle.room.EnemyRoom;
import kakkoiichris.nazonoshiro.castle.room.PuzzleRoom;
import kakkoiichris.nazonoshiro.fighter.Daimyo;
import kakkoiichris.nazonoshiro.fighter.Ninja;
import kakkoiichris.nazonoshiro.fighter.Samurai;
import kakkoiichris.nazonoshiro.fighter.Shogun;

public class OriginalCastle extends CastleFloor {
    public OriginalCastle() {
        super("Original Castle", 4, 5);
    }
    
    public void setUpNew() {
        setUpCommon();
    }
    
    public void setUpLoad() {
        setUpCommon();
    }
    
    public void setUpCommon() {
        setRoom(0, 0, new PuzzleRoom("North: Annex C", 11, 10, true));
        setRoom(1, 0, new EnemyRoom("West: Annex A", 4, 3, true, new Ninja()));
        setRoom(2, 0, new PuzzleRoom("Secret Chamber A", 14, 13, true));
        setRoom(3, 0, new PuzzleRoom("Secret Chamber B", 15, 14, true));
        setRoom(0, 1, new PuzzleRoom("North: Annex A", 5, 4, true));
        setRoom(1, 1, new PuzzleRoom("West Wing", 3, 2, true));
        setRoom(2, 1, new PuzzleRoom("South: Annex A", 9, 8, true));
        setRoom(3, 1, new PuzzleRoom("Secret Chamber C", 16, 15, true));
        setRoom(0, 2, new PuzzleRoom("North Wing", 2, 1, true));
        setRoom(1, 2, new PuzzleRoom("Central Hall", 1, 0, false));
        setRoom(2, 2, new PuzzleRoom("South Wing", 7, 6, true));
        setRoom(3, 2, new PuzzleRoom("Secret Chamber D", 17, 16, true));
        setRoom(0, 3, new EnemyRoom("North: Annex B", 8, 7, true, new Samurai()));
        setRoom(1, 3, new PuzzleRoom("East Wing", 6, 5, true));
        setRoom(2, 3, new EnemyRoom("South: Annex B", 12, 11, true, new Shogun()));
        setRoom(3, 3, new PuzzleRoom("Secret Chamber E", 18, 17, true));
        setRoom(0, 4, new PuzzleRoom("Foyer", 13, 12, true));
        setRoom(1, 4, new PuzzleRoom("East: Annex A", 10, 9, true));
        setRoom(2, 4, new EnemyRoom("Secret Treasure Room", 20, 19, true, new Daimyo()));
        setRoom(3, 4, new PuzzleRoom("Secret Chamber F", 19, 18, true));
        
        getRoom(0, 0).setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getRoom(0, 0).setWall(Direction.WEST, new Wall(Direction.WEST));
        getRoom(0, 0).setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getRoom(0, 1).setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getRoom(0, 1).setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getRoom(0, 2).setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getRoom(0, 3).setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getRoom(0, 3).setWall(Direction.EAST, new Wall(Direction.EAST));
        getRoom(0, 3).setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getRoom(0, 4).setWall(Direction.EAST, new Wall(Direction.EAST));
        getRoom(0, 4).setWall(Direction.WEST, new Wall(Direction.WEST));
        getRoom(1, 0).setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getRoom(1, 0).setWall(Direction.WEST, new Wall(Direction.WEST));
        getRoom(1, 1).setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getRoom(1, 1).setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getRoom(1, 3).setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getRoom(1, 3).setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getRoom(1, 4).setWall(Direction.EAST, new Wall(Direction.EAST));
        getRoom(1, 4).setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getRoom(2, 0).setWall(Direction.EAST, new Wall(Direction.EAST));
        getRoom(2, 0).setWall(Direction.WEST, new Wall(Direction.WEST));
        getRoom(2, 1).setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getRoom(2, 1).setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getRoom(2, 1).setWall(Direction.WEST, new Wall(Direction.WEST));
        getRoom(2, 2).setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getRoom(2, 3).setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getRoom(2, 3).setWall(Direction.EAST, new Wall(Direction.EAST));
        getRoom(2, 3).setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getRoom(2, 4).setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getRoom(2, 4).setWall(Direction.EAST, new Wall(Direction.EAST));
        getRoom(2, 4).setWall(Direction.WEST, new Wall(Direction.WEST));
        getRoom(3, 0).setWall(Direction.WEST, new Wall(Direction.WEST));
        getRoom(3, 0).setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getRoom(3, 1).setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getRoom(3, 1).setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getRoom(3, 2).setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getRoom(3, 2).setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getRoom(3, 3).setWall(Direction.NORTH, new Wall(Direction.NORTH));
        getRoom(3, 3).setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
        getRoom(3, 4).setWall(Direction.EAST, new Wall(Direction.EAST));
        getRoom(3, 4).setWall(Direction.SOUTH, new Wall(Direction.SOUTH));
    }
}