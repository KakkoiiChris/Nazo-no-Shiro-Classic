package kakkoiichris.nazonoshiro.item.kasugi;//Christian Alexander, 9/13/2011

   import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Brace extends Kasugi
   {
       public Brace()
      {
         super("kakkoiichris.nazonoshiro.item.kasugi.Brace", 3, 3);
         forYou = true;
      }
      
       public void affect(Fighter any)
      {
         if(timer != 0)
         {
            if(any.getName().equals("kakkoiichris.nazonoshiro.fighter.Ninja") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Shogun") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Samurai") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Daimyo") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Imperial Guard"))
               System.out.print("They");
            else
               System.out.print("You");
            System.out.println("'ve braced for impact!");
            System.out.println();
            any.setDef(any.getDef()-3);
         }
         else
         {
            System.out.println("kakkoiichris.nazonoshiro.item.kasugi.Brace has worn off.");
         }
         timer--;
      } 
   }