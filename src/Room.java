//Christian Alexander, 5/12/11, Pd. 6

   import java.io.*;
   import java.util.*;

    public class Room
   {
      private String name;
      private int puzzle, key, lock, key1, lock1, i, foe;
      protected ArrayList<Wall> walls = new ArrayList();
      protected ArrayList<Item> items = new ArrayList();
      private String choice = new String();
      private final boolean hasStairs;
   
   
       public Room(String na, int p, int k, int l, int f, boolean s)
      {
         name = na;
         puzzle = p;
         key = k;
         lock = l;
         foe = f;
         hasStairs = s;
      }
      
       public Room(String na, int p, int k, int l, boolean s)
      {
         name = na;
         puzzle = p;
         key = k;
         lock = l;
         hasStairs = s;
      }
      
       public void storeState()
      {
         key1 = key;
         for(int i=0; i<walls.size(); i++)
         {
            walls.get(i).getStorage().storeState();
         }
      }
      
       public void resetState()
      {
         key = key1;
         for(int i=0; i<walls.size(); i++)
         {
            walls.get(i).getStorage().resetState();
         }
      }
      
       public int getFoe()
      {
         return foe;
      }
      
       public void setRoom()
      {
         if(walls.size()>0)
         {
            int d=items.size()/walls.size();
            for(int i=0; i<walls.size(); i++)
            {
               for(int j=0; j<d; j++)
               {
                  int random = (int)(Math.random()*items.size());
                  walls.get(i).getStorage().addToStored(items.get(random));
                  items.remove(random);
               }
            }
         }
      }
         
       public void findWall(int N, int S, int E, int W)
      {
         for(int k=0; k<walls.size(); k++)
         {
            if(walls.get(k).getSide() == 'n')
               N++;
            else
               if(walls.get(k).getSide() == 's')                  
                  S++;
               else
                  if(walls.get(k).getSide() == 'e')                     
                     E++;
                  else
                     if(walls.get(k).getSide() == 'w')                        
                        W++;
         }
      }
      
       public void addToRoom(Item a)
      {
         items.add(a);
      }
   		
       public int getSize()
      {
         return walls.size();
      }
   	
       public Wall getWall(int i)
      {
         return walls.get(i);
      }
   	
       public void setWall(int r, int c, char w)
      {
         walls.add(new Wall(r, c, w));
      }
      
       public String getName()
      {
         return name;
      }
      
       public int getPuzzle()
      {
         return puzzle;
      }
      
       public int getKey()
      {
         return key;
      }
      
       public void setKey(int a)
      {
         key = a;
      }
      
       public int getLock()
      {
         return lock;
      }
      
       public String toString()
      {
         return name;
      }
      
       public void look(Fighter self, ArrayList<Item> inventory, char dir)
      {
         if(walls.size() > 0)
         {
            for(i=0; i<walls.size(); i++)
            {
               if(walls.get(i).getSide() == dir)
               {
                  walls.get(i).getStorage().open(self, inventory);
               }
               else
                  System.out.println("Just a door. Nothing else."); 
            }  
         }
         else
            System.out.println("There's nothing there.");
      }
       
       public void redistribute()
      {
         for(int i=0; i<this.getSize(); i++)
         {
            for(int j=0; j<items.size()/this.getSize(); j++)
            {
               this.getWall(i).getStorage().addToStored(items.get((int)(Math.random()*items.size())));
               items.remove(j);
            }
         }
      }
   }