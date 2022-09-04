//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.item.weapon;

public class Tanto extends Weapon {
    public Tanto() {
        super("Tanto", 1);
    }
    
    public void show() {
        System.out.println("""
            [XXX{========>
                Tanto""".stripIndent());
    }
}