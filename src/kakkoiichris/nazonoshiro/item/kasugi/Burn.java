package kakkoiichris.nazonoshiro.item.kasugi;//Christian Alexander, 9/13/2011

   import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Burn extends Kasugi
   {
       public Burn()
      {
         super("kakkoiichris.nazonoshiro.item.kasugi.Burn", 3, -1);
      }
      
       public void affect(Fighter any)
      {
         if(timer != 0)
         {
            if(any.getName().equals("kakkoiichris.nazonoshiro.fighter.Ninja") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Shogun") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Samurai") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Daimyo") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Imperial Guard"))
               System.out.print("Thier");
            else
               System.out.print("Your");
            System.out.println(" shield's been degraded!");
            System.out.println();
            any.setSpd(any.getSpd()-magnitude);
         }
         else
         {
            if(any.getName().equals("kakkoiichris.nazonoshiro.fighter.Ninja") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Shogun") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Samurai") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Daimyo") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Imperial Guard"))
               System.out.print("Thier");
            else
               System.out.print("Your");
            System.out.println(" poison is cured!");
            System.out.println();
            setTimer(0);
         }
      } 
   }