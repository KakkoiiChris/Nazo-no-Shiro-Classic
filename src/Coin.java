//Christian Alexander, 6/14/11, Pd. 6
   import java.io.*;
   import java.util.*;

    public class Coin extends Item
   {
       public Coin()
      {
         super("Coin", 5);
      }
   
       public void pickUp(Fighter self)
      {
      }
      
       public void use(Fighter self, int value)
      {
         System.out.println("You cannot use this.");
      }
   
       public String toString()
      {
         return (value)+" Yen.";
      }
   }