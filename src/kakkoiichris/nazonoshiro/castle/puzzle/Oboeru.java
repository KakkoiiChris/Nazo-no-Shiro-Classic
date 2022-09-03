package kakkoiichris.nazonoshiro.castle.puzzle;//Christian Alexander, 5/12/11, Pd. 6

   import java.util.*;

    public class Oboeru extends Puzzle
   {	
      public String[][] board = new String[5][6]; 
      public String[][] key = new String[5][6];
      public int r, c, a, b, x, y, i, count=0;
      public Scanner input = new Scanner(System.in);
   
       public Oboeru()
      {
         super("Oboeru");
      }
   	
       public void setUp()
      {
         for(int r=0; r<5; r++)
         {
            for(int c=0; c<6; c++)
            {
               board[r][c] = "~";
            }
         }
         
         for(int r=0; r<5; r++)
         {
            for(int c=0; c<6; c++)
            {
               key[r][c] = "~";
            }
         }
         
         for(int i=0; i<2; i++)
         {
            r = (int)(Math.random()*5); 
            c = (int)(Math.random()*6);
         
            if(key[r][c].equals("~"))
            {
               key[r][c] = "A";
               board[r][c] = "A";
            }
            else
               i--;
         }
         for(int i=0; i<2; i++)
         {
            r = (int)(Math.random()*5); 
            c = (int)(Math.random()*6);
         
            if(key[r][c].equals("~"))
            {
               key[r][c] = "B";
               board[r][c] = "B";
            }
            else
               i--;
         }
         for(int i=0; i<2; i++)
         {
            r = (int)(Math.random()*5); 
            c = (int)(Math.random()*6);
         
            if(key[r][c].equals("~"))
            {
               key[r][c] = "C";
               board[r][c] = "C";
            }
            else
               i--;
         }
         for(int i=0; i<2; i++)
         {
            r = (int)(Math.random()*5); 
            c = (int)(Math.random()*6);
         
            if(key[r][c].equals("~"))
            {
               key[r][c] = "D";
               board[r][c] = "D";
            }
            else
               i--;
         }
         for(int i=0; i<2; i++)
         {
            r = (int)(Math.random()*5); 
            c = (int)(Math.random()*6);
         
            if(key[r][c].equals("~"))
            {
               key[r][c] = "E";
               board[r][c] = "E";
            }
            else
               i--;
         }
         for(int i=0; i<2; i++)
         {
            r = (int)(Math.random()*5); 
            c = (int)(Math.random()*6);
         
            if(key[r][c].equals("~"))
            {
               key[r][c] = "F";
               board[r][c] = "F";
            }
            else
               i--;
         }
         for(int i=0; i<2; i++)
         {
            r = (int)(Math.random()*5); 
            c = (int)(Math.random()*6);
         
            if(key[r][c].equals("~"))
            {
               key[r][c] = "G";
               board[r][c] = "G";
            }
            else
               i--;
         }
         for(int i=0; i<2; i++)
         {
            r = (int)(Math.random()*5); 
            c = (int)(Math.random()*6);
         
            if(key[r][c].equals("~"))
            {
               key[r][c] = "H";
               board[r][c] = "H";
            }
            else
               i--;
         }
         for(int i=0; i<2; i++)
         {
            r = (int)(Math.random()*5); 
            c = (int)(Math.random()*6);
         
            if(key[r][c].equals("~"))
            {
               key[r][c] = "I";
               board[r][c] = "I";
            }
            else
               i--;
         }
         for(int i=0; i<2; i++)
         {
            r = (int)(Math.random()*5); 
            c = (int)(Math.random()*6);
         
            if(key[r][c].equals("~"))
            {
               key[r][c] = "J";
               board[r][c] = "J";
            }
            else
               i--;
         }
         for(int i=0; i<2; i++)
         {
            r = (int)(Math.random()*5); 
            c = (int)(Math.random()*6);
         
            if(key[r][c].equals("~"))
            {
               key[r][c] = "K";
               board[r][c] = "K";
            }
            else
               i--;
         }
         for(int i=0; i<2; i++)
         {
            r = (int)(Math.random()*5); 
            c = (int)(Math.random()*6);
         
            if(key[r][c].equals("~"))
            {
               key[r][c] = "L";
               board[r][c] = "L";
            }
            else
               i--;
         }
         for(int i=0; i<2; i++)
         {
            r = (int)(Math.random()*5); 
            c = (int)(Math.random()*6);
         
            if(key[r][c].equals("~"))
            {
               key[r][c] = "M";
               board[r][c] = "M";
            }
            else
               i--;
         }
         for(int i=0; i<2; i++)
         {
            r = (int)(Math.random()*5); 
            c = (int)(Math.random()*6);
         
            if(key[r][c].equals("~"))
            {
               key[r][c] = "N";
               board[r][c] = "N";
            }
            else
               i--;
         }
         for(int i=0; i<2; i++)
         {
            r = (int)(Math.random()*5); 
            c = (int)(Math.random()*6);
         
            if(key[r][c].equals("~"))
            {
               key[r][c] = "O";
               board[r][c] = "O";
            }
            else
               i--;
         }
      }
      
       public void show(String[][] board)
      {
         System.out.println("   0   1   2   3   4   5");
         for(r=0; r<5; r++)
         {
            System.out.print(r +" ");
            for(c=0; c<6; c++)
            {
               if(!board[r][c].equals("~"))
                  System.out.print("[~] ");
               else
                  if(board[r][c].equals("~"))
                     System.out.print("["+key[r][c]+"] ");
            }
            System.out.println();
         }
      }
         
       public void reset(int x, int y, int a, int b)
      {
         board[x][y] = key[x][y]; 
         board[a][b] = key[a][b];
      }
   	
       public void play()
      {
         setUp();
         show(board);
         while(count < 15)
         {
            System.out.println("   0   1   2   3   4   5");
            for(r=0; r<5; r++)
            {
               System.out.print(r +" ");
               for(c=0; c<6; c++)
               {
                  System.out.print("["+key[r][c]+"] ");
               }
               System.out.println();
            }
         
            System.out.println("Flip two cards:");
            System.out.print("Row 1:");
            x = input.nextInt();
            System.out.println();
            System.out.print("Column 1:");
            y = input.nextInt();
            System.out.println();
            board[x][y] = "~";
            show(board);
            System.out.print("Row 2:");
            a = input.nextInt();
            System.out.println();
            System.out.print("Column 2:");
            b = input.nextInt();
            System.out.println();
            board[a][b] = "~";
            show(board);
         
            if(key[x][y].equals(key[a][b]))
            {
               System.out.println("That's a match.");
               count++;
            }
            else
            {
               System.out.println("Not a match.");
               reset(x, y, a, b);   
            }
            System.out.println();
         }
         victory();
      }
   }