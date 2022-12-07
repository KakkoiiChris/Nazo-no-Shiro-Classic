//Christian Alexander, 6/21/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.storage;

import kakkoiichris.nazonoshiro.Console;
import kakkoiichris.nazonoshiro.fighter.Self;

public class Dresser extends Storage {
    public Dresser() {
        super("Dresser");
    }
    
    @Override
    public void open(Self self) {
        Console.writeLine("""
            The dresser seems to contain but Royal
            Kimonos and Headdresses. Further inspection
            reveals scattered Items on the bottom.""".stripIndent());
        
        rummage(self);
    }
}