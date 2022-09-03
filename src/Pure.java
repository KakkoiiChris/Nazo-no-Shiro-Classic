//Christian Alexander, 9/13/2011

   import java.io.*;
   import java.util.*;

    public class Pure extends Kasugi
   {
       public Pure()
      {
         super("Pure", 0, 0);
         forYou = true;
         timer = 99;
      }
      
       public void affect(Fighter any)
      {
         if(any.getName().equals("Ninja") || any.getName().equals("Shogun") || any.getName().equals("Samurai") || any.getName().equals("Daimyo") || any.getName().equals("Imperial Guard"))
            System.out.print("They");
         else
            System.out.print("You");
         System.out.println(" used an antidote");
         System.out.println();
      }  
   }