//Christian Alexander, 6/21/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.storage;

import kakkoiichris.kotoba.Console;
import kakkoiichris.nazonoshiro.fighter.Self;
import kakkoiichris.nazonoshiro.item.Weapon;

public class Armoire extends Storage {
    public Armoire() {
        super("Armoire");
    }
    
    @Override
    public void open(Console console, Self self) {
        console.writeLine("""
            A thick rope holds the two doors closed.
            The ends of the rope are melted together.
            A sharp, powerful weapon could be used to
            cut the rope apart.
            """.stripIndent());
        
        console.setPrompt("> ");
        
        var decision = console.readLine().orElseThrow().toLowerCase();
        
        console.newLine();
        
        if (decision.equals("use katana") && self.hasItem(Weapon.KATANA.getClass())) {
            rummage(console, self);
        }
    }
}
