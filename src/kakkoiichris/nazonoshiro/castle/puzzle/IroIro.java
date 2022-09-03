package kakkoiichris.nazonoshiro.castle.puzzle;//Christian Alexander, 8/8/11, Pd. 6

   import kakkoiichris.nazonoshiro.castle.puzzle.Puzzle;

   import java.util.*;

    public class IroIro extends Puzzle
   {
      public static Scanner input = new Scanner(System.in);
      public static String again = new String(), temp = new String();
      public static char[][] board = new char[4][4];
      public static char[][] guide = new char[4][4];
      public static int w = 0, l = 0, t = 0;
      public static ArrayList<Character> colors = new ArrayList();
      public static ArrayList<Character> level1 = new ArrayList();
      public static ArrayList<Character> level2 = new ArrayList();
      public static ArrayList<Character> level3 = new ArrayList();
      public static ArrayList<Character> level4 = new ArrayList();
         
       public IroIro()
      {
         super("IroIro");
      }
      
       public void play()
      {
         again = "y";
        
         while(again.equals("y")||again.equals("Y"))
         {
            System.out.println("  <[IROIRO]>  ");
            System.out.println();
            setUp(guide);
         	
            show(board);
            
            System.out.print(" >");
            temp = input.next();
         	
            System.out.println();
            
         	
            
         	
         	
            if(l>0)
            {
               System.out.println("Try again? y/n");
               again=input.next();	
               l=0;
            }
            else
            {
               victory();
               again = "n";
            }
         }
      }	
      
       public void show(char[][] guide)
      {
         for(int r=0; r<4; r++)
         {
            for(int c=0; c<4; c++)
            {
               System.out.print(guide[r][c]+" ");
            }
            System.out.println();
         }
      }
         
       public void setUp(char[][] board)
      {
         colors.add('R');
         colors.add('Y');
         colors.add('G');
         colors.add('B');
         
         for(int i=0; i<4; i++)
         {
            int random =(int)(Math.random()*colors.size());
            level1.add(colors.get(random));
            colors.remove(random);
         }
         
         colors.add('R');
         colors.add('Y');
         colors.add('G');
         colors.add('B');
         
         for(int i=0; i<4; i++)
         {
            int random =(int)(Math.random()*colors.size());
            level2.add(colors.get(random));
            colors.remove(random);
         }
         
         colors.add('R');
         colors.add('Y');
         colors.add('G');
         colors.add('B');
         
         for(int i=0; i<4; i++)
         {
            int random =(int)(Math.random()*colors.size());
            level3.add(colors.get(random));
            colors.remove(random);  
         }
         
         colors.add('R');
         colors.add('Y');
         colors.add('G');
         colors.add('B');
         
         for(int i=0; i<4; i++)
         {
            int random =(int)(Math.random()*colors.size());
            level4.add(colors.get(random));
            colors.remove(random);
         }
         
         for(int i=0; i<4; i++)
            guide[0][i] = level1.get(i);
         for(int i=0; i<4; i++)
            guide[1][i] = level2.get(i);
         for(int i=0; i<4; i++)
            guide[2][i] = level3.get(i);
         for(int i=0; i<4; i++)
            guide[3][i] = level4.get(i);
      } 
   }
