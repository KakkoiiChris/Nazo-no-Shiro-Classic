//Christian Alexander, 5/12/11, Pd. 6

   import java.io.*;
   import java.util.*;

    public class Ninja extends Enemy
   {
       public Ninja()
      {
         super("Ninja", 1, 2, 3, 25);
      }  
      
      protected ArrayList<Kasugi> useable = new ArrayList();
   }