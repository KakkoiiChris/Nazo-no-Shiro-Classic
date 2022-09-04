package kakkoiichris.nazonoshiro;   //Christian Alexander, 10/06/2011, Period 8

import kakkoiichris.nazonoshiro.castle.CastleFloor;
import kakkoiichris.nazonoshiro.fighter.Fighter;
import kakkoiichris.nazonoshiro.fighter.Self;

import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class SaveFileCreator {
    private final Scanner input = new Scanner(System.in);
    private Formatter x;

    public void openFile() {
        try {
            System.out.print("File Name > ");
            x = new Formatter(input.nextLine() + ".txt");
            System.out.println();
        }
        catch (Exception e) {
            System.err.println("FILE SAVE ERROR");
        }
    }

    public void addData(List<CastleFloor> castle, Fighter[] guards, Self self, int R, int C, int F, boolean t) {
        for (var castleFloor : castle) {
            x.format("%s%s", castleFloor.getName(), "\n");
        }

        for (var f = 0; f < castle.size(); f++) {
            for (var r = 0; r < castle.get(f).getXSize(); r++) {
                for (var c = 0; c < castle.get(f).getYSize(); c++) {
                    x.format("%s%s%s%s%s", "#", f, r, c, castle.get(f).getRoom(r, c).getName() + "\n");
                    x.format("%s%s%s%s%s", castle.get(f).getPuzzleType()[r][c], ",", castle.get(f).getFloorPlan()[r][c].getKey(), "'", castle.get(f).getFloorPlan()[r][c].getLock() + "\n");

                    for (var w = 0; w < castle.get(f).getRoom(r, c).getSize(); w++) {
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

        for (var i = 0; i < self.getEffectives().size(); i++) {
            if (self.getEffectives().size() > 0) {
                x.format("%s", "e" + self.getEffectives().get(i).getName() + ":" + self.getEffectives().get(i).getTimer() + "\n");
            }
        }

        x.format("%s", "k");

        for (var i = 0; i < self.getKeys().size(); i++) {
            x.format("%s", self.getKeys().get(i) + ",");
        }

        x.format("%s", "\n");

        for (var guard : guards) {
            x.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s",
                    "S" + F + R + C + ":" + guard.getName() + "\n",
                    "P" + guard.getAtk() + "," + guard.getDef() + "," + guard.getSpd() + "," + guard.getHP() + "\n",
                    "!" + guard.getCount("Tanto") + "a",
                    guard.getCount("Wakizashi") + "b",
                    guard.getCount("Katana") + "c",
                    guard.getCount("Bo Staff") + "d",
                    guard.getCount("Shuriken") + "e",
                    guard.getCount("Nunchaku") + "\n",
                    "*" + guard.getCount("Herb") + "a",
                    guard.getCount("Bushel") + "b",
                    guard.getCount("Coin") + "\n",
                    "&" + guard.getCountB("Pure") + "a",
                    guard.getCountB("Corrupt") + "b",
                    guard.getCountB("Ultra") + "c",
                    guard.getCountB("Brace") + "d",
                    guard.getCountB("Velocity") + "e",
                    guard.getCountB("Sub") + "f",
                    guard.getCountB("Intimidate") + "g",
                    guard.getCountB("Blind") + "\n");

            for (var i = 0; i < guard.getEffectives().size(); i++) {
                if (guard.getEffectives().size() > 0) {
                    x.format("%s", "e" + guard.getEffectives().get(i).getName() + ":" + guard.getEffectives().get(i).getTimer() + "\n");
                }
            }
        }
    }

    public void closeFile() {
        x.close();
    }
}