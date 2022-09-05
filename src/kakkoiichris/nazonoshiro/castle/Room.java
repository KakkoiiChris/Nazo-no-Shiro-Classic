//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.castle;

import kakkoiichris.nazonoshiro.fighter.Self;
import kakkoiichris.nazonoshiro.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private final String name;
    private final int puzzle;
    private int key;
    private final int lock;
    private int keyLast;
    private int lockLast;
    private int foe;
    private List<Wall> walls = new ArrayList<>();
    protected List<Item> items = new ArrayList<>();
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
    
    public void storeState() {
        keyLast = key;
        
        visitedLast = visited;
        
        for (var i = 0; i < getWalls().size(); i++) {
            getWalls().get(i).getStorage().storeState();
        }
    }
    
    public void resetState() {
        key = keyLast;
        
        for (var i = 0; i < getWalls().size(); i++) {
            getWalls().get(i).getStorage().resetState();
        }
    }
    
    public int getFoe() {
        return foe;
    }
    
    public void setRoom() {
        if (getWalls().size() > 0) {
            var d = items.size() / getWalls().size();
            
            for (var i = 0; i < getWalls().size(); i++) {
                for (var j = 0; j < d; j++) {
                    var random = (int) (Math.random() * items.size());
                    
                    getWalls().get(i).getStorage().add(items.remove(random));
                }
            }
        }
    }
    
    public void findWall(int n, int s, int e, int w) {
        for (var k = 0; k < getWalls().size(); k++) {
            switch (getWalls().get(k).getSide()) {
                case 'n' -> n++;
                
                case 's' -> s++;
                
                case 'e' -> e++;
                
                case 'w' -> w++;
            }
        }
    }
    
    public void addToRoom(Item item) {
        items.add(item);
    }
    
    public int getSize() {
        return getWalls().size();
    }
    
    public Wall getWall(int i) {
        return getWalls().get(i);
    }
    
    public void setWall(int r, int c, char w) {
        getWalls().add(new Wall(r, c, w));
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
    
    public void look(Self self, char dir) {
        if (getWalls().size() > 0) {
            for (var i = 0; i < getWalls().size(); i++) {
                if (getWalls().get(i).getSide() == dir) {
                    getWalls().get(i).getStorage().open(self);
                }
                else {
                    System.out.println("Just a door. Nothing else.");
                }
            }
        }
        else {
            System.out.println("There's nothing there.");
        }
    }
    
    public void redistribute() {
        for (var i = 0; i < this.getSize(); i++) {
            for (var j = 0; j < items.size() / this.getSize(); j++) {
                this.getWall(i).getStorage().add(items.get((int) (Math.random() * items.size())));
                
                items.remove(j--);
            }
        }
    }
    
    public List<Wall> getWalls() {
        return walls;
    }
    
    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }
}