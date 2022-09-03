package kakkoiichris.nazonoshiro.item.kasugi;//Christian Alexander, 9/13/2011

   import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Blind extends Kasugi
   {
       public Blind()
      {
         super("Blind", 3, 3);
         forYou = false;
      }
      
       public void affect(Fighter any)
      {
         if(timer != 0)
         {
            if(any.getName().equals("Ninja") || any.getName().equals("Shogun") || any.getName().equals("Samurai") || any.getName().equals("Daimyo") || any.getName().equals("Imperial Guard"))
               System.out.print("They");
            else
               System.out.print("You");
            System.out.println("'ve been blinded!");
            System.out.println();
            any.setSpd(any.getSpd()-3);
         }
         else
         {
            System.out.println("Blind has worn off.");
            System.out.println();
            any.setSpd(any.getSpd()+3);
         }
         timer--;
      }  
   }