//Christian Alexander, 5/12/11, Pd. 6

   import java.io.*;
   import java.util.*;

    public abstract class Fighter
   {
      private String name, action;
      private int turn;
      protected int temp;
      protected int attack,
      				  defense,
      				  speed, 
      				  health, 
      				  x, 
      				  y,
      				  attack1,
      				  defense1,
      				  speed1, 
      				  health1,
      				  x1, 
      				  y1; 
      				  
      protected boolean ran = false, ran1 = false;
      protected Scanner input = new Scanner(System.in);
      protected ArrayList<Kasugi> effectives = new ArrayList();
      protected ArrayList<Kasugi> useable = new ArrayList();
      protected ArrayList<Item> inventory = new ArrayList();
      protected ArrayList<Item> inventory1 = new ArrayList();
      protected ArrayList<Integer> keys = new ArrayList();   //stores all the keys you pick up
      protected ArrayList<Integer> keys1 = new ArrayList();
      protected String gender = new String(), DoB = new String(); 
      
       public Fighter(String n, int a, int d, int s, int h)
      {
         name = n;
         attack = a;
         defense = d;
         speed = s;
         health = h;
      }
      
       public void setName(String n)
      {
         name = n;
      }
     
       public int getAtk()
      {
         return attack;
      }
   	
       public void setAtk(int n)
      {
         attack += n;
      }
   	
       public int getDef()
      {
         return defense;
      }
      
       public void setDef(int n)
      {
         defense = n;
      }
   	
       public int getSpd()
      {
         return speed;
      }
      
       public void setSpd(int n)
      {
         speed = n;
      }
   	
       public int getHP()
      {
         return health;
      }
      
       public void setHP(int a)
      {
         health = health - a;
      }
   	
       public String getName()
      {
         return name;
      }
   
       public void heal(int value)
      {
         health += value;
      }
      
       public boolean isDead()
      {
         return health <= 0;
      }
   
       public boolean hasItem(String a, ArrayList<Item> inventory)
      {
         for(int i=0; i<inventory.size(); i++)
         {
            if(inventory.get(i).getName().equalsIgnoreCase(a))
               return true;
         }
         return false;
      } 	
   	
       public void setRandomRoom()
      {
         int a = (int)(Math.random()*5);
         int b = (int)(Math.random()*4);
         if(a!=1 && b!=2)
            x=a;
         y=b;
      }
      
       public static int pickWord(String[] words)// picks a random Item from the ".txt" file that it is sent
      {
         int x = (int)(Math.random()*words.length);
         return x;
      }
      
       public void filter()
      {
         for(int i=0; i<effectives.size(); i++)
         {
            if(effectives.get(i).getTimer() == -1)
               effectives.remove(i);
         }
      }
   	
       public void allAffect()
      {
         for(int i=0; i<effectives.size(); i++)
         {
            effectives.get(i).affect(this);
         }
      }
   	
       public String toString()
      {
         return name;
      }
    
       public void showHP()
      {
         System.out.print(name+": "+health+" [");
         for(int i=0; i<health; i++)
         {
            System.out.print("=");
         }
         System.out.println("]");
         System.out.println();
      } 
    
       public int getCount(String n)
      {
         int temp = 0;
         for(int i=0; i<inventory.size(); i++)
         {
            if(inventory.get(i).getName().equals(n.toLowerCase()))
               temp++;
         }
         return temp;
      }
      
       public int getCountB(String n)
      {
         int temp = 0;
         for(int i=0; i<useable.size(); i++)
         {
            if(useable.get(i).getName().equals(n.toLowerCase()))
               temp++;
         }
         return temp;
      }
    
       public void addKasugi(Kasugi a)
      {
         useable.add(a);
      }
      
       public abstract void attack(Fighter enemy, String[] a, String[] b, String[] c);
            
       public abstract void use(Fighter enemy);
       
       public abstract void storeState();
      
       public abstract void resetState();
   }