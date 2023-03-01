// Christian Alexander, 2/18/2023
package kakkoiichris.nazonoshiro.castle.room;

import kakkoiichris.nazonoshiro.castle.Direction;
import kakkoiichris.nazonoshiro.castle.Wall;
import kakkoiichris.nazonoshiro.fighter.Ninja;
import kakkoiichris.nazonoshiro.json.JsonConverter;
import kakkoiichris.nazonoshiro.json.parser.Object;

public class RoomConverter implements JsonConverter<Room> {
    @Override
    public Room load(Object object) {
        var name = object.get("name").asString().orElseThrow();
        
        var position = object.get("position").asArray().orElseThrow();
        
        var floor = position.get(0).asNumber().orElseThrow().intValue();
        var row = position.get(1).asNumber().orElseThrow().intValue();
        var column = position.get(2).asNumber().orElseThrow().intValue();
        
        var key = object.get("key").asNumber().orElseThrow().intValue();
        
        var lock = object.get("lock").asNumber().orElseThrow().intValue();
        
        var locked = object.get("locked").asBoolean().orElseThrow();
        
        var room = new EnemyRoom(name, floor, row, column, key, lock, locked, new Ninja());
        
        var wallList = object.get("walls").asArray().orElseThrow();
        
        for (var wall : wallList.elements()) {
            var ordinal = wall.asNumber().orElseThrow().intValue();
            
            var direction = Direction.values()[ordinal];
            
            room.setWall(direction, new Wall(direction));
        }
        
        if (object.members().containsKey("exit")) {
            var directionOrdinal = object.get("exit").asNumber().orElseThrow().intValue();
            
            room.setExit(Direction.values()[directionOrdinal]);
        }
        
        return room;
    }
    
    @Override
    public Object save(Room room) {
        return null;
    }
}
