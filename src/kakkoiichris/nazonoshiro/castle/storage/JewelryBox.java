//Christian Alexander, 6/21/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.storage;

import kakkoiichris.nazonoshiro.fighter.Self;

public class JewelryBox extends Storage {
    public JewelryBox(int x, int y) {
        super("Jewelry Box", x, y);
    }
    
    @Override
    public void open(Self self) {
        System.out.println("""
            An ornate and surprisingly big jewelry box
            sits upon a small table. There may be useful
            Items amongst the gemstones.""".stripIndent());
        
        rummage(self);
    }
}
