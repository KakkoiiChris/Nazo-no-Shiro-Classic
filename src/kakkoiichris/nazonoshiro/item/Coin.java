package kakkoiichris.nazonoshiro.item;//Christian Alexander, 6/14/11, Pd. 6
   import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Coin extends Item
   {
       public Coin()
      {
         super("kakkoiichris.nazonoshiro.item.Coin", 5);
      }
   
       public void pickUp(Fighter self)
      {
      }
      
       public void use(Fighter self, int value)
      {
         System.out.println("You cannot use this.");
      }
   
       public String toString()
      {
         return (value)+" Yen.";
      }
   }