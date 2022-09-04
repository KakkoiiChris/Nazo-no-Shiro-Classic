package kakkoiichris.nazonoshiro.item.kasugi;//Christian Alexander, 9/13/2011

import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Velocity extends Kasugi {
    public Velocity() {
        super("Velocity", 3, 3);
        forYou = true;
    }
    
    public void affect(Fighter any) {
        if (timer != 0) {
            if (any.getName().equals("Ninja") || any.getName().equals("Shogun") || any.getName().equals("Samurai") || any.getName().equals("Daimyo") || any.getName().equals("Imperial Guard"))
                System.out.print("Their");
            else
                System.out.print("Your");
            System.out.println(" reactions are much quicker.");
            System.out.println();
            any.setSpeed(any.getSpeed() + 3);
        }
        else {
            System.out.println("Velocity has worn off.");
        }
        timer--;
    }
}