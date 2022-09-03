//Christian Alexander, 9/13/2011

   import java.io.*;
   import java.util.*;

    public class Corrupt extends Kasugi
   {
       public Corrupt()
      {
         super("Corrupt", 1, 99);
         forYou = false;
      }
      
       public void affect(Fighter any)
      {
         if(any.getName().equals("Ninja") || any.getName().equals("Shogun") || any.getName().equals("Samurai") || any.getName().equals("Daimyo") || any.getName().equals("Imperial Guard"))
            System.out.print("They");
         else
            System.out.print("You");
         System.out.println("'ve been poisoned!");
         System.out.println();
         any.setHP(1);
      }  
   }