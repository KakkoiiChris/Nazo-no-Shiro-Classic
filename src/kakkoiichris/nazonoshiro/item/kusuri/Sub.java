//Christian Alexander, 9/27/2011
package kakkoiichris.nazonoshiro.item.kusuri;

import kakkoiichris.kotoba.Console;
import kakkoiichris.nazonoshiro.fighter.Enemy;
import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Sub extends Kusuri {
    public Sub() {
        super("Sub", "", 3, 3, false);
    }
    
    @java.lang.Override
    public boolean use(Console console, Fighter fighter) {
        return true;
    }
    
    @java.lang.Override
    public void affect(Console console, Fighter fighter) {
        if (timer != 0) {
            var pronoun = fighter.getPossessive();
            
            console.writeLine("%s power's been dulled!%n", pronoun);
            
            fighter.setAttack(fighter.getAttack() - 3);
        }
        else {
            console.writeLine("Sub has worn off.");
        }
        
        timer--;
    }
}