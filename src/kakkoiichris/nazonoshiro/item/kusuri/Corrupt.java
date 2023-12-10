//Christian Alexander, 9/13/2011
package kakkoiichris.nazonoshiro.item.kusuri;

import kakkoiichris.kotoba.Console;
import kakkoiichris.nazonoshiro.fighter.Enemy;
import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Corrupt extends Kusuri {
    public Corrupt() {
        super("Corrupt", "", 1, 99, false);
    }
    
    @java.lang.Override
    public boolean use(Console console, Fighter fighter) {
        return true;
    }
    
    @java.lang.Override
    public void affect(Console console, Fighter fighter) {
        var pronoun = fighter.getPronoun();
        
        console.writeLine("%s've been poisoned!%n", pronoun);
        
        fighter.setHealth(1);
    }
}