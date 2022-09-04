//Christian Alexander, 6/15/11, Pd. 6
package kakkoiichris.nazonoshiro.item;

import kakkoiichris.nazonoshiro.fighter.Fighter;

public class HealthPack extends Item {
    public HealthPack(String name, int value) {
        super(name, value);
    }
    
    @Override
    public void pickUp(Fighter self) {
    }
    
    @Override
    public void use(Fighter self) {
        System.out.printf("HP increased by %d points.%n%n", value);
        
        self.heal(value);
    }
    
    @Override
    public String toString() {
        return "%d point recovery.".formatted(value);
    }
}