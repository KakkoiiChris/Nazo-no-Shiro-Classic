//Christian Alexander, 9/13/2011
package kakkoiichris.nazonoshiro.item.kusuri;

import kakkoiichris.kotoba.Console;
import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Fixer extends Kusuri {
    public Fixer() {
        super("Kasugi", "", 0, 3, true);
    }
    
    @java.lang.Override
    public boolean use(Console console, Fighter self) {
        return true;
    }
    
    @java.lang.Override
    public void affect(Console console, Fighter fighter) {
    
    }
}