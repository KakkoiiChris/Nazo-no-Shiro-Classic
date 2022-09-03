   //Christian Alexander, 10/06/2011, Period 8
   
   import kakkoiichris.nazonoshiro.castle.CastleFloor;
   import kakkoiichris.nazonoshiro.fighter.Fighter;
   import kakkoiichris.nazonoshiro.fighter.Self;

   import java.io.*;
   import java.lang.*;
   import java.util.*;

    public class SaveFileCreator
   {
      private Scanner input = new Scanner(System.in);
      private ArrayList<String> allData = new ArrayList();
      private Formatter x;
   
       public void openFile() throws IOException
      {
         try
         {
            System.out.print("File Name > ");
            x = new Formatter(input.nextLine() + ".txt");
            System.out.println();
         }
             catch(Exception e)
            {
               System.out.println("FILE SAVE ERROR");
            }
      }
   
       public void addData(ArrayList<CastleFloor> castle, Fighter[] guards, Self self, int R, int C, int F, int t)
      {
         for(int f=0; f<castle.size(); f++)
         {
            x.format("%s%s", castle.get(f).getName(), "\n");
         }
         
         for(int f=0; f<castle.size(); f++)
         {	
            for(int r=0; r<castle.get(f).getXSize(); r++)
            {
               for(int c=0; c<castle.get(f).getYSize(); c++)
               {
                  x.format("%s%s%s%s%s", "#", f, r, c, castle.get(f).getRoom(r, c).getName() + "\n");
                  x.format("%s%s%s%s%s", castle.get(f).getPuzzleType()[r][c], ",", castle.get(f).getFloorPlan()[r][c].getKey(), "'", castle.get(f).getFloorPlan()[r][c].getLock() + "\n");
                  for(int w=0; w<castle.get(f).getRoom(r, c).getSize(); w++)
                  {
                     x.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s", 
                        "|" + castle.get(f).getRoom(r, c).getWall(w).getSide() + ":", 
                              castle.get(f).getRoom(r, c).getWall(w).getStorage().getName() + "\n", 
                        "!" + castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("kakkoiichris.nazonoshiro.item.weapon.Tanto") + "a",
                        		castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("kakkoiichris.nazonoshiro.item.weapon.Wakizashi") + "b",
                        		castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("kakkoiichris.nazonoshiro.item.weapon.Katana") + "c",
                        		castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("kakkoiichris.nazonoshiro.item.weapon.Bo Staff") + "d",
                        		castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("kakkoiichris.nazonoshiro.item.weapon.Shuriken") + "e",
                              castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("kakkoiichris.nazonoshiro.item.weapon.Nunchaku") + "\n",
                        "*" + castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("Herb") + "a",
                        		castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("Bushel") + "b",
                        		castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("kakkoiichris.nazonoshiro.item.Coin") + "\n",
                        "&" + castle.get(f).getRoom(r, c).getWall(w).getStorage().getCountB("kakkoiichris.nazonoshiro.item.kasugi.Pure") + "a",
                        		castle.get(f).getRoom(r, c).getWall(w).getStorage().getCountB("kakkoiichris.nazonoshiro.item.kasugi.Corrupt") + "b",
                        		castle.get(f).getRoom(r, c).getWall(w).getStorage().getCountB("kakkoiichris.nazonoshiro.item.kasugi.Ultra") + "c",
                        		castle.get(f).getRoom(r, c).getWall(w).getStorage().getCountB("kakkoiichris.nazonoshiro.item.kasugi.Brace") + "d",
                        		castle.get(f).getRoom(r, c).getWall(w).getStorage().getCountB("kakkoiichris.nazonoshiro.item.kasugi.Velocity") + "e",
                        		castle.get(f).getRoom(r, c).getWall(w).getStorage().getCountB("kakkoiichris.nazonoshiro.item.kasugi.Sub") + "f",
                        		castle.get(f).getRoom(r, c).getWall(w).getStorage().getCountB("kakkoiichris.nazonoshiro.item.kasugi.Intimidate") + "g",
                        		castle.get(f).getRoom(r, c).getWall(w).getStorage().getCountB("kakkoiichris.nazonoshiro.item.kasugi.Blind") + "\n");
                  }
               }
            }
         }
         
         x.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s",
                        "S" + F + R + C + ":" + self.getName() + "," + self.getGender() + "," + self.getDoB() + "\n",
                        "P" + self.getAtk() + "," + self.getDef() + "," + self.getSpd() + "," + self.getHP() + "\n",
                        "!" + self.getCount("kakkoiichris.nazonoshiro.item.weapon.Tanto") + "a",
                        		self.getCount("kakkoiichris.nazonoshiro.item.weapon.Wakizashi") + "b",
                        		self.getCount("kakkoiichris.nazonoshiro.item.weapon.Katana") + "c",
                        		self.getCount("kakkoiichris.nazonoshiro.item.weapon.Bo Staff") + "d",
                        		self.getCount("kakkoiichris.nazonoshiro.item.weapon.Shuriken") + "e",
                              self.getCount("kakkoiichris.nazonoshiro.item.weapon.Nunchaku") + "\n",
                        "*" + self.getCount("Herb") + "a",
                        		self.getCount("Bushel") + "b",
                        		self.getCount("kakkoiichris.nazonoshiro.item.Coin") + "\n",
                        "&" + self.getCountB("kakkoiichris.nazonoshiro.item.kasugi.Pure") + "a",
                        		self.getCountB("kakkoiichris.nazonoshiro.item.kasugi.Corrupt") + "b",
                        		self.getCountB("kakkoiichris.nazonoshiro.item.kasugi.Ultra") + "c",
                        		self.getCountB("kakkoiichris.nazonoshiro.item.kasugi.Brace") + "d",
                        		self.getCountB("kakkoiichris.nazonoshiro.item.kasugi.Velocity") + "e",
                        		self.getCountB("kakkoiichris.nazonoshiro.item.kasugi.Sub") + "f",
                        		self.getCountB("kakkoiichris.nazonoshiro.item.kasugi.Intimidate") + "g",
                        		self.getCountB("kakkoiichris.nazonoshiro.item.kasugi.Blind") + "\n");
      						
         for(int i = 0; i< self.getEffectives().size(); i++)
         {
            if(self.getEffectives().size() > 0)
               x.format("%s", "e" + self.getEffectives().get(i).getName() + ":" + self.getEffectives().get(i).getTimer() + "\n");
         }
         
         x.format("%s", "k");
         
         for(int i = 0; i< self.getKeys().size(); i++)
         {
            x.format("%s", self.getKeys().get(i) + ",");
         }
         
         x.format("%s", "\n");
         
         for(int g=0; g<guards.length; g++)
         {
            x.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s",
                        "S" + F + R + C + ":" + guards[g].getName() + "\n",
                        "P" + guards[g].getAtk() + "," + guards[g].getDef() + "," + guards[g].getSpd() + "," + guards[g].getHP() + "\n",
                        "!" + guards[g].getCount("kakkoiichris.nazonoshiro.item.weapon.Tanto") + "a",
                        		guards[g].getCount("kakkoiichris.nazonoshiro.item.weapon.Wakizashi") + "b",
                        		guards[g].getCount("kakkoiichris.nazonoshiro.item.weapon.Katana") + "c",
                        		guards[g].getCount("kakkoiichris.nazonoshiro.item.weapon.Bo Staff") + "d",
                        		guards[g].getCount("kakkoiichris.nazonoshiro.item.weapon.Shuriken") + "e",
                              guards[g].getCount("kakkoiichris.nazonoshiro.item.weapon.Nunchaku") + "\n",
                        "*" + guards[g].getCount("Herb") + "a",
                        		guards[g].getCount("Bushel") + "b",
                        		guards[g].getCount("kakkoiichris.nazonoshiro.item.Coin") + "\n",
                        "&" + guards[g].getCountB("kakkoiichris.nazonoshiro.item.kasugi.Pure") + "a",
                        		guards[g].getCountB("kakkoiichris.nazonoshiro.item.kasugi.Corrupt") + "b",
                        		guards[g].getCountB("kakkoiichris.nazonoshiro.item.kasugi.Ultra") + "c",
                        		guards[g].getCountB("kakkoiichris.nazonoshiro.item.kasugi.Brace") + "d",
                        		guards[g].getCountB("kakkoiichris.nazonoshiro.item.kasugi.Velocity") + "e",
                        		guards[g].getCountB("kakkoiichris.nazonoshiro.item.kasugi.Sub") + "f",
                        		guards[g].getCountB("kakkoiichris.nazonoshiro.item.kasugi.Intimidate") + "g",
                        		guards[g].getCountB("kakkoiichris.nazonoshiro.item.kasugi.Blind") + "\n");
               			
            for(int i = 0; i< guards[g].getEffectives().size(); i++)
            {
               if(guards[g].getEffectives().size() > 0)
                  x.format("%s", "e" + guards[g].getEffectives().get(i).getName() + ":" + guards[g].getEffectives().get(i).getTimer() + "\n");
            }
         }
      }
      
       public void closeFile()
      {
         x.close();
      }
   }