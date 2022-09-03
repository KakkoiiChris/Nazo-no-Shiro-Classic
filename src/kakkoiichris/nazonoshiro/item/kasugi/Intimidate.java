package kakkoiichris.nazonoshiro.item.kasugi;//Christian Alexander, 9/27/2011

import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Intimidate extends Kasugi {
    public Intimidate() {
        super("Intimidate", 3, 3);
        forYou = false;
    }
    
    public void affect(Fighter any) {
        if (timer != 0) {
            if (any.getName().equals("Ninja") || any.getName().equals("Shogun") || any.getName().equals("Samurai") || any.getName().equals("Daimyo") || any.getName().equals("Imperial Guard"))
                System.out.print("They");
            else
                System.out.print("You");
            System.out.println("'ve been intimidated!");
            System.out.println();
            any.setDef(any.getDef() - 3);
        }
        else {
            System.out.println("Intimidate has worn off.");
        }
        timer--;
    }
}