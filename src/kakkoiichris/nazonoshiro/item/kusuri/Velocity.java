//Christian Alexander, 9/13/2011
package kakkoiichris.nazonoshiro.item.kusuri;

import kakkoiichris.kotoba.Console;
import kakkoiichris.nazonoshiro.fighter.Enemy;
import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Velocity extends Kusuri {
    public Velocity() {
        super("Velocity", "", 3, 3, true);
    }
    
    @java.lang.Override
    public boolean use(Console console, Fighter self) {
        return true;
    }
    
    @java.lang.Override
    public void affect(Console console, Fighter fighter) {
        if (timer != 0) {
            var pronoun = (fighter instanceof Enemy) ? "Their" : "Your";
            
            console.writeLine("%s reactions are much quicker!%n", pronoun);
            
            fighter.setSpeed(fighter.getSpeed() + 3);
        }
        else {
            console.writeLine("Velocity has worn off.");
        }
        
        timer--;
    }
}