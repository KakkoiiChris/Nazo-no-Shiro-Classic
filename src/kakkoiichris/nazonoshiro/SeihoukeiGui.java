package kakkoiichris.nazonoshiro;//Christian Alexander, 5/12/11, Pd. 6

   import java.util.*;
   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;

    public class SeihoukeiGui extends JFrame
   {   
      public JButton ul = new JButton(),
                     ur = new JButton(),
                     dl = new JButton(),
                     dr = new JButton();
      public JButton[][] board = new JButton[6][6],
                         upArrow = new JButton[1][6],
      						 downArrow = new JButton[1][6],
      						 leftArrow = new JButton[6][1],
      						 rightArrow = new JButton[6][1];
      public Dimension d = new Dimension(50, 50);
      public JPanel up = new JPanel(),
                    down = new JPanel(), 
      				  left = new JPanel(), 
      				  right = new JPanel(), 
      				  center = new JPanel();
      public static Color[][] key = new Color[6][6],
                              grid = new Color[6][6];
      public static int r, c, t;
      public String choice = new String();;
      public Color temp;
      public Scanner input = new Scanner(System.in);
      public boolean keyToggled = false;
   	 
       public SeihoukeiGui()
      {
         super("kakkoiichris.nazonoshiro.castle.puzzle.Seihoukei");
         setLayout(new FlowLayout());
         setSize(438, 458);
         
         up.setLayout(new GridLayout(1,6));
         up.setSize(120, 20);
         down.setLayout(new GridLayout(1,6));
         down.setSize(120, 20);
         left.setLayout(new GridLayout(6,1));
         left.setSize(20, 120);
         right.setLayout(new GridLayout(6,1));
         right.setSize(20, 120);
         center.setLayout(new GridLayout(6,6));
         center.setSize(120, 120);
         
         setUp();
         
         ArrowHandler ah = new ArrowHandler();
         
         for(int i=0; i<6; i++)
         {
            upArrow[0][i] = new JButton("^");
            upArrow[0][i].setPreferredSize(d);
            upArrow[0][i].addActionListener(ah);
            upArrow[0][i].setBackground(Color.WHITE);
            up.add(upArrow[0][i]);
            downArrow[0][i] = new JButton("v");
            downArrow[0][i].setPreferredSize(d);
            downArrow[0][i].addActionListener(ah);
            downArrow[0][i].setBackground(Color.WHITE);
            down.add(downArrow[0][i]);
            leftArrow[i][0] = new JButton("<");
            leftArrow[i][0].setPreferredSize(d);
            leftArrow[i][0].addActionListener(ah);
            leftArrow[i][0].setBackground(Color.WHITE);
            left.add(leftArrow[i][0]);
            rightArrow[i][0] = new JButton(">");
            rightArrow[i][0].setPreferredSize(d);
            rightArrow[i][0].addActionListener(ah);
            rightArrow[i][0].setBackground(Color.WHITE);
            right.add(rightArrow[i][0]);
         }
         
         CornerHandler ch = new CornerHandler();
         
         ul.setPreferredSize(d);
         ul.setBackground(Color.GRAY);
         ul.addActionListener(ch);
         ur.setPreferredSize(d);
         ur.setBackground(Color.GRAY);
         ur.addActionListener(ch);
         dl.setPreferredSize(d);
         dl.setBackground(Color.GRAY);
         dl.addActionListener(ch);
         dr.setPreferredSize(d);
         dr.setBackground(Color.GRAY);
         dr.addActionListener(ch);
         
         add(ul);
         add(up);
         add(ur);
         add(left);
         add(center);
         add(right);
         add(dl);
         add(down);
         add(dr);
         
         setVisible(true);
      }
   
       public void shiftUp(int c)
      {
         temp = board[0][c].getBackground();
         for(int i=0; i<5; i++)
            board[i][c].setBackground(board[i+1][c].getBackground());
         board[5][c].setBackground(temp);
      }
   
       public void shiftDown(int c)
      {
         temp = board[5][c].getBackground();
         for(int i=5; i>0; i--)
            board[i][c].setBackground(board[i-1][c].getBackground());
         board[0][c].setBackground(temp);
      }
   
       public void shiftLeft(int r)
      {
         temp = board[r][0].getBackground();
         for(int i=0; i<5; i++)
            board[r][i].setBackground(board[r][i+1].getBackground());
         board[r][5].setBackground(temp);
      }
   
       public void shiftRight(int r)
      {
         temp = board[r][5].getBackground();
         for(int i=5; i>0; i--)
            board[r][i].setBackground(board[r][i-1].getBackground());
         board[r][0].setBackground(temp);
      }
         
       public class CornerHandler implements ActionListener
      {
          public void actionPerformed(ActionEvent e)
         {
            toggleAnswerKey();
         }
      }
   		
       public class ArrowHandler implements ActionListener
      {
          public void actionPerformed(ActionEvent e)
         {
            for(int i=0; i<6; i++)
            {
               if(e.getSource() == upArrow[0][i])
                  shiftUp(i);
               else
                  if(e.getSource() == downArrow[0][i])
                     shiftDown(i);
                  else
                     if(e.getSource() == leftArrow[i][0])
                        shiftLeft(i);
                     else
                        if(e.getSource() == rightArrow[i][0])
                           shiftRight(i);
            }
            
            if(detectWin() == true)
            {
               int delay = 0;
               int period = 100; 
               java.util.Timer timer = new java.util.Timer(); 
               timer.scheduleAtFixedRate(
                      new TimerTask() 
                     {
                        int iter = 0;
                        
                         public void run() 
                        {
                           if(ul.getBackground() == Color.GRAY)
                           {
                              ul.setBackground(Color.ORANGE);
                              ur.setBackground(Color.ORANGE);
                              dl.setBackground(Color.ORANGE);
                              dr.setBackground(Color.ORANGE);
                           }
                           else
                           {
                              ul.setBackground(Color.GRAY);
                              ur.setBackground(Color.GRAY);
                              dl.setBackground(Color.GRAY);
                              dr.setBackground(Color.GRAY);
                           }
                           
                           if(iter > 16)
                              setVisible(false);
                           else
                              iter++;
                        } 
                     }
                  , delay, period);
            }
         }
      }
    
       public void setUp()
      {
         for(r=0; r<board.length; r++)
         {
            for(c=0; c<board.length; c++)
            {
               board[r][c] = new JButton();
               board[r][c].setText(" ");
               board[r][c].setPreferredSize(d);
               board[r][c].setBackground(Color.WHITE);
               center.add(board[r][c]);
            }
         }
         
         for(int i=0; i<12; i++)
         {
            r = (int)(Math.random()*6); 
            c = (int)(Math.random()*6);
         
            if(board[r][c].getBackground() != Color.BLUE)
               board[r][c].setBackground(Color.BLUE);
            else
               i--;
         }
         
         for(r=0; r<key.length; r++)
         {
            for(c=0; c<key.length; c++)
            {
               key[r][c] = Color.WHITE;
            }
         }
         
         setKey();
      }
   	   
       public void toggleAnswerKey()
      {
         keyToggled = !keyToggled;
      
         if(keyToggled == true)
         {
            for(int i=0; i<6; i++)
            {
               upArrow[0][i].setEnabled(false);
               downArrow[0][i].setEnabled(false);
               leftArrow[i][0].setEnabled(false);
               rightArrow[i][0].setEnabled(false);
            }
            
            grid = saveBoard();
            
            showKey();
         }
         else
         {
            for(int i=0; i<6; i++)
            {
               upArrow[0][i].setEnabled(true);
               downArrow[0][i].setEnabled(true);
               leftArrow[i][0].setEnabled(true);
               rightArrow[i][0].setEnabled(true);
            }
            
            showBoard();
         }
      }
      
       public Color[][] saveBoard()
      {
         Color[][] temp = new Color[6][6];
      
         for(r=0; r<6; r++)
         {
            for(c=0; c<6; c++)
            {
               temp[r][c] = board[r][c].getBackground();
            }
         }
         
         return temp;
      }
      
       public void showKey()
      {
         for(r=0; r<6; r++)
         {
            for(c=0; c<6; c++)
            {
               board[r][c].setBackground(key[r][c]);
            }
         }
      }
   	
       public void showBoard()
      {
         for(r=0; r<6; r++)
         {
            for(c=0; c<6; c++)
            {
               board[r][c].setBackground(grid[r][c]);
            }
         }
      }
      
       public void setKey()
      {
         int k=(int)(Math.random()*20);
      
         switch(k)
         {
            case 0:
               key[0][0] = Color.BLUE;
               key[0][5] = Color.BLUE;
               key[1][1] = Color.BLUE;
               key[1][4] = Color.BLUE;
               key[2][2] = Color.BLUE;
               key[2][3] = Color.BLUE;
               key[3][3] = Color.BLUE;
               key[3][2] = Color.BLUE;
               key[4][4] = Color.BLUE;
               key[4][1] = Color.BLUE;
               key[5][5] = Color.BLUE;
               key[5][0] = Color.BLUE;
               break;
            case 1:
               key[0][0] = Color.BLUE;
               key[0][4] = Color.BLUE;
               key[0][5] = Color.BLUE;
               key[1][0] = Color.BLUE;
               key[1][3] = Color.BLUE;
               key[2][1] = Color.BLUE;
               key[3][4] = Color.BLUE;
               key[4][2] = Color.BLUE;
               key[4][5] = Color.BLUE;
               key[5][0] = Color.BLUE;
               key[5][1] = Color.BLUE;
               key[5][5] = Color.BLUE;
               break;
            case 2:
               key[0][0] = Color.BLUE;
               key[0][1] = Color.BLUE;
               key[1][0] = Color.BLUE;
               key[1][3] = Color.BLUE;
               key[2][3] = Color.BLUE;
               key[2][4] = Color.BLUE;
               key[3][1] = Color.BLUE;
               key[3][2] = Color.BLUE;
               key[4][2] = Color.BLUE;
               key[4][5] = Color.BLUE;
               key[5][4] = Color.BLUE;
               key[5][5] = Color.BLUE;
               break;
            case 3:
               key[0][0] = Color.BLUE;
               key[0][5] = Color.BLUE;
               key[1][2] = Color.BLUE;
               key[1][3] = Color.BLUE;
               key[2][1] = Color.BLUE;
               key[2][4] = Color.BLUE;
               key[3][1] = Color.BLUE;
               key[3][4] = Color.BLUE;
               key[4][2] = Color.BLUE;
               key[4][3] = Color.BLUE;
               key[5][0] = Color.BLUE;
               key[5][5] = Color.BLUE;
               break;
            case 4:
               key[0][2] = Color.BLUE;
               key[0][3] = Color.BLUE;
               key[1][1] = Color.BLUE;
               key[1][4] = Color.BLUE;
               key[2][0] = Color.BLUE;
               key[2][5] = Color.BLUE;
               key[3][0] = Color.BLUE;
               key[3][5] = Color.BLUE;
               key[4][1] = Color.BLUE;
               key[4][4] = Color.BLUE;
               key[5][2] = Color.BLUE;
               key[5][3] = Color.BLUE;
               break;
            case 5:
               key[0][0] = Color.BLUE;
               key[0][1] = Color.BLUE;
               key[1][4] = Color.BLUE;
               key[1][5] = Color.BLUE;
               key[2][0] = Color.BLUE;
               key[2][1] = Color.BLUE;
               key[3][4] = Color.BLUE;
               key[3][5] = Color.BLUE;
               key[4][0] = Color.BLUE;
               key[4][1] = Color.BLUE;
               key[5][4] = Color.BLUE;
               key[5][5] = Color.BLUE;
               break;
            case 6:
               key[0][0] = Color.BLUE;
               key[0][5] = Color.BLUE;
               key[1][0] = Color.BLUE;
               key[1][2] = Color.BLUE;
               key[1][3] = Color.BLUE;
               key[1][5] = Color.BLUE;
               key[4][0] = Color.BLUE;
               key[4][2] = Color.BLUE;
               key[4][3] = Color.BLUE;
               key[4][5] = Color.BLUE;
               key[5][0] = Color.BLUE;
               key[5][5] = Color.BLUE;
               break;
            case 7:
               key[1][0] = Color.BLUE;
               key[1][2] = Color.BLUE;
               key[1][3] = Color.BLUE;
               key[2][0] = Color.BLUE;
               key[2][2] = Color.BLUE;
               key[2][4] = Color.BLUE;
               key[3][1] = Color.BLUE;
               key[3][3] = Color.BLUE;
               key[3][5] = Color.BLUE;
               key[4][2] = Color.BLUE;
               key[4][3] = Color.BLUE;
               key[4][5] = Color.BLUE;
               break;
            case 8:
               key[0][0] = Color.BLUE;
               key[0][4] = Color.BLUE;
               key[1][1] = Color.BLUE;
               key[1][3] = Color.BLUE;
               key[2][2] = Color.BLUE;
               key[2][5] = Color.BLUE;
               key[3][0] = Color.BLUE;
               key[3][3] = Color.BLUE;
               key[4][2] = Color.BLUE;
               key[4][4] = Color.BLUE;
               key[5][1] = Color.BLUE;
               key[5][5] = Color.BLUE;
               break;
            case 9:
               key[0][0] = Color.BLUE;
               key[0][3] = Color.BLUE;
               key[0][5] = Color.BLUE;
               key[1][1] = Color.BLUE;
               key[1][4] = Color.BLUE;
               key[2][0] = Color.BLUE;
               key[3][5] = Color.BLUE;
               key[4][1] = Color.BLUE;
               key[4][4] = Color.BLUE;
               key[5][0] = Color.BLUE;
               key[5][2] = Color.BLUE;
               key[5][5] = Color.BLUE;
               break;
            case 10:
               key[0][1] = Color.BLUE;
               key[0][5] = Color.BLUE;
               key[1][0] = Color.BLUE;
               key[1][3] = Color.BLUE;
               key[1][4] = Color.BLUE;
               key[2][0] = Color.BLUE;
               key[3][5] = Color.BLUE;
               key[4][1] = Color.BLUE;
               key[4][2] = Color.BLUE;
               key[4][5] = Color.BLUE;
               key[5][0] = Color.BLUE;
               key[5][4] = Color.BLUE;
               break;
            case 11:
               key[0][1] = Color.BLUE;
               key[0][3] = Color.BLUE;
               key[1][5] = Color.BLUE;
               key[2][0] = Color.BLUE;
               key[2][2] = Color.BLUE;
               key[2][3] = Color.BLUE;
               key[3][2] = Color.BLUE;
               key[3][3] = Color.BLUE;
               key[3][5] = Color.BLUE;
               key[4][0] = Color.BLUE;
               key[5][2] = Color.BLUE;
               key[5][4] = Color.BLUE;
               break;
            case 12:
               key[0][1] = Color.BLUE;
               key[0][4] = Color.BLUE;
               key[1][0] = Color.BLUE;
               key[1][1] = Color.BLUE;
               key[1][4] = Color.BLUE;
               key[1][5] = Color.BLUE;
               key[4][0] = Color.BLUE;
               key[4][1] = Color.BLUE;
               key[4][4] = Color.BLUE;
               key[4][5] = Color.BLUE;
               key[5][1] = Color.BLUE;
               key[5][4] = Color.BLUE;
               break;
            case 13:
               key[0][1] = Color.BLUE;
               key[0][3] = Color.BLUE;
               key[1][0] = Color.BLUE;
               key[2][1] = Color.BLUE;
               key[2][3] = Color.BLUE;
               key[2][5] = Color.BLUE;
               key[3][0] = Color.BLUE;
               key[3][2] = Color.BLUE;
               key[3][4] = Color.BLUE;
               key[4][5] = Color.BLUE;
               key[5][2] = Color.BLUE;
               key[5][4] = Color.BLUE;
               break;
            case 14:
               key[0][0] = Color.BLUE;
               key[0][3] = Color.BLUE;
               key[1][1] = Color.BLUE;
               key[1][4] = Color.BLUE;
               key[2][2] = Color.BLUE;
               key[2][5] = Color.BLUE;
               key[3][0] = Color.BLUE;
               key[3][3] = Color.BLUE;
               key[4][1] = Color.BLUE;
               key[4][4] = Color.BLUE;
               key[5][2] = Color.BLUE;
               key[5][5] = Color.BLUE;
               break;
            case 15:
               key[0][0] = Color.BLUE;
               key[0][2] = Color.BLUE;
               key[0][5] = Color.BLUE;
               key[1][3] = Color.BLUE;
               key[2][1] = Color.BLUE;
               key[2][5] = Color.BLUE;
               key[3][0] = Color.BLUE;
               key[3][4] = Color.BLUE;
               key[4][2] = Color.BLUE;
               key[5][0] = Color.BLUE;
               key[5][3] = Color.BLUE;
               key[5][5] = Color.BLUE;
               break;
            case 16:
               key[0][0] = Color.BLUE;
               key[0][3] = Color.BLUE;
               key[1][0] = Color.BLUE;
               key[1][4] = Color.BLUE;
               key[2][0] = Color.BLUE;
               key[2][1] = Color.BLUE;
               key[3][4] = Color.BLUE;
               key[3][5] = Color.BLUE;
               key[4][1] = Color.BLUE;
               key[4][5] = Color.BLUE;
               key[5][2] = Color.BLUE;
               key[5][5] = Color.BLUE;
               break;
            case 17:
               key[0][0] = Color.BLUE;
               key[0][5] = Color.BLUE;
               key[1][2] = Color.BLUE;
               key[1][3] = Color.BLUE;
               key[2][1] = Color.BLUE;
               key[2][4] = Color.BLUE;
               key[3][0] = Color.BLUE;
               key[3][5] = Color.BLUE;
               key[4][2] = Color.BLUE;
               key[4][3] = Color.BLUE;
               key[5][1] = Color.BLUE;
               key[5][4] = Color.BLUE;
               break;
            case 18:
               key[0][0] = Color.BLUE;
               key[0][2] = Color.BLUE;
               key[1][3] = Color.BLUE;
               key[1][5] = Color.BLUE;
               key[2][1] = Color.BLUE;
               key[2][3] = Color.BLUE;
               key[3][2] = Color.BLUE;
               key[3][4] = Color.BLUE;
               key[4][0] = Color.BLUE;
               key[4][2] = Color.BLUE;
               key[5][3] = Color.BLUE;
               key[5][5] = Color.BLUE;
               break;
            case 19:
               key[0][0] = Color.BLUE;
               key[0][5] = Color.BLUE;
               key[1][1] = Color.BLUE;
               key[1][4] = Color.BLUE;
               key[2][1] = Color.BLUE;
               key[2][4] = Color.BLUE;
               key[3][0] = Color.BLUE;
               key[3][5] = Color.BLUE;
               key[4][0] = Color.BLUE;
               key[4][5] = Color.BLUE;
               key[5][1] = Color.BLUE;
               key[5][4] = Color.BLUE;
               break;
         }
      }
   	
       public boolean detectWin()
      {
         for(r=0; r<board.length; r++)
         {
            for(c=0; c<board.length; c++)
            {
               if(board[r][c].getBackground() != key[r][c])
                  return false;
            }
         }
         return true;
      }
      
       public static void main(String[] args)
      {
         SeihoukeiGui s = new SeihoukeiGui();
      }
   }