//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.item.weapon;

public class Katana extends Weapon {
    public Katana() {
        super("Katana", 1);
    }
    
    public void show() {
        System.out.println("""
            [XXXXX{================>
                     Katana""".stripIndent());
    }
}