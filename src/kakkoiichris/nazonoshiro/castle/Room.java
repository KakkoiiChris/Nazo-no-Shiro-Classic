//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.castle;

import kakkoiichris.nazonoshiro.Resettable;
import kakkoiichris.nazonoshiro.fighter.Self;
import kakkoiichris.nazonoshiro.item.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room implements Resettable {
    private final String name;
    private final int puzzle;
    private int key;
    private final int lock;
    private int keyLast;
    private int lockLast;
    private int foe;
    private final Map<Direction, Wall> walls = new HashMap<>();
    private final boolean hasStairs;
    
    private boolean visited = false, visitedLast = false;
    
    public Room(String name, int puzzle, int key, int lock, int foe, boolean hasStairs) {
        this.name = name;
        this.puzzle = puzzle;
        this.key = key;
        this.lock = lock;
        this.foe = foe;
        this.hasStairs = hasStairs;
    }
    
    public Room(String name, int puzzle, int key, int lock, boolean hasStairs) {
        this.name = name;
        this.puzzle = puzzle;
        this.key = key;
        this.lock = lock;
        this.hasStairs = hasStairs;
    }
    
    public boolean isVisited() {
        return visited;
    }
    
    public void setVisited() {
        visited = true;
    }
    
    @Override
    public void storeState() {
        keyLast = key;
        
        visitedLast = visited;
        
        walls.values().forEach(Wall::storeState);
    }
    
    @Override
    public void resetState() {
        key = keyLast;
        
        visited = visitedLast;
        
        walls.values().forEach(Wall::resetState);
    }
    
    public int getFoe() {
        return foe;
    }
    
    public int size() {
        return walls.size();
    }
    
    public Wall getWall(Direction direction) {
        return walls.get(direction);
    }
    
    public void setWall(Direction direction, Wall wall) {
        walls.put(direction, wall);
    }
    
    public boolean hasWall(Direction direction) {
        return walls.containsKey(direction);
    }
    
    public Map<Direction, Wall> getWalls() {
        return walls;
    }
    
    public String getName() {
        return name;
    }
    
    public int getPuzzle() {
        return puzzle;
    }
    
    public int getKey() {
        return key;
    }
    
    public void setKey(int a) {
        key = a;
    }
    
    public int getLock() {
        return lock;
    }
    
    public String toString() {
        return name;
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
}