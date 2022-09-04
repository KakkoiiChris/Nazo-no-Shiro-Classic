//Christian Alexander, 6/14/11, Pd. 6
package kakkoiichris.nazonoshiro.item;

import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Coin extends Item {
    public Coin() {
        super("Coin", 5);
    }
    
    @Override
    public void pickUp(Fighter self) {
    }
    
    @Override
    public void use(Fighter self) {
        System.out.println("You cannot use this.");
    }
    
    @Override
    public String toString() {
        return "A %d Yen Coin.".formatted(value);
    }
}