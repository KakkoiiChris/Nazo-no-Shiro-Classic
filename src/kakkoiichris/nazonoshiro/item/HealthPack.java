package kakkoiichris.nazonoshiro.item;//Christian Alexander, 6/15/11, Pd. 6

import kakkoiichris.nazonoshiro.fighter.Fighter;

public class HealthPack extends Item {
    public HealthPack(String name, int v) {
        super(name, v);
    }
    
    public void pickUp(Fighter self) {
    }
    
    public void use(Fighter self, int value) {
        System.out.println("HP increased by " + value + " points.");
        self.heal(value);
        System.out.println();
    }
    
    public String toString() {
        return (value) + " point recovery.";
    }
}