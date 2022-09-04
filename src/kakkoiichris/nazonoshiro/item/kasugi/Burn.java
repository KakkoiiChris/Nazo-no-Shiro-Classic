//Christian Alexander, 9/13/2011
package kakkoiichris.nazonoshiro.item.kasugi;

import kakkoiichris.nazonoshiro.fighter.Enemy;
import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Burn extends Kasugi {
    public Burn() {
        super("Burn", 3, -1, true);
    }
    
    @java.lang.Override
    public void affect(Fighter fighter) {
        var pronoun = (fighter instanceof Enemy) ? "Their" : "Your";
        
        if (timer != 0) {
            System.out.printf("%s shield's been degraded!%n%n", pronoun);
            
            fighter.setSpeed(fighter.getSpeed() - magnitude);
        }
        else {
            System.out.printf("%s poison's been cured!%n%n", pronoun);
            
            setTimer(0);
        }
    }
}