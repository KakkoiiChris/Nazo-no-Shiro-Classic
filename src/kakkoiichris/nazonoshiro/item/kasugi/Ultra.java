//Christian Alexander, 9/13/2011
package kakkoiichris.nazonoshiro.item.kasugi;

import kakkoiichris.nazonoshiro.fighter.Enemy;
import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Ultra extends Kasugi {
    public Ultra() {
        super("Ultra", 3, 3, true);
    }
    
    @java.lang.Override
    public void affect(Fighter fighter) {
        if (timer != 0) {
            var pronoun = (fighter instanceof Enemy) ? "They" : "You";
    
            System.out.printf("%s feel reinvigorated!%n%n", pronoun);
            
            fighter.setHealth(-3);
        }
        else {
            System.out.println("Ultra has worn off.");
        }
        
        timer--;
    }
}