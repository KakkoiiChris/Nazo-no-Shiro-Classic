package kakkoiichris.nazonoshiro.item.kasugi;//Christian Alexander, 9/27/2011

   import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Intimidate extends Kasugi
   {
       public Intimidate()
      {
         super("kakkoiichris.nazonoshiro.item.kasugi.Intimidate", 3, 3);
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
            System.out.println("'ve been intimidated!");
            System.out.println();
            any.setDef(any.getDef()-3);
         }
         else
         {
            System.out.println("kakkoiichris.nazonoshiro.item.kasugi.Intimidate has worn off.");
         }
         timer--;
      }  
   }