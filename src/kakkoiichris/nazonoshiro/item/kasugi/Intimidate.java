//Christian Alexander, 9/27/2011
package kakkoiichris.nazonoshiro.item.kasugi;

import kakkoiichris.nazonoshiro.Console;
import kakkoiichris.nazonoshiro.fighter.Enemy;
import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Intimidate extends Kasugi {
    public Intimidate() {
        super("Intimidate", "", 3, 3, false);
    }
    
    @java.lang.Override
    public boolean use(Fighter self) {
        return true;
    }
    
    @java.lang.Override
    public void affect(Fighter fighter) {
        var pronoun = (fighter instanceof Enemy) ? "Their" : "Your";
        
        if (timer != 0) {
            Console.writeLine("%s've been intimidated!%n", pronoun);
            
            fighter.setDefense(fighter.getDefense() - 3);
            
            timer--;
        }
        else {
            Console.writeLine("Intimidate has worn off.");
        }
    }
}