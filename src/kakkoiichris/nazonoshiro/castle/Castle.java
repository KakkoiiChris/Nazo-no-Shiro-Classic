package kakkoiichris.nazonoshiro.castle;

import kakkoiichris.nazonoshiro.Resettable;
import kakkoiichris.nazonoshiro.Resources;
import kakkoiichris.nazonoshiro.castle.room.EnemyRoom;
import kakkoiichris.nazonoshiro.castle.room.Room;
import kakkoiichris.nazonoshiro.fighter.Ninja;
import kakkoiichris.nazonoshiro.item.Item;

import java.util.ArrayList;
import java.util.List;

// Christian Alexander, 9/8/2022
public class Castle implements Resettable {
    protected final String name;
    
    protected final Room[][][] rooms;
    
    public Castle(String fileName) {
        var lines = Resources.getLines(fileName);
        
        var header = parseCSV(lines.remove(0));
        
        var floors = Integer.parseInt(header.get(0));
        var rows = Integer.parseInt(header.get(1));
        var columns = Integer.parseInt(header.get(2));
        
        name = header.get(3);
        
        rooms = new Room[floors][rows][columns];
        
        for (var line : lines) {
            var tokens = parseCSV(line);
            
            var label = tokens.remove(0);
            
            switch (label) {
                case "R" -> {
                    var floor = Integer.parseInt(tokens.get(0));
                    var row = Integer.parseInt(tokens.get(1));
                    var column = Integer.parseInt(tokens.get(2));
                    var name = tokens.get(3);
                    var key = Integer.parseInt(tokens.get(4));
                    var lock = Integer.parseInt(tokens.get(5));
                    var locked = Boolean.parseBoolean(tokens.get(6));
                    
                    rooms[floor][row][column] = new EnemyRoom(name, key, lock, locked, new Ninja());
                }
                
                case "W" -> {
                    var floor = Integer.parseInt(tokens.get(0));
                    var row = Integer.parseInt(tokens.get(1));
                    var column = Integer.parseInt(tokens.get(2));
                    
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
    
    private List<String> parseCSV(String line) {
        return new ArrayList<>(List.of(line.split(",")));
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
