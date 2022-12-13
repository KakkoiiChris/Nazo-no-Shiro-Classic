//Christian Alexander, 6/21/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.storage;

import kakkoiichris.nazonoshiro.Console;
import kakkoiichris.nazonoshiro.fighter.Self;

public class JewelryBox extends Storage {
    public JewelryBox() {
        super("Jewelry Box");
    }
    
    @Override
    public void open(Self self) {
        Console.writeLine("""
            An ornate and surprisingly big jewelry box
            sits upon a small table. There may be useful
            Items amongst the gemstones.
            """.stripIndent());
        
        rummage(self);
    }
}
