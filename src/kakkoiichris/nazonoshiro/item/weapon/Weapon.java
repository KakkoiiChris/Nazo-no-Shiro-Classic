package kakkoiichris.nazonoshiro.item.weapon;//Christian Alexander, 5/12/11, Pd. 6

   import kakkoiichris.nazonoshiro.fighter.Fighter;
   import kakkoiichris.nazonoshiro.item.Item;

public abstract class Weapon extends Item
   {
       public Weapon(String n, int v)
      {
         super(n, v);
      }
      
       public int getAtk()
      {
         return value;
      }
      
       public abstract void show();
       
       public void pickUp(Fighter self)
      {
         self.setAtk(1);
      }
      
       public void use(Fighter self, int value)
      {
         System.out.println("You cannot use this.");
      }
   	
       public String toString()
      {
         return name;
      }
   }