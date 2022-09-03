package kakkoiichris.nazonoshiro.fighter;//Christian Alexander, 5/12/11, Pd. 6

   import kakkoiichris.nazonoshiro.item.kasugi.Kasugi;

   import java.util.*;

    public class Shogun extends Enemy
   {   
       public Shogun()
      {
         super("Shogun", 2, 3, 1, 30);
      }
   	
      protected ArrayList<Kasugi> useable = new ArrayList();
   }