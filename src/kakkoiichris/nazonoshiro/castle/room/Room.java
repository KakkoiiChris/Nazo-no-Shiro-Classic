//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.room;

import kakkoiichris.nazonoshiro.ResetGroup;
import kakkoiichris.nazonoshiro.ResetValue;
import kakkoiichris.nazonoshiro.Resettable;
import kakkoiichris.nazonoshiro.castle.Direction;
import kakkoiichris.nazonoshiro.castle.Wall;
import kakkoiichris.nazonoshiro.fighter.Self;
import kakkoiichris.nazonoshiro.item.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room implements Resettable {
    private final String name;
    
    private final int lock;
    private final int key;
    
    private final Map<Direction, Wall> walls = new HashMap<>();
    
    private ResetValue<Boolean> visited = new ResetValue<>(false);
    
    private ResetValue<Boolean> locked;
    
    protected final ResetGroup resetGroup;
    
    public Room(String name, int key, int lock, boolean locked) {
        this.name = name;
        this.key = key;
        this.lock = lock;
        this.locked = new ResetValue<>(locked);
        
        resetGroup = ResetGroup.of(visited, this.locked);
    }
    
    public String getName() {
        return name;
    }
    
    public int getKey() {
        return key;
    }
    
    public int getLock() {
        return lock;
    }
    
    public Wall getWall(Direction direction) {
        return walls.get(direction);
    }
    
    public void setWall(Direction direction, Wall wall) {
        resetGroup.add(wall);
        
        walls.put(direction, wall);
    }
    
    public boolean hasWall(Direction direction) {
        return walls.containsKey(direction);
    }
    
    public Map<Direction, Wall> getWalls() {
        return walls;
    }
    
    public int size() {
        return walls.size();
    }
    
    public boolean isVisited() {
        return visited.get();
    }
    
    public void setVisited() {
        visited.set(true);
    }
    
    public boolean isLocked() {
        return locked.get();
    }
    
    public void unlock() {
        this.locked.set(false);
    }
    
    @Override
    public void storeState() {
        resetGroup.storeState();
    }
    
    @Override
    public void resetState() {
        resetGroup.resetState();
    }
    
    public void look(Direction direction, Self self) {
        if (walls.isEmpty()) {
            System.out.println("There's nothing there.");
            
            return;
        }
        
        if (hasWall(direction)) {
            var wall = getWall(direction);
            
            wall.getStorage().open(self);
            
            return;
        }
        
        System.out.println("Just a door. Nothing else.");
    }
    
    public void distributeItems(List<Item> items) {
        for (var wall : walls.values()) {
            if (items.isEmpty()) {
                return;
            }
            
            wall.getStorage().add(items.remove(0));
        }
    }
    
    public String toString() {
        return name;
    }
}