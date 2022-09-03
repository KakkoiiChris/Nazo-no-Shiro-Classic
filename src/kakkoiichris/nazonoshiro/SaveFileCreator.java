package kakkoiichris.nazonoshiro;   //Christian Alexander, 10/06/2011, Period 8

import kakkoiichris.nazonoshiro.castle.CastleFloor;
import kakkoiichris.nazonoshiro.fighter.Fighter;
import kakkoiichris.nazonoshiro.fighter.Self;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class SaveFileCreator {
    private Scanner input = new Scanner(System.in);
    private ArrayList<String> allData = new ArrayList();
    private Formatter x;
    
    public void openFile() throws IOException {
        try {
            System.out.print("File Name > ");
            x = new Formatter(input.nextLine() + ".txt");
            System.out.println();
        }
        catch (Exception e) {
            System.out.println("FILE SAVE ERROR");
        }
    }
    
    public void addData(List<CastleFloor> castle, Fighter[] guards, Self self, int R, int C, int F, boolean t) {
        for (int f = 0; f < castle.size(); f++) {
            x.format("%s%s", castle.get(f).getName(), "\n");
        }
        
        for (int f = 0; f < castle.size(); f++) {
            for (int r = 0; r < castle.get(f).getXSize(); r++) {
                for (int c = 0; c < castle.get(f).getYSize(); c++) {
                    x.format("%s%s%s%s%s", "#", f, r, c, castle.get(f).getRoom(r, c).getName() + "\n");
                    x.format("%s%s%s%s%s", castle.get(f).getPuzzleType()[r][c], ",", castle.get(f).getFloorPlan()[r][c].getKey(), "'", castle.get(f).getFloorPlan()[r][c].getLock() + "\n");
                    for (int w = 0; w < castle.get(f).getRoom(r, c).getSize(); w++) {
                        x.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s",
                            "|" + castle.get(f).getRoom(r, c).getWall(w).getSide() + ":",
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getName() + "\n",
                            "!" + castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("Tanto") + "a",
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("Wakizashi") + "b",
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("Katana") + "c",
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("Bo Staff") + "d",
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("Shuriken") + "e",
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("Nunchaku") + "\n",
                            "*" + castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("Herb") + "a",
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("Bushel") + "b",
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("Coin") + "\n",
                            "&" + castle.get(f).getRoom(r, c).getWall(w).getStorage().getCountB("Pure") + "a",
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCountB("Corrupt") + "b",
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCountB("Ultra") + "c",
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCountB("Brace") + "d",
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCountB("Velocity") + "e",
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCountB("Sub") + "f",
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCountB("Intimidate") + "g",
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCountB("Blind") + "\n");
                    }
                }
            }
        }
        
        x.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s",
            "S" + F + R + C + ":" + self.getName() + "," + self.getGender() + "," + self.getDoB() + "\n",
            "P" + self.getAtk() + "," + self.getDef() + "," + self.getSpd() + "," + self.getHP() + "\n",
            "!" + self.getCount("Tanto") + "a",
            self.getCount("Wakizashi") + "b",
            self.getCount("Katana") + "c",
            self.getCount("Bo Staff") + "d",
            self.getCount("Shuriken") + "e",
            self.getCount("Nunchaku") + "\n",
            "*" + self.getCount("Herb") + "a",
            self.getCount("Bushel") + "b",
            self.getCount("Coin") + "\n",
            "&" + self.getCountB("Pure") + "a",
            self.getCountB("Corrupt") + "b",
            self.getCountB("Ultra") + "c",
            self.getCountB("Brace") + "d",
            self.getCountB("Velocity") + "e",
            self.getCountB("Sub") + "f",
            self.getCountB("Intimidate") + "g",
            self.getCountB("Blind") + "\n");
        
        for (int i = 0; i < self.getEffectives().size(); i++) {
            if (self.getEffectives().size() > 0)
                x.format("%s", "e" + self.getEffectives().get(i).getName() + ":" + self.getEffectives().get(i).getTimer() + "\n");
        }
        
        x.format("%s", "k");
        
        for (int i = 0; i < self.getKeys().size(); i++) {
            x.format("%s", self.getKeys().get(i) + ",");
        }
        
        x.format("%s", "\n");
        
        for (int g = 0; g < guards.length; g++) {
            x.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s",
                "S" + F + R + C + ":" + guards[g].getName() + "\n",
                "P" + guards[g].getAtk() + "," + guards[g].getDef() + "," + guards[g].getSpd() + "," + guards[g].getHP() + "\n",
                "!" + guards[g].getCount("Tanto") + "a",
                guards[g].getCount("Wakizashi") + "b",
                guards[g].getCount("Katana") + "c",
                guards[g].getCount("Bo Staff") + "d",
                guards[g].getCount("Shuriken") + "e",
                guards[g].getCount("Nunchaku") + "\n",
                "*" + guards[g].getCount("Herb") + "a",
                guards[g].getCount("Bushel") + "b",
                guards[g].getCount("Coin") + "\n",
                "&" + guards[g].getCountB("Pure") + "a",
                guards[g].getCountB("Corrupt") + "b",
                guards[g].getCountB("Ultra") + "c",
                guards[g].getCountB("Brace") + "d",
                guards[g].getCountB("Velocity") + "e",
                guards[g].getCountB("Sub") + "f",
                guards[g].getCountB("Intimidate") + "g",
                guards[g].getCountB("Blind") + "\n");
            
            for (int i = 0; i < guards[g].getEffectives().size(); i++) {
                if (guards[g].getEffectives().size() > 0)
                    x.format("%s", "e" + guards[g].getEffectives().get(i).getName() + ":" + guards[g].getEffectives().get(i).getTimer() + "\n");
            }
        }
    }
    
    public void closeFile() {
        x.close();
    }
}