package kakkoiichris.nazonoshiro.fighter;//Christian Alexander, 5/12/11, Pd. 6

   import kakkoiichris.nazonoshiro.item.kasugi.Kasugi;

   import java.util.*;

    public class Imperial extends Enemy
   {
       public Imperial()
      {
         super("kakkoiichris.nazonoshiro.fighter.Imperial Guard", 3, 3, 3, 35);
      }
      
      protected ArrayList<Kasugi> useable = new ArrayList();
   }