//Christian Alexander, 6/14/11, Pd. 6
package kakkoiichris.nazonoshiro.item;

import kakkoiichris.nazonoshiro.fighter.Fighter;
import kakkoiichris.nazonoshiro.fighter.Self;

public interface Item {
    int getValue();
    
    String getName();
    
    String getDescription();
    
    boolean pickUp(Self self);
    
    boolean use(Fighter self);
}
