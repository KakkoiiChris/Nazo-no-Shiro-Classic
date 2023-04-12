//Christian Alexander, 6/14/11, Pd. 6
package kakkoiichris.nazonoshiro.item;

import kakkoiichris.kotoba.Console;
import kakkoiichris.nazonoshiro.fighter.Fighter;
import kakkoiichris.nazonoshiro.fighter.Self;


public interface Item {
    int getValue();
    
    String getName();
    
    String getDescription();
    
    boolean pickUp(Console console, Self self);
    
    boolean use(Console console, Fighter self);
}
