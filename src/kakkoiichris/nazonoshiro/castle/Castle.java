package kakkoiichris.nazonoshiro.castle;

import kakkoiichris.nazonoshiro.Resettable;
import kakkoiichris.nazonoshiro.Resources;
import kakkoiichris.nazonoshiro.castle.room.PuzzleRoom;
import kakkoiichris.nazonoshiro.castle.room.Room;
import kakkoiichris.nazonoshiro.item.Item;

import java.util.ArrayList;
import java.util.List;

// Christian Alexander, 9/8/2022
public class Castle implements Resettable {
    protected String name;
    
    protected Room[][][] rooms;
    
    public Castle(String fileName) {
        var lines = Resources.getLines(fileName);
        
        for (var line : lines) {
            var tokens = new ArrayList<>(List.of(line.split(",")));
            
            var header = tokens.remove(0);
            
            switch (header) {
                case "C" -> {
                    var floors = Integer.parseInt(tokens.get(0));
                    var rows = Integer.parseInt(tokens.get(1));
                    var columns = Integer.parseInt(tokens.get(2));
                    
                    name = tokens.get(3);
                    
                    rooms = new Room[floors][rows][columns];
                }
                
                case "R" -> {
                    var floor = Integer.parseInt(tokens.get(0));
                    var row = Integer.parseInt(tokens.get(1));
                    var column = Integer.parseInt(tokens.get(2));
                    var name = tokens.get(3);
                    var key = Integer.parseInt(tokens.get(4));
                    var lock = Integer.parseInt(tokens.get(5));
                    var locked = Boolean.parseBoolean(tokens.get(6));
                    
                    assert rooms != null;
                    
                    rooms[floor][row][column] = new PuzzleRoom(name, key, lock, locked);
                }
                
                case "W" -> {
                    var floor = Integer.parseInt(tokens.get(0));
                    var row = Integer.parseInt(tokens.get(1));
                    var column = Integer.parseInt(tokens.get(2));
                    
                    assert rooms != null;
                    
                    var room = rooms[floor][row][column];
                    
                    for (var i = 3; i < tokens.size(); i++) {
                        var token = Integer.parseInt(tokens.get(i));
                        
                        var direction = Direction.values()[token];
                        
                        room.setWall(direction, new Wall(direction));
                    }
                }
            }
        }
    }
    
    public Room get(int floor, int row, int column) {
        return rooms[floor][row][column];
    }
    
    public int getFloors() {
        return rooms.length;
    }
    
    public int getRows() {
        return rooms[0].length;
    }
    
    public int getColumns() {
        return rooms[0][0].length;
    }
    
    public void distributeItems(List<Item> items) {
        for (var floor : rooms) {
            for (var row : floor) {
                for (var room : row) {
                    room.distributeItems(items);
                }
            }
        }
    }
    
    @Override
    public void storeState() {
        for (var floor : rooms) {
            for (var row : floor) {
                for (var room : row) {
                    room.storeState();
                }
            }
        }
    }
    
    @Override
    public void resetState() {
        for (var floor : rooms) {
            for (var row : floor) {
                for (var room : row) {
                    room.resetState();
                }
            }
        }
    }
}
