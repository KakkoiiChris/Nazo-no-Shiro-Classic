package kakkoiichris.nazonoshiro.item.kasugi;//Christian Alexander, 9/27/2011

   import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Sub extends Kasugi
   {
       public Sub()
      {
         super("kakkoiichris.nazonoshiro.item.kasugi.Sub", 3, 3);
         forYou = false;
      }
      
       public void affect(Fighter any)
      {
         if(timer != 0)
         {
            if(any.getName().equals("kakkoiichris.nazonoshiro.fighter.Ninja") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Shogun") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Samurai") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Daimyo") || any.getName().equals("kakkoiichris.nazonoshiro.fighter.Imperial Guard"))
               System.out.print("Their");
            else
               System.out.print("Your");
            System.out.println(" power's been dulled!");
            System.out.println();
            any.setAtk(any.getAtk()-3);
         }
         else
         {
            System.out.println("kakkoiichris.nazonoshiro.item.kasugi.Sub has worn off.");
         }
         timer--;
      }  
   }