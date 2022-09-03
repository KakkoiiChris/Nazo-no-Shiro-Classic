//Christian Alexander, 5/12/11, Pd. 6

   import java.io.*;
   import java.util.*;

    public class Daimyo extends Enemy
   {
       public Daimyo()
      {
         super("Daimyo", 2, 2, 2, 30);
      }
      
      protected ArrayList<Kasugi> useable = new ArrayList();
   }