package kakkoiichris.nazonoshiro.fighter;//Christian Alexander, 1/1/11, Pd. 6

   import kakkoiichris.nazonoshiro.item.Item;
   import kakkoiichris.nazonoshiro.item.kasugi.*;
   import kakkoiichris.nazonoshiro.item.weapon.Weapon;

   import java.util.*;

    public abstract class Enemy extends Fighter
   {
      private Item droppable, droppable1;
   
       public Enemy(String n, int a, int d, int s, int h)
      {
         super(n, a, d, s, h);
      }
      
       public void setDrop(Fighter self, ArrayList<Weapon> weapons, int wCount)
      {
         wCount = (int)(10-self.getAtk());
         if(Math.random() > 0.5 && wCount > 0)
         {
            int temp = (int)(Math.random()*weapons.size());
            droppable = weapons.get(temp);
         }
      }
      
       public Item getDrop()
      {
         return droppable;
      }
      
       public void storeState()
      {
      }
   	
       public void resetState()
      {
      }
    
       public void dropItem(Fighter self)
      {
         System.out.println("The "+this+" dropped something. You pick it up.");
         
         try
         {
            this.getDrop().pickUp(self);
            System.out.println("It's a "+this.getDrop()+"!");
         }
             catch(NullPointerException e)
            {
               System.out.println("It's just a null. Nothing worth while.");
            }
      }
      
       public void attack(Fighter enemy, String[] list4, String[] list5, String[] list6)
      {
         int aMax=0, aMin=0, dMax=0, dMin=0;
         
         if(this.getAtk() == 1)
         {
            aMax = 6;
            aMin = 1;
         }
         else 
            if(this.getAtk() == 2)
            {
               aMax = 8;
               aMin = 3;
            }
            else
            {
               aMax = 10;
               aMin = 5;
            }
            
         if(enemy.getDef() == 1)
         {
            dMax = 6;
            dMin = 1;
         }
         else 
            if(enemy.getDef() == 2)
            {
               dMax = 8;
               dMin = 3;
            }
            else
            {
               dMax = 10;
               dMin = 5;
            }
            
         int A = (int)(Math.random() * (aMax - aMin)) + aMin;
         int D = (int)(Math.random() * (dMax - dMin)) + dMin;
      	
         if(A + (A - D) < 0)
         {
            temp = pickWord(list6);
            System.out.println(list6[temp].substring(list6[temp].indexOf('@')+1, list6[temp].indexOf('#'))+enemy+list6[temp].substring(list6[temp].indexOf('$')+1, list6[temp].indexOf('%')));
         }
         else
            if(A + (A - D) < aMax)
            {
               temp = pickWord(list5);
               System.out.println(list5[temp].substring(list5[temp].indexOf('@')+1, list5[temp].indexOf('#'))+enemy+list5[temp].substring(list5[temp].indexOf('$')+1, list5[temp].indexOf('%')));
            }
            else
            {
               temp = pickWord(list4);
               System.out.println(list4[temp].substring(list4[temp].indexOf('@')+1, list4[temp].indexOf('#'))+enemy+list4[temp].substring(list4[temp].indexOf('$')+1, list4[temp].indexOf('%')));
            }      
      			
         enemy.setHP(A + (A - D));
         
         System.out.println();
      }
   	   
       public void use(Fighter self)
      {
         int a=0, b=0, c=0, d=0, e=0, f=0, g=0, h=0, i=0;
         
         boolean done = false;
      
         for(int j=0; j<useable.size(); j++)
         {
            if(useable.get(i).getName().equals("kakkoiichris.nazonoshiro.item.kasugi.Blind"))
               a++;
            else
               if(useable.get(i).getName().equals("kakkoiichris.nazonoshiro.item.kasugi.Brace"))
                  b++;
               else
                  if(useable.get(i).getName().equals("kakkoiichris.nazonoshiro.item.kasugi.Burn"))
                     c++;
                  else
                     if(useable.get(i).getName().equals("kakkoiichris.nazonoshiro.item.kasugi.Corrupt"))
                        d++;
                     else
                        if(useable.get(i).getName().equals("kakkoiichris.nazonoshiro.item.kasugi.Fixer"))
                           e++;
                        else
                           if(useable.get(i).getName().equals("kakkoiichris.nazonoshiro.item.kasugi.Pure"))
                              f++;
                           else
                              if(useable.get(i).getName().equals("kakkoiichris.nazonoshiro.item.kasugi.Ultra"))
                                 g++;
                              else
                                 if(useable.get(i).getName().equals("kakkoiichris.nazonoshiro.item.kasugi.Velocity"))
                                    h++;
                                 else
                                    i++;
         }
         
         if(getEffectives().size() > 0)
         {
            for(int j = 0; j< getEffectives().size(); j++)
            {
               if(getEffectives().get(j).getName().equals("kakkoiichris.nazonoshiro.item.kasugi.Corrupt") && health <= 10)
               {                   
                  int index = search("kakkoiichris.nazonoshiro.item.kasugi.Pure", useable);
                  
                  if(index >= 0)
                     useable.remove(index);
                  else
                     System.out.println("NO!!!!!"); 
               	
                  index = search("kakkoiichris.nazonoshiro.item.kasugi.Corrupt", getEffectives());
                  if(index >= 0)
                     getEffectives().remove(index);
                  else
                     System.out.println("NO!!!!!"); 
               
                  System.out.println("They've been purified.");
                  
                  System.out.println();
                  
                  done = true;
               }
                  
               break;
            }
         
            if(done == false)
            {
               int rand = (int)(Math.random()*3)+1;
            
               switch(rand)
               {
                  case 1:
                     for(int k=0; k<useable.size(); k++)
                     {
                        if(useable.get(k).getName().equals("kakkoiichris.nazonoshiro.item.kasugi.Ultra"))
                        {
                           getEffectives().add(useable.remove(k));
                           done = true;
                           break;
                        }
                     }
                     break;
                  case 2:
                     for(int k=0; k<useable.size(); k++)
                     {
                        if(useable.get(k).getName().equals("kakkoiichris.nazonoshiro.item.kasugi.Brace"))
                        {
                           getEffectives().add(new Brace());
                           useable.remove(k);
                           done = true;
                           break;
                        }
                     }
                     break;
                  case 3:
                     for(int k=0; k<useable.size(); k++)
                     {
                        if(useable.get(k).getName().equals("kakkoiichris.nazonoshiro.item.kasugi.Velocity"))
                        {
                           getEffectives().add(new Velocity());
                           useable.remove(k);
                           done = true;
                           break;
                        }
                     }
                     break;
               }
            }
            
            if(done == false)
            {
               int rand = (int)(Math.random()*3)+1;
            
               switch(rand)
               {
                  case 1:
                     for(int k=0; k<useable.size(); k++)
                     {
                        if(useable.get(k).getName().equals("kakkoiichris.nazonoshiro.item.kasugi.Sub"))
                        {
                           getEffectives().add(new Sub());
                           useable.remove(k);
                           done = true;
                           break;
                        }
                     }
                     break;
                  case 2:
                     for(int k=0; k<useable.size(); k++)
                     {
                        if(useable.get(k).getName().equals("kakkoiichris.nazonoshiro.item.kasugi.Intimidate"))
                        {
                           getEffectives().add(new Intimidate());
                           useable.remove(k);
                           done = true;
                           break;
                        }
                     }
                     break;
                  case 3:
                     for(int k=0; k<useable.size(); k++)
                     {
                        if(useable.get(k).getName().equals("kakkoiichris.nazonoshiro.item.kasugi.Blind"))
                        {
                           getEffectives().add(new Blind());
                           useable.remove(k);
                           done = true;
                           break;
                        }
                     }
                     break;
               }
            } 
         
            if(done == false)
            {
               done = true;
            }
         }
      }
      
       public int search(String type, ArrayList<Kasugi> any)
      {
         for(int k=0; k<any.size(); k++)
         {
            if(((any.get(k)).getName()).equals(type))
               return k;
         }
         return -5;
      }
   }