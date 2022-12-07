//Christian Alexander, 6/21/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.storage;

import kakkoiichris.nazonoshiro.Console;
import kakkoiichris.nazonoshiro.fighter.Self;

public class Desk extends Storage {
    public Desk() {
        super("Desk");
    }
    
    @Override
    public void open(Self self) {
        Console.writeLine("""
            Despite the presence of a metal lock, the
            desk drawer seems to be propped open slightly.
            Quite a lucky find.""".stripIndent());
        
        rummage(self);
    }
}