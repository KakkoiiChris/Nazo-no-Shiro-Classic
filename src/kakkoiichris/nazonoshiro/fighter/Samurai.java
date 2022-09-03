package kakkoiichris.nazonoshiro.fighter;//Christian Alexander, 5/12/11, Pd. 6

   import kakkoiichris.nazonoshiro.item.kasugi.Kasugi;

   import java.util.*;

    public class Samurai extends Enemy
   {   
       public Samurai()
      {
         super("kakkoiichris.nazonoshiro.fighter.Samurai", 3, 1, 2, 25);
      }
   	
      protected ArrayList<Kasugi> useable = new ArrayList();
   }