//Christian Alexander, 6/21/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.storage;

import kakkoiichris.nazonoshiro.fighter.Self;
import kakkoiichris.nazonoshiro.item.Item;

import java.util.List;

public class Crate extends Storage {
    public Crate(int x, int y) {
        super("Crate", x, y);
    }
    
    @Override
    public void open(Self self) {
        System.out.println("""
            It seems to be sealed with nails.
            There is a small opening in the lid.
            A short, thin object could be used to
            pry it open.
            
            >\040""".stripIndent());
        
        decision = input.nextLine().toLowerCase();
        
        System.out.println();
        
        if (decision.equals("use tanto") && self.hasItem("tanto")) {
            rummage(self);
        }
    }
}