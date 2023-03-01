//Christian Alexander, 9/13/2011
package kakkoiichris.nazonoshiro.item.kusuri;

import kakkoiichris.nazonoshiro.Console;
import kakkoiichris.nazonoshiro.fighter.Enemy;
import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Pure extends Kusuri {
    public Pure() {
        super("Pure", "", 0, 0, true);
        
        timer = 99;
    }
    
    @java.lang.Override
    public boolean use(Fighter self) {
        return true;
    }
    
    @java.lang.Override
    public void affect(Fighter fighter) {
        var pronoun = (fighter instanceof Enemy) ? "They" : "You";
        
        Console.writeLine("%s used an antidote!%n", pronoun);
    }
}