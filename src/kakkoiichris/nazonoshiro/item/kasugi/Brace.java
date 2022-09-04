package kakkoiichris.nazonoshiro.item.kasugi;//Christian Alexander, 9/13/2011

import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Brace extends Kasugi {
    public Brace() {
        super("Brace", 3, 3);
        forYou = true;
    }
    
    public void affect(Fighter any) {
        if (timer != 0) {
            if (any.getName().equals("Ninja") || any.getName().equals("Shogun") || any.getName().equals("Samurai") || any.getName().equals("Daimyo") || any.getName().equals("Imperial Guard"))
                System.out.print("They");
            else
                System.out.print("You");
            System.out.println("'ve braced for impact!");
            System.out.println();
            any.setDefense(any.getDefense() - 3);
        }
        else {
            System.out.println("Brace has worn off.");
        }
        timer--;
    }
}