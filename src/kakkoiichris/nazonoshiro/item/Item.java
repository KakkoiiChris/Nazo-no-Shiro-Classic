//Christian Alexander, 6/14/11, Pd. 6
package kakkoiichris.nazonoshiro.item;

import kakkoiichris.nazonoshiro.fighter.Fighter;

public abstract class Item {
    protected String name;
    protected int value;
    
    public Item(String name, int value) {
        this.name = name;
        this.value = value;
    }
    
    public String getName() {
        return name.toLowerCase();
    }
    
    public int getValue() {
        return value;
    }
    
    public abstract void pickUp(Fighter self);
    
    public abstract void use(Fighter self);
    
    @Override
    public abstract String toString();
}