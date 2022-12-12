//Christian Alexander, 9/13/2011
package kakkoiichris.nazonoshiro.item.kasugi;

import kakkoiichris.nazonoshiro.Console;
import kakkoiichris.nazonoshiro.fighter.Enemy;
import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Velocity extends Kasugi {
    public Velocity() {
        super("Velocity", "", 3, 3, true);
    }
    
    @java.lang.Override
    public boolean use(Fighter self) {
        return true;
    }
    
    @java.lang.Override
    public void affect(Fighter fighter) {
        if (timer != 0) {
            var pronoun = (fighter instanceof Enemy) ? "Their" : "Your";
            
            Console.writeLine("%s reactions are much quicker!%n", pronoun);
            
            fighter.setSpeed(fighter.getSpeed() + 3);
        }
        else {
            Console.writeLine("Velocity has worn off.");
        }
        
        timer--;
    }
}