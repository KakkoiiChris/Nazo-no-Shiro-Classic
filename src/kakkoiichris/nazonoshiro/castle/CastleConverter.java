// Christian Alexander, 2/18/2023
package kakkoiichris.nazonoshiro.castle;

import kakkoiichris.nazonoshiro.castle.room.Room;
import kakkoiichris.nazonoshiro.castle.room.RoomConverter;
import kakkoiichris.nazonoshiro.json.JsonConverter;
import kakkoiichris.nazonoshiro.json.parser.Object;

public class CastleConverter implements JsonConverter<Castle> {
    @Override
    public Castle load(Object object) {
        var name = object.get("name").asString().orElseThrow();
        
        var size = object.get("size").asArray().orElseThrow();
        
        var floors = size.get(0).asNumber().orElseThrow().intValue();
        var rows = size.get(1).asNumber().orElseThrow().intValue();
        var columns = size.get(2).asNumber().orElseThrow().intValue();
        
        var rooms = new Room[floors][rows][columns];
        
        var roomList = object.get("rooms").asObjectArray().orElseThrow();
        
        var converter = new RoomConverter();
        
        for (var roomObject : roomList) {
            var room = converter.load(roomObject);
            
            rooms[room.getFloor()][room.getRow()][room.getColumn()] = room;
        }
        
        return new Castle(name, rooms);
    }
    
    @Override
    public Object save(Castle castle) {
        return null;
    }
}
