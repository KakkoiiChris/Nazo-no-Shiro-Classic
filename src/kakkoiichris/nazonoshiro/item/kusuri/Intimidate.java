//Christian Alexander, 9/27/2011
package kakkoiichris.nazonoshiro.item.kusuri;

import kakkoiichris.kotoba.Console;
import kakkoiichris.nazonoshiro.fighter.Enemy;
import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Intimidate extends Kusuri {
    public Intimidate() {
        super("Intimidate", "", 3, 3, false);
    }
    
    @java.lang.Override
    public boolean use(Console console, Fighter self) {
        return true;
    }
    
    @java.lang.Override
    public void affect(Console console,Fighter fighter) {
        var pronoun = (fighter instanceof Enemy) ? "Their" : "Your";
        
        if (timer != 0) {
            console.writeLine("%s've been intimidated!%n", pronoun);
            
            fighter.setDefense(fighter.getDefense() - 3);
            
            timer--;
        }
        else {
            console.writeLine("Intimidate has worn off.");
        }
    }
}