//Christian Alexander, 9/13/2011
package kakkoiichris.nazonoshiro.item.kasugi;

import kakkoiichris.nazonoshiro.Console;
import kakkoiichris.nazonoshiro.fighter.Enemy;
import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Blind extends Kasugi {
    public Blind() {
        super("Blind", 3, 3, false);
    }
    
    @java.lang.Override
    public void affect(Fighter fighter) {
        if (timer != 0) {
            var pronoun = (fighter instanceof Enemy) ? "They" : "You";
            
            Console.writeLine("%s've been blinded!%n", pronoun);
            
            fighter.setSpeed(fighter.getSpeed() - 3);
        }
        else {
            Console.writeLine("Blind has worn off.\n");
            
            fighter.setSpeed(fighter.getSpeed() + 3);
        }
        
        timer--;
    }
}