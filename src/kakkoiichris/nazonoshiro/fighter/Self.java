package kakkoiichris.nazonoshiro.fighter;//Christian Alexander, 5/12/11, Pd. 6

import kakkoiichris.nazonoshiro.item.kasugi.Kasugi;

public class Self extends Fighter
   {   
       public Self()
      {
         super("kakkoiichris.nazonoshiro.fighter.Self", (int)(Math.random()*5)+3, (int)(Math.random()*5)+3, (int)(Math.random()*5)+3, 50);
      }
   	
       public void addKasugi(Kasugi a)
      {
         useable.add(a);
      }
      
       public void storeState()
      {
         attack1 = attack;
         defense1 = defense;
         speed1 = speed;
         health1 = health;
         x1 = x;
         y1 = y;
      }
      
       public void resetState()
      {
         attack = attack1;
         defense = defense1;
         speed = speed1;
         health = health1;
         x = x1;
         y = y1;
      }
      
       public String getGender()
      {
         return gender;
      }
   
       public String getDoB()
      {
         return DoB;
      }
   	
       public void attack(Fighter enemy, String[] list, String[] list2, String[] list3)
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
            temp = pickWord(list3);
            System.out.println(list3[temp].substring(list3[temp].indexOf('@')+1, list3[temp].indexOf('#'))+enemy+list3[temp].substring(list3[temp].indexOf('$')+1, list3[temp].indexOf('%')));
         }
         else
            if(A + (A - D) < aMax)
            {
               temp = pickWord(list2);
               System.out.println(list2[temp].substring(list2[temp].indexOf('@')+1, list2[temp].indexOf('#'))+enemy+list2[temp].substring(list2[temp].indexOf('$')+1, list2[temp].indexOf('%')));
            }
            else
            {
               temp = pickWord(list);
               System.out.println(list[temp].substring(list[temp].indexOf('@')+1, list[temp].indexOf('#'))+enemy+list[temp].substring(list[temp].indexOf('$')+1, list[temp].indexOf('%')));
            }      
      			
         enemy.setHP(A + (A - D));
         
         System.out.println();
      }
      
       public void use(Fighter enemy)
      {
         int a=0, b=0, c=0, d=0, e=0, f=0, g=0, h=0, i=0;
         
         boolean done = false;
      	
         System.out.println("kakkoiichris.nazonoshiro.item.kasugi.Kasugi:");
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
      	
         if(a > 0)
            System.out.println("kakkoiichris.nazonoshiro.item.kasugi.Blind: [" + a + "]");
         if(b > 0)
            System.out.println("kakkoiichris.nazonoshiro.item.kasugi.Brace: [" + b + "]");
         if(c > 0)
            System.out.println("kakkoiichris.nazonoshiro.item.kasugi.Burn: [" + c + "]");
         if(d > 0)
            System.out.println("kakkoiichris.nazonoshiro.item.kasugi.Corrupt: [" + d + "]");
         if(e > 0)
            System.out.println("kakkoiichris.nazonoshiro.item.kasugi.Fixer: [" + e + "]");
         if(f > 0)
            System.out.println("kakkoiichris.nazonoshiro.item.kasugi.Pure: [" + f + "]");
         if(g > 0)
            System.out.println("kakkoiichris.nazonoshiro.item.kasugi.Ultra: [" + g + "]");
         if(h > 0)
            System.out.println("kakkoiichris.nazonoshiro.item.kasugi.Velocity: [" + h + "]");
         if(i > 0)
            System.out.println("kakkoiichris.nazonoshiro.item.kasugi.Volatile: [" + i + "]");
         System.out.println();
            
         while(done == false)
         {
            System.out.print("> ");
            String temp = input.next();
            System.out.println();
            System.out.println();
         
            while(!temp.equals("kakkoiichris.nazonoshiro.item.kasugi.Blind") && !temp.equals("kakkoiichris.nazonoshiro.item.kasugi.Brace") && !temp.equals("kakkoiichris.nazonoshiro.item.kasugi.Burn") && !temp.equals("kakkoiichris.nazonoshiro.item.kasugi.Corrupt") && !temp.equals("kakkoiichris.nazonoshiro.item.kasugi.Fixer") && !temp.equals("kakkoiichris.nazonoshiro.item.kasugi.Pure") && !temp.equals("kakkoiichris.nazonoshiro.item.kasugi.Ultra") && !temp.equals("kakkoiichris.nazonoshiro.item.kasugi.Velocity") && !temp.equals("kakkoiichris.nazonoshiro.item.kasugi.Volatile"))
            {
               System.out.print("What?");
               System.out.println();
               System.out.print("> ");
               temp = input.nextLine();
               System.out.println();
               System.out.println();
            }
         
            int k=0;
            for(int j=0; j<useable.size(); j++)
            {
               if(temp.equals(useable.get(j).getName()))
               {
                  if(useable.get(j).getForYou() == true)
                  {
                     this.getEffectives().add(useable.get(j));
                  }
                  else
                  {
                     enemy.getEffectives().add(useable.get(j));
                  }
                  done = true;
                  k=0;
               }
               else
                  k++;
            }
         
            if(k == useable.size() && done == false)
            {
               System.out.println("You don't have any " + temp + "s to use.");
               System.out.println();
            }
         }
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
   }