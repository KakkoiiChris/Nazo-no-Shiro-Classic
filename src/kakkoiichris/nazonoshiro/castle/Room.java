package kakkoiichris.nazonoshiro.castle;//Christian Alexander, 5/12/11, Pd. 6

import kakkoiichris.nazonoshiro.fighter.Self;
import kakkoiichris.nazonoshiro.item.Item;

import java.util.ArrayList;

public class Room {
    private String name;
    private int puzzle, key, lock, key1, lock1, i, foe;
    private ArrayList<Wall> walls = new ArrayList();
    protected ArrayList<Item> items = new ArrayList();
    private String choice = new String();
    private final boolean hasStairs;
    
    
    public Room(String na, int p, int k, int l, int f, boolean s) {
        name = na;
        puzzle = p;
        key = k;
        lock = l;
        foe = f;
        hasStairs = s;
    }
    
    public Room(String na, int p, int k, int l, boolean s) {
        name = na;
        puzzle = p;
        key = k;
        lock = l;
        hasStairs = s;
    }
    
    public void storeState() {
        key1 = key;
        for (int i = 0; i < getWalls().size(); i++) {
            getWalls().get(i).getStorage().storeState();
        }
    }
    
    public void resetState() {
        key = key1;
        for (int i = 0; i < getWalls().size(); i++) {
            getWalls().get(i).getStorage().resetState();
        }
    }
    
    public int getFoe() {
        return foe;
    }
    
    public void setRoom() {
        if (getWalls().size() > 0) {
            int d = items.size() / getWalls().size();
            for (int i = 0; i < getWalls().size(); i++) {
                for (int j = 0; j < d; j++) {
                    int random = (int) (Math.random() * items.size());
                    getWalls().get(i).getStorage().add(items.get(random));
                    items.remove(random);
                }
            }
        }
    }
    
    public void findWall(int N, int S, int E, int W) {
        for (int k = 0; k < getWalls().size(); k++) {
            if (getWalls().get(k).getSide() == 'n')
                N++;
            else if (getWalls().get(k).getSide() == 's')
                S++;
            else if (getWalls().get(k).getSide() == 'e')
                E++;
            else if (getWalls().get(k).getSide() == 'w')
                W++;
        }
    }
    
    public void addToRoom(Item a) {
        items.add(a);
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
    
    public void look(Self self, ArrayList<Item> inventory, char dir) {
        if (getWalls().size() > 0) {
            for (i = 0; i < getWalls().size(); i++) {
                if (getWalls().get(i).getSide() == dir) {
                    getWalls().get(i).getStorage().open(self);
                }
                else
                    System.out.println("Just a door. Nothing else.");
            }
        }
        else
            System.out.println("There's nothing there.");
    }
    
    public void redistribute() {
        for (int i = 0; i < this.getSize(); i++) {
            for (int j = 0; j < items.size() / this.getSize(); j++) {
                this.getWall(i).getStorage().add(items.get((int) (Math.random() * items.size())));
                items.remove(j);
            }
        }
    }
    
    public ArrayList<Wall> getWalls() {
        return walls;
    }
    
    public void setWalls(ArrayList<Wall> walls) {
        this.walls = walls;
    }
}