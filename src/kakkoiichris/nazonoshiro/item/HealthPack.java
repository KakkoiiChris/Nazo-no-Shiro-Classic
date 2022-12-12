//Christian Alexander, 6/15/11, Pd. 6
package kakkoiichris.nazonoshiro.item;

import kakkoiichris.nazonoshiro.fighter.Fighter;
import kakkoiichris.nazonoshiro.fighter.Self;

public enum HealthPack implements Item {
    HERB(3, "Herb", "A tiny leaf."),
    BUSHEL(5, "Bushel", "Many tiny leaves.");
    
    private final int value;
    private final String name;
    private final String description;
    
    HealthPack(int value, String name, String description) {
        this.value = value;
        this.name = name;
        this.description = description;
    }
    
    @Override
    public int getValue() {
        return value;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String getDescription() {
        return description;
    }
    
    @Override
    public boolean pickUp(Self self) {
        return true;
    }
    
    @Override
    public boolean use(Fighter self) {
        return false;
    }
}
