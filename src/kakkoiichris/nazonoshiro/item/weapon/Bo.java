//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.item.weapon;

public class Bo extends Weapon {
    public Bo() {
        super("Bo Staff", 1);
    }
    
    public void show() {
        System.out.println("""
            ========XXXXXX========
                   Bo Staff""".stripIndent());
    }
}