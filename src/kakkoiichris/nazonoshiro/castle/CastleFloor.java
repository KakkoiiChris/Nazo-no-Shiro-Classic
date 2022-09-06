//Christian Alexander, 10/06/2011, Period 8
package kakkoiichris.nazonoshiro.castle;

import kakkoiichris.nazonoshiro.castle.room.Room;

public abstract class CastleFloor {
    protected final String name;
    protected final int rows, columns;
    
    protected Room[][] rooms;
    
    public CastleFloor(String name, int rows, int columns) {
        this.name = name;
        this.rows = rows;
        this.columns = columns;
        
        rooms = new Room[rows][columns];
    }
    
    public String getName() {
        return name;
    }
    
    public int getColumns() {
        return rows;
    }
    
    public int getRows() {
        return columns;
    }
    
    public Room[][] getRooms() {
        return rooms;
    }
    
    public void setRooms(Room[][] rooms) {
        this.rooms = rooms;
    }
    
    public Room getRoom(int r, int c) {
        return rooms[r][c];
    }
    
    public void setRoom(int r, int c, Room room) {
        rooms[r][c] = room;
    }
    
    public abstract void setUpNew();
    
    public abstract void setUpLoad();
}