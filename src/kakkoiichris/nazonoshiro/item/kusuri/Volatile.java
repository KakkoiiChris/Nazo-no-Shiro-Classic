//Christian Alexander, 9/13/2011
package kakkoiichris.nazonoshiro.item.kusuri;

import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Volatile extends Kusuri {
    public Volatile() {
        super("Volatile", "", 3, 0, true);
    }
    
    @java.lang.Override
    public boolean use(Fighter self) {
        return true;
    }
    
    @java.lang.Override
    public void affect(Fighter fighter) {
    
    }
}