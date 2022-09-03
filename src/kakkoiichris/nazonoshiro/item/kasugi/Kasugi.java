package kakkoiichris.nazonoshiro.item.kasugi;//Christian Alexander, 9/13/2011

   import kakkoiichris.nazonoshiro.fighter.Fighter;

   import java.io.*;
   import java.util.*;

    public abstract class Kasugi
   {
   protected String name = new String();
      protected int duration, magnitude, timer = 0;
      protected boolean forYou = true;
   	
       public Kasugi(String n, int m, int d)
      {
         duration = d;
         magnitude = m;
         name = n;
         timer = duration;
      }
      
       public String toString()
      {
         return name;
      }
      
       public String getName()
      {
         return name;
      }
      
       public int getTimer()
      {
         return timer;
      }
   
      public void setTimer(int timer) {
         this.timer = timer;
      }
   
      public abstract void affect(Fighter any);
       
       public static int getFileSize(String fileName)throws IOException//returns the size of the ".txt" file that it is sent
      {
         Scanner input = new Scanner(new FileReader(fileName));
         int size=0;
         while(input.hasNextLine())
         {
            size++;
            input.nextLine();
         }
         input.close();
         return size;
      }
      
       public static int input(String[] array, String filename) throws Exception
      {
         BufferedReader infile = new BufferedReader(new FileReader(filename));
         int n = 0;
         String s = infile.readLine();
         while(s != null)
         {
            array[n] = s;
            n++;
            s = infile.readLine();
         }
         infile.close();
         return n;
      }
      
       public static void readFile(String[] words, String fileName)throws IOException
      {
         Scanner input = new Scanner(new FileReader(fileName));
         int i=0;
         String line;
         while(input.hasNextLine())
         {
            line=input.nextLine();
            words[i]= line;
            i++;
         }
         input.close();
      }
       
       public boolean getForYou()
      {
         return forYou;
      }
   }
