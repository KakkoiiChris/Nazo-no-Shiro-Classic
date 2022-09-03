//Christian Alexander, 5/12/11, Pd. 6

   import java.io.*;
   import java.util.*;

    public class Samurai extends Enemy
   {   
       public Samurai()
      {
         super("Samurai", 3, 1, 2, 25);
      }
   	
      protected ArrayList<Kasugi> useable = new ArrayList();
   }