//Christian Alexander, 9/13/2011
package kakkoiichris.nazonoshiro.item.kasugi;

import kakkoiichris.nazonoshiro.fighter.Enemy;
import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Corrupt extends Kasugi {
    public Corrupt() {
        super("Corrupt", 1, 99, false);
    }
    
    @java.lang.Override
    public void affect(Fighter fighter) {
        var pronoun = (fighter instanceof Enemy) ? "They" : "You";
        
        System.out.printf("%s've been poisoned!%n%n", pronoun);
        
        fighter.setHealth(1);
    }
}