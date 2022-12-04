//Christian Alexander, 6/22/11, Period 6
package kakkoiichris.nazonoshiro.castle;

import kakkoiichris.nazonoshiro.Resettable;
import kakkoiichris.nazonoshiro.Util;
import kakkoiichris.nazonoshiro.castle.storage.*;

public class Wall implements Resettable {
    private final Direction direction;
    
    private final Storage storage;
    
    public Wall(Direction direction) {
        this.direction = direction;
        
        var number = Util.random.nextInt(5);
        
        storage = switch (number) {
            case 0 -> new Armoir();
            
            case 1 -> new Crate();
            
            case 2 -> new Dresser();
            
            case 3 -> new Desk();
            
            case 4 -> new JewelryBox();
            
            default -> throw new IllegalStateException("Unexpected value: " + number);
        };
    }
    
    public Wall(Direction direction, Storage storage) {
        this.direction = direction;
        this.storage = storage;
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    public Storage getStorage() {
        return storage;
    }
    
    @Override
    public void storeState() {
        storage.storeState();
    }
    
    @Override
    public void resetState() {
        storage.resetState();
    }
}