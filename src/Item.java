//Christian Alexander, 6/14/11, Pd. 6
   import java.io.*;
   import java.util.*;

    public abstract class Item
   {
      protected String name = new String();
      protected int value;
   
       public Item(String n, int v)
      {
         name = n;
         value = v;
      }
      
       public int getValue()
      {
         return value;
      }
      
       public abstract void pickUp(Fighter self);
      
       public String getName()
      {
         return name.toLowerCase();
      }
             
       public abstract String toString();
       
       public abstract void use(Fighter self, int value);
   }