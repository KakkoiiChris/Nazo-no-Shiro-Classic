package kakkoiichris.nazonoshiro.castle;

import kakkoiichris.nazonoshiro.Resettable;
import kakkoiichris.nazonoshiro.Resources;
import kakkoiichris.nazonoshiro.castle.room.Room;
import kakkoiichris.nazonoshiro.item.Item;
import kakkoiichris.nazonoshiro.json.Json;
import kakkoiichris.nazonoshiro.json.JsonConverter;
import kakkoiichris.nazonoshiro.json.parser.Object;

import java.util.ArrayList;
import java.util.List;

// Christian Alexander, 9/8/2022
public class Castle implements Resettable {
    protected final String name;
    
    protected final Room[][][] rooms;
    
    public static Castle load(String fileName) {
        var string = Resources.tryGetString(fileName);
        
        if (string.isEmpty()) {
            throw new RuntimeException();
        }
        
        var source = string.get();
        
        var json = Json.of(source, new CastleConverter());
        
        return json.load();
    }
    
    public Castle(String name, Room[][][] rooms) {
        this.name = name;
        this.rooms = rooms;
        
        //rooms[0][1][1] = new EnemyRoom("FIGHT!!!", 0, 0, false, new Ninja());
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
