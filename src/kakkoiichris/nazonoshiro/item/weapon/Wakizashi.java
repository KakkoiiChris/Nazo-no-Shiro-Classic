//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.item.weapon;

public class Wakizashi extends Weapon {
    public Wakizashi() {
        super("Wakizashi", 1);
    }
    
    public void show() {
        System.out.println("""
            [XXXX{============>
                 Wakizashi""".stripIndent());
    }
}