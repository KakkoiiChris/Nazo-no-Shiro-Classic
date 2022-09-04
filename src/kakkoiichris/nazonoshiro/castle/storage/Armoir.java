package kakkoiichris.nazonoshiro.castle.storage;//Christian Alexander, 6/21/11, Pd. 6

import kakkoiichris.nazonoshiro.fighter.Fighter;
import kakkoiichris.nazonoshiro.fighter.Self;
import kakkoiichris.nazonoshiro.item.Item;

import java.util.List;

public class Armoir extends Storage {
    public Armoir(int x, int y) {
        super("Armoir", x, y);
    }
    
    @Override
    public void open(Self self) {
        System.out.println("""
            A thick rope holds the two doors closed.
            The ends of the rope are melted together.
            A sharp, powerful weapon could be used to
            cut the rope apart.
            
            >\040""".stripIndent());
        
        var decision = input.nextLine().toLowerCase();
        
        System.out.println();
        
        if (decision.equals("use katana") && self.hasItem("katana")) {
            rummage(self);
        }
    }
}