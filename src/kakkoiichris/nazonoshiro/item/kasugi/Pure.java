//Christian Alexander, 9/13/2011
package kakkoiichris.nazonoshiro.item.kasugi;

import kakkoiichris.nazonoshiro.fighter.Enemy;
import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Pure extends Kasugi {
    public Pure() {
        super("Pure", 0, 0, true);
        
        timer = 99;
    }
    
    @java.lang.Override
    public void affect(Fighter fighter) {
        var pronoun = (fighter instanceof Enemy) ? "They" : "You";
    
        System.out.printf("%s used an antidote!%n%n", pronoun);
    }
}