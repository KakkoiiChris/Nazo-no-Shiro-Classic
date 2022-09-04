//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.item.weapon;

public class Nunchaku extends Weapon {
    public Nunchaku() {
        super("Nunchaku", 1);
    }
    
    public void show() {
        System.out.println("""
            |XXXX|O-oooooo-O|XXXX|
                   Nunchaku""".stripIndent());
    }
}