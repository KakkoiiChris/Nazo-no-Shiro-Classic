package kakkoiichris.nazonoshiro.castle;//Christian Alexander, 6/22/11, Period 6

import kakkoiichris.nazonoshiro.castle.storage.*;

public class Wall {
    private final int x;
    private final int y;
    private final Storage[] storage = new Storage[1];
    private final char Direction;
    
    public Wall(int x, int y, char d) {
        this.x = x;
        this.y = y;
        Direction = d;
        setStorage(this.x, this.y);
    }
    
    public Wall(int x, int y, char d, String n) {
        this(x, y, d);
        setStorage(this.x, this.y, n);
    }
    
    public Storage getStorage() {
        return storage[0];
    }
    
    public char getSide() {
        return Direction;
    }
    
    public static int randomize() {
        return (int) (Math.random() * 5) + 1;
    }
    
    public void setStorage(int r, int c) {
        var temp = randomize();
    
        switch (temp) {
            case 1 -> storage[0] = new Armoir(r, c);
            
            case 2 -> storage[0] = new Crate(r, c);
            
            case 3 -> storage[0] = new Dresser(r, c);
            
            case 4 -> storage[0] = new Desk(r, c);
            
            case 5 -> storage[0] = new JewelryBox(r, c);
        }
    }
    
    public void setStorage(int r, int c, String n) {
        switch (n) {
            case "Armoir" -> storage[0] = new Armoir(r, c);
            
            case "Crate" -> storage[0] = new Crate(r, c);
            
            case "Dresser" -> storage[0] = new Dresser(r, c);
            
            case "Desk" -> storage[0] = new Desk(r, c);
            
            case "Jewelry Box" -> storage[0] = new JewelryBox(r, c);
        }
    }
}