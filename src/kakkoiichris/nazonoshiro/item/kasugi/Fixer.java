//Christian Alexander, 9/13/2011
package kakkoiichris.nazonoshiro.item.kasugi;

import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Fixer extends Kasugi {
    public Fixer() {
        super("Kasugi", "", 0, 3, true);
    }
    
    @java.lang.Override
    public boolean use(Fighter self) {
        return true;
    }
    
    @java.lang.Override
    public void affect(Fighter fighter) {
    
    }
}