//Christian Alexander, 9/13/2011
package kakkoiichris.nazonoshiro.item.kasugi;

import kakkoiichris.nazonoshiro.fighter.Enemy;
import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Velocity extends Kasugi {
    public Velocity() {
        super("Velocity", 3, 3, true);
    }
    
    @java.lang.Override
    public void affect(Fighter fighter) {
        if (timer != 0) {
            var pronoun = (fighter instanceof Enemy) ? "Their" : "Your";
    
            System.out.printf("%s reactions are much quicker!%n%n", pronoun);
            
            fighter.setSpeed(fighter.getSpeed() + 3);
        }
        else {
            System.out.println("Velocity has worn off.");
        }
        
        timer--;
    }
}