package kakkoiichris.nazonoshiro.castle.puzzle;//Christian Alexander, 5/12/11, Pd. 6

   import java.util.*;

    public class Seihoukei extends Puzzle
   {   
      public char[][] board = new char[6][6];
      public char[][] key = new char[6][6];
      public int r, c, t;
      public String choice = new String();;
      public char temp;
      public Scanner input = new Scanner(System.in);
   	 
       public Seihoukei()
      {
         super("Seihoukei");
      }
   
       public void shiftUp(int c)
      {
         temp = board[0][c];
         for(int i=0; i<5; i++)
            board[i][c] = board[i+1][c];
         board[5][c] = temp;
      }
   
       public void shiftDown(int c)
      {
         temp = board[5][c];
         for(int i=5; i>0; i--)
            board[i][c] = board[i-1][c];
         board[0][c] = temp;
      }
   
       public void shiftLeft(int r)
      {
         temp = board[r][0];
         for(int i=0; i<5; i++)
            board[r][i] = board[r][i+1];
         board[r][5] = temp;
      }
   
       public void shiftRight(int r)
      {
         temp = board[r][5];
         for(int i=5; i>0; i--)
            board[r][i] = board[r][i-1];
         board[r][0] = temp;
      }
         
       public void setUp()
      {
         for(r=0; r<board.length; r++)
         {
            for(c=0; c<board.length; c++)
            {
               board[r][c] = '~';
            }
         }
         
         for(int i=0; i<12; i++)
         {
            r = (int)(Math.random()*6); 
            c = (int)(Math.random()*6);
         
            if(board[r][c] != '#')
               board[r][c] = '#';
            else
               i--;
         }
         
         for(r=0; r<key.length; r++)
         {
            for(c=0; c<key.length; c++)
            {
               key[r][c] = '~';
            }
         }
      }
   	
       public void show(char[][] board)
      {
         System.out.println("  0 1 2 3 4 5");
         for(r=0; r<board.length; r++)
         {
            System.out.print(r +" ");
            for(c=0; c<board.length; c++)
            {
               System.out.print(board[r][c]+" ");
            }
            System.out.println();
         }
      }
      
       public void setKey()
      {
         int k=(int)(Math.random()*20);
      
         switch(k)
         {
            case 0:
               key[0][0] = '#';
               key[0][5] = '#';
               key[1][1] = '#';
               key[1][4] = '#';
               key[2][2] = '#';
               key[2][3] = '#';
               key[3][3] = '#';
               key[3][2] = '#';
               key[4][4] = '#';
               key[4][1] = '#';
               key[5][5] = '#';
               key[5][0] = '#';
               break;
            case 1:
               key[0][0] = '#';
               key[0][4] = '#';
               key[0][5] = '#';
               key[1][0] = '#';
               key[1][3] = '#';
               key[2][1] = '#';
               key[3][4] = '#';
               key[4][2] = '#';
               key[4][5] = '#';
               key[5][0] = '#';
               key[5][1] = '#';
               key[5][5] = '#';
               break;
            case 2:
               key[0][0] = '#';
               key[0][1] = '#';
               key[1][0] = '#';
               key[1][3] = '#';
               key[2][3] = '#';
               key[2][4] = '#';
               key[3][1] = '#';
               key[3][2] = '#';
               key[4][2] = '#';
               key[4][5] = '#';
               key[5][4] = '#';
               key[5][5] = '#';
               break;
            case 3:
               key[0][0] = '#';
               key[0][5] = '#';
               key[1][2] = '#';
               key[1][3] = '#';
               key[2][1] = '#';
               key[2][4] = '#';
               key[3][1] = '#';
               key[3][4] = '#';
               key[4][2] = '#';
               key[4][3] = '#';
               key[5][0] = '#';
               key[5][5] = '#';
               break;
            case 4:
               key[0][2] = '#';
               key[0][3] = '#';
               key[1][1] = '#';
               key[1][4] = '#';
               key[2][0] = '#';
               key[2][5] = '#';
               key[3][0] = '#';
               key[3][5] = '#';
               key[4][1] = '#';
               key[4][4] = '#';
               key[5][2] = '#';
               key[5][3] = '#';
               break;
            case 5:
               key[0][0] = '#';
               key[0][1] = '#';
               key[1][4] = '#';
               key[1][5] = '#';
               key[2][0] = '#';
               key[2][1] = '#';
               key[3][4] = '#';
               key[3][5] = '#';
               key[4][0] = '#';
               key[4][1] = '#';
               key[5][4] = '#';
               key[5][5] = '#';
               break;
            case 6:
               key[0][0] = '#';
               key[0][5] = '#';
               key[1][0] = '#';
               key[1][2] = '#';
               key[1][3] = '#';
               key[1][5] = '#';
               key[4][0] = '#';
               key[4][2] = '#';
               key[4][3] = '#';
               key[4][5] = '#';
               key[5][0] = '#';
               key[5][5] = '#';
               break;
            case 7:
               key[1][0] = '#';
               key[1][2] = '#';
               key[1][3] = '#';
               key[2][0] = '#';
               key[2][2] = '#';
               key[2][4] = '#';
               key[3][1] = '#';
               key[3][3] = '#';
               key[3][5] = '#';
               key[4][2] = '#';
               key[4][3] = '#';
               key[4][5] = '#';
               break;
            case 8:
               key[0][0] = '#';
               key[0][4] = '#';
               key[1][1] = '#';
               key[1][3] = '#';
               key[2][2] = '#';
               key[2][5] = '#';
               key[3][0] = '#';
               key[3][3] = '#';
               key[4][2] = '#';
               key[4][4] = '#';
               key[5][1] = '#';
               key[5][5] = '#';
               break;
            case 9:
               key[0][0] = '#';
               key[0][3] = '#';
               key[0][5] = '#';
               key[1][1] = '#';
               key[1][4] = '#';
               key[2][0] = '#';
               key[3][5] = '#';
               key[4][1] = '#';
               key[4][4] = '#';
               key[5][0] = '#';
               key[5][2] = '#';
               key[5][5] = '#';
               break;
            case 10:
               key[0][1] = '#';
               key[0][5] = '#';
               key[1][0] = '#';
               key[1][3] = '#';
               key[1][4] = '#';
               key[2][0] = '#';
               key[3][5] = '#';
               key[4][1] = '#';
               key[4][2] = '#';
               key[4][5] = '#';
               key[5][0] = '#';
               key[5][4] = '#';
               break;
            case 11:
               key[0][1] = '#';
               key[0][3] = '#';
               key[1][5] = '#';
               key[2][0] = '#';
               key[2][2] = '#';
               key[2][3] = '#';
               key[3][2] = '#';
               key[3][3] = '#';
               key[3][5] = '#';
               key[4][0] = '#';
               key[5][2] = '#';
               key[5][4] = '#';
               break;
            case 12:
               key[0][1] = '#';
               key[0][4] = '#';
               key[1][0] = '#';
               key[1][1] = '#';
               key[1][4] = '#';
               key[1][5] = '#';
               key[4][0] = '#';
               key[4][1] = '#';
               key[4][4] = '#';
               key[4][5] = '#';
               key[5][1] = '#';
               key[5][4] = '#';
               break;
            case 13:
               key[0][1] = '#';
               key[0][3] = '#';
               key[1][0] = '#';
               key[2][1] = '#';
               key[2][3] = '#';
               key[2][5] = '#';
               key[3][0] = '#';
               key[3][2] = '#';
               key[3][4] = '#';
               key[4][5] = '#';
               key[5][2] = '#';
               key[5][4] = '#';
               break;
            case 14:
               key[0][0] = '#';
               key[0][3] = '#';
               key[1][1] = '#';
               key[1][4] = '#';
               key[2][2] = '#';
               key[2][5] = '#';
               key[3][0] = '#';
               key[3][3] = '#';
               key[4][1] = '#';
               key[4][4] = '#';
               key[5][2] = '#';
               key[5][5] = '#';
               break;
            case 15:
               key[0][0] = '#';
               key[0][2] = '#';
               key[0][5] = '#';
               key[1][3] = '#';
               key[2][1] = '#';
               key[2][5] = '#';
               key[3][0] = '#';
               key[3][4] = '#';
               key[4][2] = '#';
               key[5][0] = '#';
               key[5][3] = '#';
               key[5][5] = '#';
               break;
            case 16:
               key[0][0] = '#';
               key[0][3] = '#';
               key[1][0] = '#';
               key[1][4] = '#';
               key[2][0] = '#';
               key[2][1] = '#';
               key[3][4] = '#';
               key[3][5] = '#';
               key[4][1] = '#';
               key[4][5] = '#';
               key[5][2] = '#';
               key[5][5] = '#';
               break;
            case 17:
               key[0][0] = '#';
               key[0][5] = '#';
               key[1][2] = '#';
               key[1][3] = '#';
               key[2][1] = '#';
               key[2][4] = '#';
               key[3][0] = '#';
               key[3][5] = '#';
               key[4][2] = '#';
               key[4][3] = '#';
               key[5][1] = '#';
               key[5][4] = '#';
               break;
            case 18:
               key[0][0] = '#';
               key[0][2] = '#';
               key[1][3] = '#';
               key[1][5] = '#';
               key[2][1] = '#';
               key[2][3] = '#';
               key[3][2] = '#';
               key[3][4] = '#';
               key[4][0] = '#';
               key[4][2] = '#';
               key[5][3] = '#';
               key[5][5] = '#';
               break;
            case 19:
               key[0][0] = '#';
               key[0][5] = '#';
               key[1][1] = '#';
               key[1][4] = '#';
               key[2][1] = '#';
               key[2][4] = '#';
               key[3][0] = '#';
               key[3][5] = '#';
               key[4][0] = '#';
               key[4][5] = '#';
               key[5][1] = '#';
               key[5][4] = '#';
               break;
         }
      }
   	
       public boolean win()
      {
         int count=0;
         for(r=0; r<board.length; r++)
         {
            for(c=0; c<board.length; c++)
            {
               if(board[r][c] == key[r][c])
                  count++;
            }
         }
         if(count==36)
            return true;
         return false;
      }
   
       public void play()
      {
         System.out.println("<[Seihoukei]>");
         System.out.println();
         setUp();
         setKey();
         System.out.println("Solve for this pattern:");
         System.out.println();
         while(win()==false)
         {
            System.out.println("  [Solution]");
            show(key);
            System.out.println();
            show(board);
            System.out.println();
            System.out.println("Movement:");
            System.out.println("(Up/ Down/ Left/ Right)");
            choice = input.next();
            choice = choice.toLowerCase();
            
            if(choice.equals("up"))
            {
               System.out.print("Column: ");
               t = input.nextInt();
               while(t>5 || t<0)
               {
                  System.out.println("Invalid: enter again");
                  System.out.print("Column: ");
                  t = input.nextInt();
               }
               shiftUp(t);
            }
            else
               if(choice.equals("down"))
               {
                  System.out.print("Column: ");
                  t = input.nextInt();
                  while(t>5 || t<0)
                  {
                     System.out.println("Invalid: enter again");
                     System.out.print("Column: ");
                     t = input.nextInt();
                  }
                  shiftDown(t);
               }
               else
                  if(choice.equals("left"))
                  {
                     System.out.print("Row: ");
                     t = input.nextInt();
                     while(t>5 || t<0)
                     {
                        System.out.println("Invalid: enter again");
                        System.out.print("Row: ");
                        t = input.nextInt();
                     }
                     shiftLeft(t);
                  }
                  else
                     if(choice.equals("right"))
                     {
                        System.out.print("Row: ");
                        t = input.nextInt();
                        while(t>5 || t<0)
                        {
                           System.out.println("Invalid: enter again");
                           System.out.print("Row: ");
                           t = input.nextInt();
                        }
                        shiftRight(t);
                     }
                     else
                     {
                        System.out.println("Goodbye.");
                        break;
                     }
         }
         victory();
      }	
   }