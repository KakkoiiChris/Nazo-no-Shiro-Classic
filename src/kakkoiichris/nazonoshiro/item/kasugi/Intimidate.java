//Christian Alexander, 9/27/2011
package kakkoiichris.nazonoshiro.item.kasugi;

import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Intimidate extends Kasugi {
    public Intimidate() {
        super("Intimidate", 3, 3, false);
    }
    
    @java.lang.Override
    public void affect(Fighter fighter) {
        if (timer != 0) {
            if (fighter.getName().equals("Ninja") || fighter.getName().equals("Shogun") || fighter.getName().equals("Samurai") || fighter.getName().equals("Daimyo") || fighter.getName().equals("Imperial Guard"))
                System.out.print("They");
            else
                System.out.print("You");
            System.out.println("'ve been intimidated!");
            System.out.println();
            fighter.setDefense(fighter.getDefense() - 3);
        }
        else {
            System.out.println("Intimidate has worn off.");
        }
        timer--;
    }
}