 //Christian Alexander, 5/12/11, Pd. 6

import java.io.*;
import java.util.*;
import javax.swing.JFrame;

public class nazoNoShiroDriver
{
   public static OptionsMenu aaa = new OptionsMenu();

   public static int R = 1, //the row you are located in
                     C = 2, //the column you are located in
   						F = 0, //the floor you are located in
   					  R1 = 1, //stores previous row for restart checkpoint 
                    C1 = 2, //stores previous column for restart checkpoint
   					  F1 = 0, //stores previous floor for restart checkpoint
   				  count = 0, //used to count the number of keys you have 
   					turn = 1, //determines who's attacking and who's defending in battle
   				 wCount = 0,
   					size = 0; //used when reading in '.txt' files
							
   public static IroIro iroiro = new IroIro();  
	
   public static SaveFileCreator saver = new SaveFileCreator();
	
   public static double speedDiff; //used in run method
   
   public static boolean ran = false, ran1 = false; //keeps track of if you run from a fight
   
   public static String choice = new String(), //used when navigating between rooms
   							action = new String(), //used when fighting
   						selection = new String(), //used in inventory 
   						    fName = new String(), 
   						  mapName = "null";
   
   public static Scanner input = new Scanner(System.in);
   
   public static Enemy[] guards = new Enemy[5];   //collection of all enemies
   public static Self self = new Self();     //this fighter represents you
   public static Ninja ninja = new Ninja();    //enemy #1
   public static Samurai samurai = new Samurai();  //enemy #2
   public static Daimyo daimyo = new Daimyo();   //enemy #3
   public static Shogun shogun = new Shogun();   //enemy #4
   public static Imperial imperial = new Imperial(); //enemy #5
   
   public static ArrayList<Item> allItems = new ArrayList(); //stores all the Items within the Castle
   public static ArrayList<Weapon> weapons = new ArrayList(); //stores all possible weapons
   public static ArrayList<CastleFloor> Castle = new ArrayList();

   public static void main(String[] argv)throws IOException
   {
      mainMenu();
      //storeState();
      choice = "none";
      
      while(!choice.equals("quit"))
      {
         explore(Castle.get(F).getRoom(R, C));
         while(choice.equals("inventory"))
            explore(Castle.get(F).getRoom(R, C));
      }
   }

   public static void setUpNew()throws IOException
   {       
      if(mapName.equals("Original Castle"))
         Castle.add(new OriginalCastle());
      else
         if(mapName.equals("Double Classic"))
         {
            Castle.add(new OriginalCastle());
            Castle.add(new OriginalCastle());
         }
      if(mapName.equals("null"))
         Castle.add(new OriginalCastle());
   	
   
      for(int f=0; f<Castle.size(); f++)
      {
         Castle.get(f).setUpNew();
         for(int r=0; r<Castle.get(f).getXSize(); r++)
         {
            for(int c=0; c<Castle.get(f).getYSize(); c++)
            {
               Castle.get(f).getRoom(r, c).setRoom();
            }
         }
      }
   
      distributeAllItems();
      
      self.keys.add(0);
      self.inventory.add(new Katana());
      self.inventory.add(new Tanto());
      
      guards[0] = new Ninja();
      guards[1] = new Samurai();
      guards[2] = new Daimyo();
      guards[3] = new Shogun();
      guards[4] = new Imperial();	
         
      weapons.add(new Bo());
      weapons.add(new Nunchaku());
      weapons.add(new Shuriken());
      weapons.add(new Tanto());
      weapons.add(new Wakizashi());
      weapons.add(new Katana());	
      
      for(int i=0; i<guards.length; i++)
         guards[i].setDrop(self, weapons, wCount);
         
      introduction();	
   }

   public static void setUpLoad() throws IOException
   { 
      // System.out.print("File Name > ");
      // String fileName = input.nextLine() + ".txt";
      // size = getFileSize(fileName);
      // String[] lines = new String[size];
      // readFile(lines, fileName);
   //    
      // int data = 0;
   //    
      // while(!lines[data].startsWith("#"))
      // {
         // if(lines[data].equals("Original Castle"))
            // Castle.add(new OriginalCastle());
         // data++;
      // }
   //    
      // while(!lines[data].startsWith("S"))
      // {
         // int index = lines[data].indexOf("#");
         // int f = (int)(lines[data].charAt(index + 1))-48;
         // int r = (int)(lines[data].charAt(index + 2))-48;
         // int c = (int)(lines[data].charAt(index + 3))-48;
         // String name = lines[data].substring(index +4);
         // data++;
         // int p = stringToInt(lines[data].substring(0, lines[data].indexOf(",")));
         // int k = stringToInt(lines[data].substring(lines[data].indexOf(",")+1, lines[data].indexOf("'")));
         // int l = stringToInt(lines[data].substring(lines[data].indexOf("'")+1));
         // Castle.get(f).floorPlan[r][c] = new Room(name, p, k, l);
         // data++;
         // while(!lines[data].startsWith("#") && !lines[data].startsWith("S"))
         // {
            // char d = lines[data].charAt(lines[data].indexOf('|')+1);
            // String name2 = lines[data].substring(lines[data].indexOf(':')+1);
            // Castle.get(f).floorPlan[r][c].walls.add(new Wall(r, c, d, name2));
            // data++;
         //    
            // for(int u=0; u<stringToInt(lines[data].substring(lines[data].indexOf('!')+1, lines[data].indexOf('a'))); u++)
            // {
               // Castle.get(f).floorPlan[r][c].walls.get(0).getStorage().addToStored(new Tanto());
            // }
         //    
            // for(int v=0; v<stringToInt(lines[data].substring(lines[data].indexOf('a')+1, lines[data].indexOf('b'))); v++)
            // {
               // Castle.get(f).floorPlan[r][c].walls.get(0).getStorage().addToStored(new Wakizashi());
            // }
         //    
            // for(int w=0; w<stringToInt(lines[data].substring(lines[data].indexOf('b')+1, lines[data].indexOf('c'))); w++)
            // {
               // Castle.get(f).floorPlan[r][c].walls.get(0).getStorage().addToStored(new Katana());
            // }
         //    
            // for(int x=0; x<stringToInt(lines[data].substring(lines[data].indexOf('c')+1, lines[data].indexOf('d'))); x++)
            // {
               // Castle.get(f).floorPlan[r][c].walls.get(0).getStorage().addToStored(new Bo());
            // }
         //    
            // for(int y=0; y<stringToInt(lines[data].substring(lines[data].indexOf('d')+1, lines[data].indexOf('e'))); y++)
            // {
               // Castle.get(f).floorPlan[r][c].walls.get(0).getStorage().addToStored(new Shuriken());
            // }
         //    
            // for(int z=0; z<stringToInt(lines[data].substring(lines[data].indexOf('e')+1)); z++)
            // {
               // Castle.get(f).floorPlan[r][c].walls.get(0).getStorage().addToStored(new Nunchaku());
            // }
         //    
            // data++;
         // 	
            // for(int x=0; x<stringToInt(lines[data].substring(lines[data].indexOf('*')+1, lines[data].indexOf('a'))); x++)
            // {
               // Castle.get(f).floorPlan[r][c].walls.get(0).getStorage().addToStored(new HealthPack("Herb", 3));
            // }
         //    
            // for(int y=0; y<stringToInt(lines[data].substring(lines[data].indexOf('a')+1, lines[data].indexOf('b'))); y++)
            // {
               // Castle.get(f).floorPlan[r][c].walls.get(0).getStorage().addToStored(new HealthPack("Bushel", 5));
            // }
         //    
            // for(int z=0; z<stringToInt(lines[data].substring(lines[data].indexOf('b')+1)); z++)
            // {
               // Castle.get(f).floorPlan[r][c].walls.get(0).getStorage().addToStored(new Coin());
            // }
         //    
            // data +=2;
         //    
         // 	
         // }
      // }
   //    
      // guards[0] = new Ninja();
      // guards[1] = new Samurai();
      // guards[2] = new Daimyo();
      // guards[3] = new Shogun();
      // guards[4] = new Imperial();
   }
   
   public static int stringToInt(String a)
   {
      int temp = 0; 
      for(int i=0; i<a.length(); i++)
         temp += ((int)(a.charAt(i))-48)*(Math.pow(10, a.length()-i-1));
      return temp;
   }
	
   public static void mainMenu() throws IOException
   {
      size = getFileSize("mainMenu1.txt");
      String[] lines = new String[size];
      readFile(lines, "mainMenu1.txt");
      for(int i=0; i<size; i++)
      {
         System.out.println(lines[i]);
      }
      System.out.println();
      
      size = getFileSize("splashText.txt");
      lines = new String[size];
      readFile(lines, "splashText.txt");
            
      System.out.println(lines[(int)(Math.random()*lines.length)].toUpperCase());
      
      size = getFileSize("mainMenu2.txt");
      lines = new String[size];
      readFile(lines, "mainMenu2.txt");
      for(int i=0; i<size; i++)
      {
         System.out.println(lines[i]);
      }
      System.out.println();	
   	
      int a=0;
   	
      while(a == 0)
      {
         System.out.print(" > ");
         String first = input.nextLine();
         if(first.equals("1") || first.startsWith("New"))
         {
            setUpNew();
            a++;
         }
         else
            if(first.equals("2") || first.startsWith("Load"))
            {
               //setUpLoad();
               a++;
            }
            else
               if(first.equals("3") || first.equals("Options"))
                  options();
               else
                  if(first.equals("4") || first.equals("Credits"))
                     credits();
                  else
                  {
                     System.out.println("Thanks for playing!");
                     System.exit(0);
                  }
      }
   }
   
   public static void options()
   {
      aaa.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      aaa.setSize(300, 200);
      aaa.setVisible(true);
      delayOp(1000);
   }
   
   public static void credits() throws IOException
   {
      size = getFileSize("credits.txt");
      String[] a = new String[size];
      readFile(a, "credits.txt");
      for(int i=0; i<size; i++)
      {
         System.out.println(a[i]);
      }
      System.out.println();
   }
	
   public static void introduction() throws IOException
   {
      System.out.println("                        [Nazo No Shiro]");
      System.out.println("                    [XXXXX{================>");
      System.out.println();
      
      size = getFileSize("first1-2-A.txt");
      String[] lines1 = new String[size];
      readFile(lines1, "first1-2-A.txt");
      
      for(int i=0; i<5; i++)
      {
         System.out.println(lines1[i]);
      }
      delay(12);
   	
      for(int i=5; i<11; i++)
      {
         System.out.println(lines1[i]);
      }
      delay(15);
   	
      for(int i=11; i<14; i++)
      {
         System.out.println(lines1[i]);
      }
      delay(6);
   	
      for(int i=14; i<16; i++)
      {
         System.out.println(lines1[i]);
      }
      delay(2);
   	
      for(int i=16; i<22; i++)
      {
         System.out.println(lines1[i]);
      }
      delay(7);
   	
      for(int i=22; i<24; i++)
      {
         System.out.println(lines1[i]);
      }
      delay(4);
   	
      for(int i=24; i<27; i++)
      {
         System.out.println(lines1[i]);
      }
      delay(6);
   	
      System.out.println();
   	
      System.out.print("X ");
      String n = input.nextLine();
      self.setName(n);
      System.out.println();
      
      System.out.print("DoB(MM/DD/YYYY): ");
      String DoB = input.nextLine();
      System.out.println();
   	
      System.out.print("(M/F): ");
      String gen = input.nextLine();
      gen = gen.toLowerCase();
      System.out.println();
   	
      if(gen.equals("m") || gen.startsWith("m"))
         System.out.print("[Salesperson]: Alright, sir! ");
      else
         System.out.print("[Salesperson]: Alright, ma'am! ");
   		
      size = getFileSize("first1-2-B.txt");
      String[] lines2 = new String[size];
      readFile(lines2, "first1-2-B.txt");
      
      for(int i=0; i<2; i++)
      {
         System.out.println(lines2[i]);
      }
      delay(6);
   	
      for(int i=2; i<4; i++)
      {
         System.out.println(lines2[i]);
      }
      delay(2);
   	
      for(int i=4; i<8; i++)
      {
         System.out.println(lines2[i]);
      }
      delay(3);
      
      for(int i=8; i<16; i++)
      {
         System.out.println(lines2[i]);
      }
   	
      if(self.getName().length() < 15)
      {
         int q = 15-self.getName().length();
         System.out.print(lines2[17]+self.getName().toUpperCase());
         for(int i=1; i<q; i++)
         {
            System.out.print(" ");
         }
         System.out.println("         |");
      }
      else
         System.out.println(lines2[17]+self.getName().substring(0,14).toUpperCase()+lines2[18]);
      System.out.println(lines2[19]);
      System.out.println(lines2[20]+DoB+lines2[21]);
      System.out.println(lines2[22]);
      System.out.println(lines2[23]+gen.toUpperCase()+lines2[24]);
      System.out.println(lines2[25]);
      
      delay(8);
      
      for(int i=26; i<28; i++)
      {
         System.out.println(lines2[i]);
      }
      delay(2);
   	
      for(int i=28; i<33; i++)
      {
         System.out.println(lines2[i]);
      }
      delay(11);
   	
      for(int i=33; i<35; i++)
      {
         System.out.println(lines2[i]);
      }
      delay(3);
      System.out.println();
   }

   public static void explore(Room r) throws IOException
   {
      System.out.println(r);
      System.out.println();
      String d = new String();
      //if(alreadyVisited(R, C) == false)
      //{
      //String fName = "first"+R+"-"+C+".txt";
      //}
      //else
      //{
         //String fName = "then"+R+"-"+C+".txt";
      //}
      //size = getFileSize(fName);
      //String[] des = new String[size];
      //readFile(des, fName);
      
      for(int i=0; i<r.getSize(); i++)
      {
         if(r.getWall(i).getSide() == 'N')
            d = "north";
         else
            if(r.getWall(i).getSide() == 'S')
               d = "south";
            else
               if(r.getWall(i).getSide() == 'E')
                  d = "east";
               else
                  d = "west";
         System.out.println("There is a "+r.getWall(i).getStorage().getName()+" to the "+d+".");
      }
      
      int a = r.getFoe();
   	
      if(r.getPuzzle() == 4 && guards[a].getHP() > 0)
      {
         fight(guards[a]);
         if(guards[a].getHP() <= 0)
         {
            System.out.println("You were victorious!");
            System.out.println();
            guards[a].dropItem(self);
            self.keys.add(r.getKey());
            r.setKey(99);
            storeState();
         }
         else
         {
            System.out.println("You lost.");
            resetState();
         }
      }
      else
      {
         System.out.print(" > ");
         choice = input.nextLine().toLowerCase();
      	
         while(isExploreOption(choice) == false)
         {
            System.out.println("\nI don't recognize the phrase '"+choice+"'.");
            System.out.println();
            System.out.print(" > ");
            choice = input.nextLine().toLowerCase();
            System.out.println();
         }
         System.out.println();
         
         if(choice.equals("play puzzle") || choice.equals("play") || choice.equals("solve puzzle") || choice.equals("solve") && Castle.get(F).puzzles[R][C].getWon() == false)
            Castle.get(F).playPuzzle(R, C);
         else
            if(choice.equals("show inventory") || choice.equals("inventory") || choice.equals("view inventory") || choice.equals("e"))
               showInventory();
            else
               if(choice.startsWith("search"))
               {
                  String temp = choice.substring(choice.indexOf(" ")+1);
                  char dir = 'a';
                  for(int i=0; i<r.getSize(); i++)
                  {
                     if(r.getWall(i).getStorage().getName().toLowerCase().equals(temp))
                     {
                        dir = r.getWall(i).getSide();
                     }
                  }
                  r.look(self, self.inventory, dir);
               }
               else
                  if(choice.startsWith("go"))
                  {
                     storeState();
                     String temp = choice.substring(choice.indexOf(" ")+1);
                     move(temp);
                  }
                  else
                     if(choice.equals("w"))
                     {
                        storeState();
                        move("north");
                     }
                     else
                        if(choice.equals("a"))
                        {
                           storeState();
                           move("west");
                        }
                        else
                           if(choice.equals("s"))
                           {
                              storeState();
                              move("south");
                           }
                           else
                              if(choice.equals("d"))
                              {
                                 storeState();
                                 move("east");
                              }
                              else
                                 if(choice.equals("r"))
                                 {
                                    storeState();
                                    move("up");
                                 }
                                 else
                                    if(choice.equals("f"))
                                    {
                                       storeState();
                                       move("down");
                                    }
                                    else
                                       if(choice.equals("save"))
                                       {
                                          saver.openFile();
                                          System.out.print("Saving");
                                          saver.addData(Castle, guards, self, R, C, F, turn);
                                          saver.closeFile();
                                          for(int i=0; i<(int)(Math.random()*5)+3; i++)
                                          {
                                             System.out.print(".");
                                             delay(1);
                                          }
                                          System.out.println("\nSaved.\n");
                                       }
                                       else
                                          if(choice.equals("snq"))
                                          {
                                             saver.openFile();
                                             System.out.print("Saving");
                                             saver.addData(Castle, guards, self, R, C, F, turn);
                                             saver.closeFile();
                                             for(int i=0; i<(int)(Math.random()*5)+3; i++)
                                             {
                                                System.out.print(".");
                                                delay(1);
                                             }
                                             System.out.println("\nSaved.\n");
                                             System.out.println("Thanks for playing.");
                                             System.exit(0);
                                          }
                            
         if(choice.equals("play puzzle") || choice.equals("play") || choice.equals("solve puzzle") || choice.equals("solve") || choice.equals("skip") && Castle.get(F).getRoom(R, C).getKey() != 99)
         {
            self.keys.add(r.getKey());
            r.setKey(99);
            storeState();  
         }
      } 
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

   public static void fight(Fighter enemy) throws IOException
   {
      size = getFileSize("directHit.txt");
      String[] list = new String[size];
      
      readFile(list, "directHit.txt");
         
      size = getFileSize("indirectHit.txt");
      String[] list2 = new String[size];
      
      readFile(list2, "indirectHit.txt");
         
      size = getFileSize("missHit.txt");
      String[] list3 = new String[size];
      
      readFile(list3, "missHit.txt");
      
      size = getFileSize("directBlock.txt");
      String[] list4 = new String[size];
      
      readFile(list4, "directBlock.txt");
         
      size = getFileSize("indirectBlock.txt");
      String[] list5 = new String[size];
      
      readFile(list5, "indirectBlock.txt");
         
      size = getFileSize("missBlock.txt");
      String[] list6 = new String[size];
      
      readFile(list6, "missBlock.txt");
      
      while(self.isDead() == false && enemy.isDead() == false)
      {
         if(enemy.getName().equals("Imperial Guard"))
            System.out.println("An "+enemy+" stands before you.");
         else
            System.out.println("A "+enemy+" stands before you.");
         System.out.println();
         
         if(Math.random() >= 0.5)
            turn = 1;
         else
            turn = 2;
         ran = false;
         
         while(enemy.getHP() > 0 && self.getHP() > 0 && ran == false)
         {
            self.showHP();
            enemy.showHP();
         
            if(turn == 1)
            {
               self.allAffect();
               self.filter();
               
               System.out.println("(Attack/Use/Run)");
               System.out.println();
               System.out.print("> ");
               
               action = input.next();
               action = action.toLowerCase();
               System.out.println();
               
               while(!action.equals("attack") && !action.equals("a") && !action.equals("run") && !action.equals("r") && !action.equals("use") && !action.equals("e"))
               {
                  System.out.println("What?");
                  System.out.println("(Attack/Use/Run)");
                  System.out.println();
                  System.out.print(">");
                  action = input.next();
                  action = action.toLowerCase();
                  System.out.println();
               }
               
               if(action.equals("attack") || action.equals("a"))
               {
                  self.attack(enemy, list, list2, list3);
               }
               else
                  if(action.equals("use") || action.equals("e"))
                  {
                     self.use(enemy);
                  }
                  else
                     ran = true;
            }
            else
            {
               input.nextLine();
               enemy.use(self);
               enemy.allAffect();
               enemy.filter();
              
               enemy.attack(self, list4, list5, list6);
            }
            if(turn == 2)
               turn = 1;
            else
               turn = 2;
            
            if(ran == true)
            {
               String temp = action.substring(action.indexOf(" ")+1);
               run(enemy, temp);
            }
         
            if(self.isDead() == true)
            {
               System.out.println("You died.");
               System.out.println();
            }
            delay(2); 
         }
      }
   }

   public static void run(Fighter enemy, String dir) throws IOException
   {
      speedDiff = (self.getSpd()/enemy.getSpd())*50;
      double randomS = Math.random()*100;
      int tempR = R;
      int tempC = C;
   	
      move(dir);
            	
      if(R == tempR && C == tempC)
      {
         System.out.println("You've been cornered.");
         System.out.println();
         ran = false;
      }
      else
         if(speedDiff > randomS)
         {
            System.out.println("You made a clean getaway.");
            System.out.println();
            explore(Castle.get(F).getRoom(R, C));
            ran = true;
         }
         else
         {
            System.out.println("You've been cut off.");
            R = tempR;
            C = tempC;
            System.out.println();
            ran = false;
         }
   }
   
   public static void delay(int secs)
   {
      for(int i=0; i<secs; i++)
      {
         int d = 0;
       
         for(int a=0; a<825; a++)
         {
            for(int b=0; b<825; b++)
            {
               for(int c=0; c<825; c++)
               {
                  d = a + b + c + d;
               }
            }
         }
      }
   }
	
   public static void delayOp(int secs)
   {
      for(int i=0; i<secs; i++)
      {
         int d = 0;
      
         for(int a=0; a<825; a++)
         {
            for(int b=0; b<825; b++)
            {
               for(int c=0; c<825; c++)
               {
                  d = a + b + c + d;
                  
                  mapName = aaa.getMap();
                  
                  if(!mapName.equals("null"))
                     break;
               }
               if(!mapName.equals("null"))
                  break;
            }
            if(!mapName.equals("null"))
               break;
         }
         if(!mapName.equals("null"))
            break;
      }
      aaa.setVisible(false);
   }
  
   public static void move(String dir)
   {
      if(dir.equals("north"))
         R=north(R, C);
      else
         if(dir.equals("south"))
            R=south(R, C);
         else
            if(dir.equals("east"))
               C=east(R, C);
            else
               if(dir.equals("west"))
                  C=west(R, C);		
               else
                  if(dir.equals("up"))
                     F=up();
                  else
                     if(dir.equals("down"))
                        F=down();
   }

   public static void distributeAllItems()
   {
      for(int f=0; f<Castle.size(); f++)
      {
         for(int r=0; r<Castle.get(f).getXSize(); r++)
         {
            for(int c=0; c<Castle.get(f).getYSize(); c++)
            {
               for(int i=0; i<(int)(Math.random()*5)+500; i++)
                  Castle.get(f).getRoom(r, c).addToRoom(new Coin());
               for(int j=0; j<(int)(Math.random()*40); j++)
                  Castle.get(f).getRoom(r, c).addToRoom(new HealthPack("herb", 3));
               for(int k=0; k<(int)(Math.random()*40); k++)
                  Castle.get(f).getRoom(r, c).addToRoom(new HealthPack("bushel", 5));
               Castle.get(f).getRoom(r, c).redistribute();
            }
         }
      }
   }
   
   public static void showInventory()
   {
      int w=0, h=0, b=0, c=0, n=0;
      
      for(int i=0; i<self.inventory.size(); i++)
      {
         if(self.inventory.get(i).getName().equals("herb"))
            h++;
         else
            if(self.inventory.get(i).getName().equals("herb bushel"))
               b++;
            else
               if(self.inventory.get(i).getName().equals("coin"))
                  c++;
               else
                  if(self.inventory.get(i).getName().equals("note"))
                     n++;
                  else
                     w++;
      }
         
      while(!selection.equals("close") && !selection.equals("e"))
      {         
         System.out.println("[Inventory]");
         System.out.println();    
         System.out.println("Independent Items");
         System.out.println("-----------------");
         System.out.println();
         
         if(w>0)
         {
            System.out.println("Weapons: ["+w+"]");
            System.out.println();
         }
         
         if(c>0)
         {
            System.out.println("Yen: ["+c+"]");
            System.out.println();
         }
         
         System.out.println("Useable Items");
         System.out.println("-------------");
         System.out.println();
      	
         if(n>0)
         {
            System.out.println("Notes: ["+n+"]");
            System.out.println();
         }
         
         if(h>0)
         {
            System.out.println("Herbs: ["+h+"]");
            System.out.println();
         }
         
         if(b>0)
         {
            System.out.println("(Herb) Bushels: ["+b+"]");
            System.out.println();
         }
         System.out.print(" > ");
         selection = input.nextLine();
         selection = selection.toLowerCase();
         if(selection.startsWith("use"))
            selection = selection.substring(selection.indexOf(" ")+1);
         System.out.println();
         while(!selection.equals("note") && !selection.equals("herb") && !selection.equals("bushel") && !selection.equals("close") && !selection.equals("e"))
         {
            if(selection.equals("weapon"))
            {
               System.out.println("You cannot use this now.");
            }
            else
               if(selection.equals("coin"))
               {
                  if(c == 0)
                     System.out.println("Even if you had one, you still couldn't use it.");
                  else
                     System.out.println("You can't use this.");
               }
               else
               {
                  System.out.println("You don't have any "+selection+"s to use.");
               }
            
            System.out.println();
            System.out.print(" > ");
            selection = input.nextLine();
            selection = selection.toLowerCase();
            System.out.println();
         }
         
         for(int i=0; i<self.inventory.size(); i++)
         {
            if((self.inventory.get(i).getName()).equals(selection))
            {
               self.inventory.get(i).use(self, self.inventory.get(i).getValue());
               self.inventory.remove(i);
               if(selection.startsWith("h"))
                  h--;
               else
                  if(selection.startsWith("b"))
                     b--;
               break;
            }
         }
      }
      selection = "none";
   }      
   
   public static void storeState()
   {
      self.storeState();
      R1 = R;
      C1 = C;
      F1 = F;
      ran1 = ran;
   	
      for(int i=0; i<self.keys1.size(); i++)
      {
         self.keys1.remove(i);
      }
      for(int i=0; i<self.keys.size(); i++)
      {
         self.keys1.add(self.keys.get(i));
      }
      
      for(int i=0; i<self.inventory1.size(); i++)
      {
         self.inventory1.remove(i);
      }
      for(int i=0; i<self.inventory.size(); i++)
      {
         self.inventory1.add(self.inventory.get(i));
      }
      
      for(int i=0; i<guards.length; i++)
      {
         guards[i].storeState();
      }
     
      for(int h=0; h<Castle.size(); h++)
      { 
         for(int i=0; i<Castle.get(h).getXSize(); i++)
         {
            for(int j=0; j<Castle.get(h).getYSize(); j++)
            {
               Castle.get(h).puzzles[i][j].storeState();
               Castle.get(h).getRoom(i, j).storeState();
            }
         }
      }
   }
	
   public static void resetState()
   {
      self.resetState();
      R = R1;
      C = C1;
      F = F1;
      ran = ran1;
      
      for(int i=0; i<self.keys1.size(); i++)
      {
         if(self.keys.get(i) == null)
            self.keys.add(self.keys1.get(i));
            
         else
            if(self.keys.get(i) != self.keys1.get(i))
               self.keys.add(i, self.keys1.get(i));
      }
      
      for(int i=0; i<self.inventory1.size(); i++)
      {
         if(self.inventory.get(i) == null)
            self.inventory.add(self.inventory1.get(i));
            
         else
            if(self.inventory.get(i) != self.inventory1.get(i))
               self.inventory.add(i, self.inventory1.get(i));
      }
      
      for(int i=0; i<guards.length; i++)
      {
         guards[i].resetState();
      }
      
      for(int h=0; h<Castle.size(); h++)
      { 
         for(int i=0; i<Castle.get(h).getXSize(); i++)
         {
            for(int j=0; j<Castle.get(h).getYSize(); j++)
            {
               Castle.get(h).puzzles[i][j].resetState();
               Castle.get(h).getRoom(i, j).resetState();
            }
         }
      }
   }

   public static void endGame()
   {
      try
      {
         size = getFileSize("endGame.txt");
         String[] a = new String[size];
         readFile(a, "endGame.txt");
         for(int i=0; i<size; i++)
         {
            System.out.println(a[i]);
         }
         System.out.println();
      }
      catch(IOException e){}
          
      System.exit(0);
   }
   
   public static boolean hasWall(char w)
   {
      for(int i=0; i<Castle.get(F).getRoom(R, C).getSize(); i++)
      {
         if(Castle.get(F).getRoom(R, C).walls.get(i).getSide() == w)
            return true;
      }
      return false;
   }

   public static boolean northernMost()
   {
      if(R==0)
         return true;
      return false;
   }

   public static boolean southernMost()
   {
      if(R==3)
         return true;
      return false;
   }

   public static boolean easternMost()
   {
      if(C==4)
         return true;
      return false;
   }

   public static boolean westernMost()
   {
      if(C==0)
         return true;
      return false;
   }
   
   public static boolean isExploreOption(String choice)
   {
      if(choice.equals("play puzzle")    || choice.equals("play")       || 
         choice.equals("solve puzzle")   || choice.equals("solve")      || 
         choice.equals("show inventory") || choice.equals("inventory")  || 
         choice.equals("view inventory") || choice.startsWith("search") || 
         choice.equals("close")          || choice.startsWith("go")     || 
         choice.startsWith("skip")       || choice.equals("w")          ||
         choice.equals("a")              || choice.equals("s")          || 
         choice.equals("d")              || choice.equals("quit")       ||
         choice.endsWith("?")            || choice.equals("snq")        ||
         choice.equals("save")           || choice.equals("e")          ||
         choice.equals("up")             || choice.equals("down")       ||
         choice.equals("r")              || choice.equals("f"))
         return true;
      return false;
   }

   public static boolean alreadyVisited(int R, int C)
   {
      if(Castle.get(F).getRoom(R, C).getKey() == 99)
         return true;
      return false;
   }

   public static int north(int R, int C)
   {   
      count = 0;
      for(int i=0; i<self.keys.size(); i++)
      {
         if(hasWall('N') == true || northernMost() == true)
         {
            if(C == 4 && alreadyVisited(2,4) == true)
            {
               endGame();
            }
            System.out.println("A wall blocks your path.");
            System.out.println();
            break;
         }
         else
            if(alreadyVisited(R-1, C) == true)
            {
               R--;
               break;
            }
            else
               if(self.keys.get(i).equals(Castle.get(F).getRoom(R-1, C).getLock()))
               {
                  System.out.println("The door is unlocked.");
                  System.out.println();
                  R--;
                  break;
               }
               else
               {
                  count++;
                  if(count == self.keys.size())
                  {
                     System.out.println("None of the keys you have fit that lock.");
                     System.out.println();
                     break;
                  }
               }
      }
      return R;
   }

   public static int south(int R, int C)
   {  
      count = 0; 
      for(int i=0; i<self.keys.size(); i++)
      {
         if(hasWall('S') == true || southernMost() == true)
         {
            System.out.println("A wall blocks your path.");
            System.out.println();
            break; 
         }
         else
            if(alreadyVisited(R+1, C) == true)
            {
               R++;
               break;
            }
            else
               if(self.keys.get(i).equals(Castle.get(F).getRoom(R+1, C).getLock()))
               {
                  System.out.println("The door is unlocked.");
                  System.out.println();
                  R++;
                  break;
               }
               else
               {
                  count++;
                  if(count == self.keys.size())
                  {
                     System.out.println("None of the keys you have fit that lock.");
                     System.out.println();
                     break;
                  }
               }
      }
      return R;
   }

   public static int east(int R, int C)
   {   
      count = 0;
      for(int i=0; i<self.keys.size(); i++)
      {
         if(hasWall('E') == true || easternMost() == true)
         {
            System.out.println("A wall blocks your path.");
            System.out.println();
            break;
         }
         else
            if(alreadyVisited(R, C+1) == true)
            {
               C++;
               break;
            }
            else
               if(self.keys.get(i).equals(Castle.get(F).getRoom(R, C+1).getLock()))
               {
                  System.out.println("The door is unlocked.");
                  System.out.println();
                  C++;
                  break;
               }
               else
               {
                  count++;
                  if(count == self.keys.size())
                  {
                     System.out.println("None of the keys you have fit that lock.");
                     System.out.println();
                     break;
                  }
               }
      }
      return C;
   }
	
   public static int west(int R, int C)
   {   
      count = 0;
      for(int i=0; i<self.keys.size(); i++)
      {
         if(hasWall('W') == true || westernMost() == true)
         {
            System.out.println("A wall blocks your path.");
            System.out.println();
            break;
         }
         else
            if(alreadyVisited(R, C-1) == true)
            {
               C--;
               break;
            }
            else
               if(self.keys.get(i).equals(Castle.get(F).getRoom(R, C-1).getLock()))
               {
                  System.out.println("The door is unlocked.");
                  System.out.println();
                 
                  C--;
                  break;
               }
               else
               {
                  count++;
                  if(count == self.keys.size())
                  {
                     System.out.println("None of the keys you have fit that lock.");
                     System.out.println();
                     break;
                  }
               }
      }         
      return C;
   }

   public static int up() 
   {
      F++;
      return F;
   }

   public static int down() 
   {
      F--;
      return F;
   }

   public static int getFileSize(String fileName)throws IOException
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
}
//Put the following "uncommented" at the end either of the set up methods for a delightful practical joke :)

/* int delay = 0;
   int period = 1000; 
   Timer timer = new Timer(); 
   timer.scheduleAtFixedRate(
   new TimerTask() 
   {
   public void run() 
   {
   System.out.println("LOL!!! :D");
   } 
   }
   , delay, period);*/
	
	//Add this to the beginning of the main method to view the code I used to check the accuracy number of iterations needed 
	//to keep a relatively precise period of one true second 
	//(825 iterations for all three of the nested for loops creates a delay that's off by one second every ten minutes)
	
 /*for(int i=-3; i<100000; i++)
   {
   if(i >= 3600)
   System.out.println(i/3600 + "h " + i%3600/60 + "m " + i%60 + "s");
   else
   if(i >= 60)
   System.out.println(i/60 + "m " + i%60 + "s");
   else
   System.out.println(i + "s");
   delay(1);
   }*/