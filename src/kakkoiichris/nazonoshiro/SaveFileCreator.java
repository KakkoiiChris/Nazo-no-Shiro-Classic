//Christian Alexander, 10/06/2011, Period 8
package kakkoiichris.nazonoshiro;

import kakkoiichris.nazonoshiro.castle.CastleFloor;
import kakkoiichris.nazonoshiro.fighter.Fighter;
import kakkoiichris.nazonoshiro.fighter.Self;

import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class SaveFileCreator {
    private Formatter formatter;
    
    public void openFile() {
        try (var input = new Scanner(System.in)) {
            System.out.print("File Name > ");
            
            var fileName = input.nextLine();
            
            formatter = new Formatter("%s.txt".formatted(fileName));
            
            System.out.println();
        }
        catch (Exception e) {
            System.err.println("FILE SAVE ERROR");
        }
    }
    
    public void addData(List<CastleFloor> castle, Fighter[] guards, Self self, int row, int column, int floor, boolean yourTurn) {
        for (var castleFloor : castle) {
            formatter.format("%s%n", castleFloor.getName());
        }
        
        for (var f = 0; f < castle.size(); f++) {
            for (var r = 0; r < castle.get(f).getColumns(); r++) {
                for (var c = 0; c < castle.get(f).getRows(); c++) {
                    formatter.format("#%s%s%s%s%n", f, r, c, castle.get(f).getRoom(r, c).getName());
                    formatter.format("%s,%s'%s%n", castle.get(f).getPuzzleType()[r][c], castle.get(f).getFloorPlan()[r][c].getKey(), castle.get(f).getFloorPlan()[r][c].getLock());
                    
                    for (var w = 0; w < castle.get(f).getRoom(r, c).getSize(); w++) {
                        formatter.format("|%s:%s%n%sa%sb%sc%sd%se%s%n*%sa%sb%s%n&%sa%sb%sc%sd%se%sf%sg%s%n",
                            castle.get(f).getRoom(r, c).getWall(w).getSide(),
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getName(),
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("Tanto"),
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("Wakizashi"),
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("Katana"),
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("Bo Staff"),
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("Shuriken"),
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("Nunchaku"),
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("Herb"),
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("Bushel"),
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCount("Coin"),
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCountB("Pure"),
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCountB("Corrupt"),
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCountB("Ultra"),
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCountB("Brace"),
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCountB("Velocity"),
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCountB("Sub"),
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCountB("Intimidate"),
                            castle.get(f).getRoom(r, c).getWall(w).getStorage().getCountB("Blind")
                        );
                    }
                }
            }
        }
        
        formatter.format("S%d%d%d:%s,%s,%s%nP%d,%d,%d,%d%n!%sa%sb%sc%sd%se%s%n*%sa%sb%s%n&%sa%sb%sc%sd%se%sf%sg%s%n",
            floor, row, column, self.getName(), self.getGender(), self.getBirthday(),
            self.getAttack(), self.getDefense(), self.getSpeed(), self.getHealth(),
            self.getCount("Tanto"),
            self.getCount("Wakizashi"),
            self.getCount("Katana"),
            self.getCount("Bo Staff"),
            self.getCount("Shuriken"),
            self.getCount("Nunchaku"),
            self.getCount("Herb"),
            self.getCount("Bushel"),
            self.getCount("Coin"),
            self.getCountB("Pure"),
            self.getCountB("Corrupt"),
            self.getCountB("Ultra"),
            self.getCountB("Brace"),
            self.getCountB("Velocity"),
            self.getCountB("Sub"),
            self.getCountB("Intimidate"),
            self.getCountB("Blind")
        );
        
        for (var i = 0; i < self.getEffectives().size(); i++) {
            formatter.format("e%s:%d%n", self.getEffectives().get(i).getName(), self.getEffectives().get(i).getTimer());
        }
        
        formatter.format("k%s%n", String.join(",", self.getKeys().stream().map(Object::toString).toList()));
        
        for (var guard : guards) {
            formatter.format("S%d%d%d:%s%nP%d,%d,%d,%d%n!%da%db%dc%dd%de%d%n*%da%db%d%n&%da%db%dc%dd%de%df%dg%d%n",
                floor, row, column, guard.getName(),
                guard.getAttack(), guard.getDefense(), guard.getSpeed(), guard.getHealth(),
                guard.getCount("Tanto"),
                guard.getCount("Wakizashi"),
                guard.getCount("Katana"),
                guard.getCount("Bo Staff"),
                guard.getCount("Shuriken"),
                guard.getCount("Nunchaku"),
                guard.getCount("Herb"),
                guard.getCount("Bushel"),
                guard.getCount("Coin"),
                guard.getCountB("Pure"),
                guard.getCountB("Corrupt"),
                guard.getCountB("Ultra"),
                guard.getCountB("Brace"),
                guard.getCountB("Velocity"),
                guard.getCountB("Sub"),
                guard.getCountB("Intimidate"),
                guard.getCountB("Blind")
            );
            
            for (var i = 0; i < guard.getEffectives().size(); i++) {
                if (guard.getEffectives().size() > 0) {
                    formatter.format("e%s:%d%n", guard.getEffectives().get(i).getName(), guard.getEffectives().get(i).getTimer());
                }
            }
        }
    }
    
    public void closeFile() {
        formatter.close();
    }
}