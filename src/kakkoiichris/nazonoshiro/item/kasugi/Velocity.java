package kakkoiichris.nazonoshiro.item.kasugi;//Christian Alexander, 9/13/2011

   import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Velocity extends Kasugi
   {
       public Velocity()
      {
         super("kakkoiichris.nazonoshiro.item.kasugi.Velocity", 3, 3);
         forYou = true;
      }
      
       public void affect(Fighter any)
      {
         if(timer != 0)
         {
            if(any.getName().equals("kakkoiichris.nazonoshiro.fighter.Ninja") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Shogun") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Samurai") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Daimyo") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Imperial Guard"))
               System.out.print("Their");
            else
               System.out.print("Your");
            System.out.println(" reactions are much quicker.");
            System.out.println();
            any.setSpd(any.getSpd()+3);
         }
         else
         {
            System.out.println("kakkoiichris.nazonoshiro.item.kasugi.Velocity has worn off.");
         }
         timer--;
      } 
   }