//Christian Alexander, 6/21/11, Pd. 6

   import java.io.*;
   import java.util.*;

    public abstract class Storage
   {
      private String name = new String();
      protected String decision = new String();
      protected String pick = new String();
      private boolean empty = false;
      protected Scanner input = new Scanner(System.in);
      protected int X, Y;
      protected ArrayList<Item> stored = new ArrayList(); 
      protected ArrayList<Item> stored1 = new ArrayList();
      protected ArrayList<Kasugi> contained = new ArrayList();
      protected ArrayList<Kasugi> contained1 = new ArrayList();
   
       public Storage(String n, int x, int y)
      {
         name = n;
         X = x;
         Y = y;
      }
      
       public void storeState()
      {
         for(int i=0; i<stored1.size(); i++)
         {
            stored1.remove(i);
         }
         for(int i=0; i<stored.size(); i++)
         {
            stored1.add(stored.get(i));
         }
      }
      
       public void resetState()
      {
         for(int i=0; i<stored1.size(); i++)
         {
            if(stored.get(i) == null)
               stored.add(stored1.get(i));
               
            else
               if(stored.get(i) != stored1.get(i))
                  stored.add(i, stored1.get(i));
         }  
      }
         
       public void addToStored(Item a)
      {
         stored.add(a);
      } 
      
       public String getName()
      {
         return name;
      }
      
       public int getCount(String n)
      {
         int temp = 0;
         for(int i=0; i<stored.size(); i++)
         {
            if(stored.get(i).getName().equals(n.toLowerCase()))
               temp++;
         }
         return temp;
      } 
      
       public int getCountB(String n)
      {
         int temp = 0;
         for(int i=0; i<contained.size(); i++)
         {
            if(contained.get(i).getName().equals(n))
               temp++;
         }
         return temp;
      }  
      
       public abstract void open(Fighter self, ArrayList<Item> inventory);
   }