package kakkoiichris.nazonoshiro.item.kasugi;//Christian Alexander, 9/13/2011

   import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Corrupt extends Kasugi
   {
       public Corrupt()
      {
         super("kakkoiichris.nazonoshiro.item.kasugi.Corrupt", 1, 99);
         forYou = false;
      }
      
       public void affect(Fighter any)
      {
         if(any.getName().equals("kakkoiichris.nazonoshiro.fighter.Ninja") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Shogun") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Samurai") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Daimyo") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Imperial Guard"))
            System.out.print("They");
         else
            System.out.print("You");
         System.out.println("'ve been poisoned!");
         System.out.println();
         any.setHP(1);
      }  
   }