//Christian Alexander, 6/22/11, Period 6

   import java.io.*;
   import java.util.*;

    public class Wall
   {
      private int X;
      private int Y;
      private Storage[] storage = new Storage[1];
      private char Direction;
   
       public Wall(int x, int y, char d)
      {
         X=x;
         Y=y;
         Direction=d;
         setStorage(X, Y);
      }
      
       public Wall(int x, int y, char d, String n)
      {
         this(x, y, d);
         setStorage(X, Y, n);
      }
      
       public Storage getStorage()
      {
         return storage[0];
      }
      
       public char getSide()
      {
         return Direction;
      }
      
       public static int randomize()
      {
         return (int)(Math.random()*5)+1;
      }
      
       public void setStorage(int r, int c)
      {
         int temp = randomize();
      
         switch(temp)
         {
            case 1:
               storage[0] = new Armoir(r, c);
               break;
            case 2:
               storage[0] = new Crate(r, c);
               break;
            case 3:
               storage[0] = new Dresser(r, c);
               break;
            case 4:
               storage[0] = new Desk(r, c);
               break;
            case 5:
               storage[0] = new JewelryBox(r, c);
               break;
         }
      }
      
       public void setStorage(int r, int c, String n)
      {
         if(n.equals("Armoir"))
            storage[0] = new Armoir(r, c);
         else
            if(n.equals("Crate"))
               storage[0] = new Crate(r, c);
            else
               if(n.equals("Dresser"))
                  storage[0] = new Dresser(r, c);
               else
                  if(n.equals("Desk"))
                     storage[0] = new Desk(r, c);
                  else
                     if(n.equals("Jewelry Box"))
                        storage[0] = new JewelryBox(r, c);
      }
   }