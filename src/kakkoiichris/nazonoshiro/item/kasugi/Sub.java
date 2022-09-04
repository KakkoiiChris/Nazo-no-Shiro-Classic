package kakkoiichris.nazonoshiro.item.kasugi;//Christian Alexander, 9/27/2011

import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Sub extends Kasugi {
    public Sub() {
        super("Sub", 3, 3);
        forYou = false;
    }
    
    public void affect(Fighter any) {
        if (timer != 0) {
            if (any.getName().equals("Ninja") || any.getName().equals("Shogun") || any.getName().equals("Samurai") || any.getName().equals("Daimyo") || any.getName().equals("Imperial Guard"))
                System.out.print("Their");
            else
                System.out.print("Your");
            System.out.println(" power's been dulled!");
            System.out.println();
            any.setAttack(any.getAttack() - 3);
        }
        else {
            System.out.println("Sub has worn off.");
        }
        timer--;
    }
}