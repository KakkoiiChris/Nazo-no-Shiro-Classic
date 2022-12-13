//Christian Alexander, 6/21/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.storage;

import kakkoiichris.nazonoshiro.Console;
import kakkoiichris.nazonoshiro.fighter.Self;
import kakkoiichris.nazonoshiro.item.Weapon;

public class Crate extends Storage {
    public Crate() {
        super("Crate");
    }
    
    @Override
    public void open(Self self) {
        Console.writeLine("""
            It seems to be sealed with nails.
            There is a small opening in the lid.
            A short, thin object could be used to
            pry it open.
            """.stripIndent());
        
        Console.setPrompt("> ");
        
        var decision = Console.readLine().toLowerCase();
        
        Console.newLine();
        
        if (decision.equals("use tanto") && self.hasItem(Weapon.TANTO.getClass())) {
            rummage(self);
        }
    }
}
