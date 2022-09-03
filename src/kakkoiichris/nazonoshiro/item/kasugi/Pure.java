package kakkoiichris.nazonoshiro.item.kasugi;//Christian Alexander, 9/13/2011

   import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Pure extends Kasugi
   {
       public Pure()
      {
         super("kakkoiichris.nazonoshiro.item.kasugi.Pure", 0, 0);
         forYou = true;
         timer = 99;
      }
      
       public void affect(Fighter any)
      {
         if(any.getName().equals("kakkoiichris.nazonoshiro.fighter.Ninja") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Shogun") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Samurai") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Daimyo") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Imperial Guard"))
            System.out.print("They");
         else
            System.out.print("You");
         System.out.println(" used an antidote");
         System.out.println();
      }  
   }