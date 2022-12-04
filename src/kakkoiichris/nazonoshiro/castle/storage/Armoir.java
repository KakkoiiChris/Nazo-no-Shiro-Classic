//Christian Alexander, 6/21/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.storage;

import kakkoiichris.nazonoshiro.fighter.Self;
import kakkoiichris.nazonoshiro.item.Weapon;

public class Armoir extends Storage {
    public Armoir() {
        super("Armoir");
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
        
        if (decision.equals("use katana") && self.hasItem(Weapon.KATANA.getClass())) {
            rummage(self);
        }
    }
}
