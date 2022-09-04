//Christian Alexander, 6/21/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.storage;

import kakkoiichris.nazonoshiro.fighter.Self;

public class Dresser extends Storage {
    public Dresser(int x, int y) {
        super("Dresser", x, y);
    }
    
    @Override
    public void open(Self self) {
        System.out.println("""
            The dresser seems to contain but Royal
            Kimonos and Headdresses. Further inspection
            reveals scattered Items on the bottom.""".stripIndent());
        
        rummage(self);
    }
}