//Christian Alexander, 9/13/2011
package kakkoiichris.nazonoshiro.item.kusuri;

import kakkoiichris.kotoba.Console;
import kakkoiichris.nazonoshiro.fighter.Enemy;
import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Burn extends Kusuri {
    public Burn() {
        super("Burn", "", 3, -1, true);
    }
    
    @java.lang.Override
    public boolean use(Console console, Fighter fighter) {
        return true;
    }
    
    @java.lang.Override
    public void affect(Console console, Fighter fighter) {
        var pronoun = fighter.getPossessive();
        
        if (timer != 0) {
            console.writeLine("%s shield's been degraded!%n", pronoun);
            
            fighter.setSpeed(fighter.getSpeed() - value);
        }
        else {
            console.writeLine("%s poison's been cured!%n", pronoun);
            
            setTimer(0);
        }
    }
}