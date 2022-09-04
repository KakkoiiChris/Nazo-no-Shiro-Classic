//Christian Alexander, 9/27/2011
package kakkoiichris.nazonoshiro.item.kasugi;

import kakkoiichris.nazonoshiro.fighter.Enemy;
import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Sub extends Kasugi {
    public Sub() {
        super("Sub", 3, 3, false);
    }
    
    @java.lang.Override
    public void affect(Fighter fighter) {
        if (timer != 0) {
            var pronoun = (fighter instanceof Enemy) ? "Their" : "Your";
            
            System.out.printf("%s power's been dulled!%n%n", pronoun);
            
            fighter.setAttack(fighter.getAttack() - 3);
        }
        else {
            System.out.println("Sub has worn off.");
        }
        
        timer--;
    }
}