//Christian Alexander, 9/13/2011
package kakkoiichris.nazonoshiro.item.kasugi;

import kakkoiichris.nazonoshiro.Console;
import kakkoiichris.nazonoshiro.fighter.Enemy;
import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Burn extends Kasugi {
    public Burn() {
        super("Burn", "", 3, -1, true);
    }
    
    @java.lang.Override
    public boolean use(Fighter self) {
        return true;
    }
    
    @java.lang.Override
    public void affect(Fighter fighter) {
        var pronoun = (fighter instanceof Enemy) ? "Their" : "Your";
        
        if (timer != 0) {
            Console.writeLine("%s shield's been degraded!%n", pronoun);
            
            fighter.setSpeed(fighter.getSpeed() - value);
        }
        else {
            Console.writeLine("%s poison's been cured!%n", pronoun);
            
            setTimer(0);
        }
    }
}