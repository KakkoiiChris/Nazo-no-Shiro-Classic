package kakkoiichris.nazonoshiro.item.kasugi;//Christian Alexander, 9/13/2011

   import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Blind extends Kasugi
   {
       public Blind()
      {
         super("kakkoiichris.nazonoshiro.item.kasugi.Blind", 3, 3);
         forYou = false;
      }
      
       public void affect(Fighter any)
      {
         if(timer != 0)
         {
            if(any.getName().equals("kakkoiichris.nazonoshiro.fighter.Ninja") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Shogun") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Samurai") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Daimyo") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Imperial Guard"))
               System.out.print("They");
            else
               System.out.print("You");
            System.out.println("'ve been blinded!");
            System.out.println();
            any.setSpd(any.getSpd()-3);
         }
         else
         {
            System.out.println("kakkoiichris.nazonoshiro.item.kasugi.Blind has worn off.");
            System.out.println();
            any.setSpd(any.getSpd()+3);
         }
         timer--;
      }  
   }