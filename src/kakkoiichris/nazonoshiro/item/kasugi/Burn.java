package kakkoiichris.nazonoshiro.item.kasugi;//Christian Alexander, 9/13/2011

import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Burn extends Kasugi {
    public Burn() {
        super("Burn", 3, -1);
    }
    
    public void affect(Fighter any) {
        if (timer != 0) {
            if (any.getName().equals("Ninja") || any.getName().equals("Shogun") || any.getName().equals("Samurai") || any.getName().equals("Daimyo") || any.getName().equals("Imperial Guard"))
                System.out.print("Thier");
            else
                System.out.print("Your");
            System.out.println(" shield's been degraded!");
            System.out.println();
            any.setSpeed(any.getSpeed() - magnitude);
        }
        else {
            if (any.getName().equals("Ninja") || any.getName().equals("Shogun") || any.getName().equals("Samurai") || any.getName().equals("Daimyo") || any.getName().equals("Imperial Guard"))
                System.out.print("Thier");
            else
                System.out.print("Your");
            System.out.println(" poison is cured!");
            System.out.println();
            setTimer(0);
        }
    }
}