package kakkoiichris.nazonoshiro.item.weapon;//Christian Alexander, 5/12/11, Pd. 6

import kakkoiichris.nazonoshiro.fighter.Fighter;
import kakkoiichris.nazonoshiro.item.Item;

public abstract class Weapon extends Item {
    public Weapon(String name, int value) {
        super(name, value);
    }
    
    public abstract void show();
    
    @Override
    public void pickUp(Fighter self) {
        self.setAttack(1);
    }
    
    @Override
    public void use(Fighter self) {
        System.out.println("You cannot use this.");
    }
    
    @Override
    public String toString() {
        return name;
    }
}