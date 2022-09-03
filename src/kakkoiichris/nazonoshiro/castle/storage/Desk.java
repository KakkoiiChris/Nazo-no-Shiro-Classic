package kakkoiichris.nazonoshiro.castle.storage;//Christian Alexander, 6/21/11, Pd. 6

   import kakkoiichris.nazonoshiro.fighter.Fighter;
   import kakkoiichris.nazonoshiro.item.Item;

   import java.util.*;

    public class Desk extends Storage
   {
       public Desk(int x, int y)
      {
         super("Desk", x, y);
      } 
      
       public void open(Fighter self, ArrayList<Item> inventory)
      {
         System.out.println("Despite the presence of a metal lock, the");
         System.out.println("desk drawer seems to be proped open slightly.");
         System.out.println("Quite a lucky find.");
         System.out.println();
            
         int m=0, h=0, b=0, c=0;
         
         for(int i=0; i<stored.size(); i++)
         {
            if(stored.get(i).getName().equals("metal"))
               m++;
            else
               if(stored.get(i).getName().equals("Coin"))
                  c++;
               else
                  if(stored.get(i).getName().equals("herb"))
                     h++;
                  else
                     if(stored.get(i).getName().equals("bushel"))
                        b++;
         }
            
         System.out.println("The Desk contains:");
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
               System.out.print("-"+c+" Coin");
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
               
         while(!pick.equals("close desk"))
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
                     System.out.println();                  }
                  else
                     if(pick.equals("take Coin"))
                     {
                        for(int i=0; i<stored.size(); i++)
                        {
                           if((stored.get(i).getName()).equals("Coin"))
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