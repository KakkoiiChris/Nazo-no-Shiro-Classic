//Christian Alexander, 5/12/11, Pd. 6

   import java.io.*;
   import java.util.*;

    public class Imperial extends Enemy
   {
       public Imperial()
      {
         super("Imperial Guard", 3, 3, 3, 35);
      }
      
      protected ArrayList<Kasugi> useable = new ArrayList();
   }