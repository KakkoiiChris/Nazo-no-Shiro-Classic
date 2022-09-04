//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.item.weapon;

public class Shuriken extends Weapon {
    public Shuriken() {
        super("Shuriken", 1);
    }
    
    public void show() {
        System.out.println("""
                O-]==>
            Shuriken Knife""".stripIndent());
    }
}