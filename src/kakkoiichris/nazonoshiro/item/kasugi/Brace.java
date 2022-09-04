//Christian Alexander, 9/13/2011
package kakkoiichris.nazonoshiro.item.kasugi;

import kakkoiichris.nazonoshiro.fighter.Enemy;
import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Brace extends Kasugi {
    public Brace() {
        super("Brace", 3, 3, true);
    }
    
    @java.lang.Override
    public void affect(Fighter fighter) {
        if (timer != 0) {
            var pronoun = (fighter instanceof Enemy) ? "They" : "You";
    
            System.out.printf("%s've braced for impact!%n%n", pronoun);
            
            fighter.setDefense(fighter.getDefense() - 3);
        }
        else {
            System.out.println("Brace has worn off.\n");
        }
        
        timer--;
    }
}