package kakkoiichris.nazonoshiro.castle.storage;//Christian Alexander, 6/21/11, Pd. 6

   import kakkoiichris.nazonoshiro.fighter.Fighter;
   import kakkoiichris.nazonoshiro.item.Item;

   import java.util.*;

    public class Armoir extends Storage
   {
       public Armoir(int x, int y)
      {
         super("kakkoiichris.nazonoshiro.castle.storage.Armoir", x, y);
      } 
      
       public void open(Fighter self, ArrayList<Item> inventory)
      {
         System.out.println("A thick rope holds the two doors closed.");
         System.out.println("The ends of the rope are melted together.");
         System.out.println("A sharp, powerful weapon could be used to");
         System.out.println("cut the rope apart.");
         System.out.println();
         System.out.print(">");
         decision = input.nextLine();
         decision = decision.toLowerCase();
         System.out.println();
         if(decision.equals("use katana") && self.hasItem("kakkoiichris.nazonoshiro.item.weapon.Katana", inventory) == true)
         {
            System.out.println("It opened.");
            System.out.println();
            
            int m=0, h=0, b=0, c=0;
         
            for(int i=0; i<stored.size(); i++)
            {
               if(stored.get(i).getName().equals("metal"))
                  m++;
               else
                  if(stored.get(i).getName().equals("kakkoiichris.nazonoshiro.item.Coin"))
                     c++;
                  else
                     if(stored.get(i).getName().equals("herb"))
                        h++;
                     else
                        if(stored.get(i).getName().equals("bushel"))
                           b++;
            }
            
            System.out.println("The kakkoiichris.nazonoshiro.castle.storage.Armoir contains:");
            System.out.println();
            
            if(m>0)
            {
               {
                  System.out.print("-"+m+" metal plate");
                  if(m>1)
                     System.out.println("s");
                  else
                     System.out.println();
               }
               System.out.println();
            }
            
            if(c>0)
            {
               {
                  System.out.print("-"+c+" kakkoiichris.nazonoshiro.item.Coin");
                  if(c>1)
                     System.out.println("s");
                  else
                     System.out.println();
               }
               System.out.println();
            }
            
            if(h>0)
            {
               {
                  System.out.print("-"+h+" herb");
                  if(h>1)
                     System.out.println("s");
                  else
                     System.out.println();
               }
               System.out.println();
            }
            
            if(b>0)
            {
               {
                  System.out.print("-"+b+" herb bushel");
                  if(b>1)
                     System.out.println("s");
                  else
                     System.out.println();
               }
               System.out.println();
            }
            
                                 
                                
            System.out.print(">");
            pick = input.nextLine();
            pick = pick.toLowerCase();
            System.out.println();
               
            while(!pick.equals("close armoir"))
            {
               if(pick.equals("take metal plate"))
               {
                  for(int i=0; i<stored.size(); i++)
                  {
                     if((stored.get(i).getName()).equals("metal"))
                     {
                        inventory.add(stored.get(i));
                        stored.remove(i);
                     }
                  }
                  System.out.println("Taken");
                  System.out.println();
               }
               else
                  if(pick.equals("take herb bushel"))
                  {
                     for(int i=0; i<stored.size(); i++)
                     {
                        if((stored.get(i).getName()).equals("herb bushel"))
                        {
                           inventory.add(stored.get(i));
                           stored.remove(i);
                        }
                     }
                     System.out.println("Taken");
                     System.out.println();
                  }
                  else
                     if(pick.equals("take herb"))
                     {
                        for(int i=0; i<stored.size(); i++)
                        {
                           if((stored.get(i).getName()).equals("herb"))
                           {
                              inventory.add(stored.get(i));
                              stored.remove(i);
                           }
                        }
                        System.out.println("Taken");
                        System.out.println();
                     }
                     else
                        if(pick.equals("take kakkoiichris.nazonoshiro.item.Coin"))
                        {
                           for(int i=0; i<stored.size(); i++)
                           {
                              if((stored.get(i).getName()).equals("kakkoiichris.nazonoshiro.item.Coin"))
                              {
                                 inventory.add(stored.get(i));
                                 stored.remove(i);
                              }
                           }
                           System.out.println("Taken");
                           System.out.println();
                        }
               System.out.print(">");
               pick = input.nextLine();
               pick = pick.toLowerCase();
               System.out.println();
            }
         }
      }
   }