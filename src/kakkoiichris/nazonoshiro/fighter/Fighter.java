package kakkoiichris.nazonoshiro.fighter;//Christian Alexander, 5/12/11, Pd. 6

   import kakkoiichris.nazonoshiro.item.Item;
   import kakkoiichris.nazonoshiro.item.kasugi.Kasugi;

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
      private ArrayList<Kasugi> effectives = new ArrayList<>();
      protected ArrayList<Kasugi> useable = new ArrayList<>();
      private ArrayList<Item> inventory = new ArrayList<>();
      private ArrayList<Item> inventory1 = new ArrayList<>();
      private ArrayList<Integer> keys = new ArrayList<>();   //stores all the keys you pick up
      private ArrayList<Integer> keys1 = new ArrayList<>();
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
         for(int i = 0; i< getEffectives().size(); i++)
         {
            if(getEffectives().get(i).getTimer() == -1)
               getEffectives().remove(i);
         }
      }
   	
       public void allAffect()
      {
         for(int i = 0; i< getEffectives().size(); i++)
         {
            getEffectives().get(i).affect(this);
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
         for(int i = 0; i< getInventory().size(); i++)
         {
            if(getInventory().get(i).getName().equals(n.toLowerCase()))
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
   
      public ArrayList<Kasugi> getEffectives() {
         return effectives;
      }
   
      public void setEffectives(ArrayList<Kasugi> effectives) {
         this.effectives = effectives;
      }
   
      public ArrayList<Integer> getKeys() {
         return keys;
      }
   
      public void setKeys(ArrayList<Integer> keys) {
         this.keys = keys;
      }
   
      public ArrayList<Item> getInventory() {
         return inventory;
      }
   
      public void setInventory(ArrayList<Item> inventory) {
         this.inventory = inventory;
      }
   
      public ArrayList<Integer> getKeys1() {
         return keys1;
      }
   
      public void setKeys1(ArrayList<Integer> keys1) {
         this.keys1 = keys1;
      }
   
      public ArrayList<Item> getInventory1() {
         return inventory1;
      }
   
      public void setInventory1(ArrayList<Item> inventory1) {
         this.inventory1 = inventory1;
      }
   }