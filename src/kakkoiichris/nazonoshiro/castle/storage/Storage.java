package kakkoiichris.nazonoshiro.castle.storage;//Christian Alexander, 6/21/11, Pd. 6

import kakkoiichris.nazonoshiro.fighter.Self;
import kakkoiichris.nazonoshiro.item.Item;
import kakkoiichris.nazonoshiro.item.kasugi.Kasugi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Storage {
    private final String name;
    protected String decision = "";
    protected String pick = "";
    protected Scanner input = new Scanner(System.in);
    protected int x, y;
    protected List<Item> stored = new ArrayList<>();
    protected List<Item> storedLast = new ArrayList<>();
    protected List<Kasugi> contained = new ArrayList<>();
    protected List<Kasugi> containedLast = new ArrayList<>();
    
    public Storage(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isEmpty() {
        return stored.isEmpty();
    }
    
    public void storeState() {
        storedLast.clear();
        
        storedLast.addAll(stored);
    }
    
    public void resetState() {
        stored.clear();
        
        stored.addAll(storedLast);
    }
    
    public void add(Item item) {
        stored.add(item);
    }
    
    public int getCount(String n) {
        var temp = 0;
        
        for (var item : stored) {
            if (item.getName().equals(n.toLowerCase())) {
                temp++;
            }
        }
        
        return temp;
    }
    
    public int getCountB(String n) {
        var temp = 0;
        
        for (var kasugi : contained) {
            if (kasugi.getName().equals(n)) {
                temp++;
            }
        }
        
        return temp;
    }
    
    public void rummage(Self self) {
        System.out.println("It opened.\n");
        
        var m = 0;
        var h = 0;
        var b = 0;
        var c = 0;
        
        for (var item : stored) {
            switch (item.getName()) {
                case "metal" -> m++;
                
                case "coin" -> c++;
                
                case "herb" -> h++;
                
                case "bushel" -> b++;
            }
        }
        
        System.out.println("The Armoir contains:\n");
        
        if (m > 0) {
            var plural = (m > 1) ? "s" : "";
            
            System.out.printf("-%d metal plate%s%n", m, plural);
        }
        
        if (c > 0) {
            var plural = (c > 1) ? "s" : "";
            
            System.out.printf("-%d Coin%s%n", c, plural);
        }
        
        if (h > 0) {
            var plural = (h > 1) ? "s" : "";
            
            System.out.printf("-%d herb%s%n", h, plural);
        }
        
        if (b > 0) {
            var plural = (b > 1) ? "s" : "";
            
            System.out.printf("-%d herb bushel%s%n", b, plural);
        }
        
        System.out.print(">");
        
        pick = input.nextLine().toLowerCase();
        
        System.out.println();
        
        while (!pick.equals("close armoir")) {
            switch (pick) {
                case "take metal plate" -> {
                    for (var i = 0; i < stored.size(); i++) {
                        if ((stored.get(i).getName()).equals("metal")) {
                            self.getInventory().add(stored.remove(i--));
                        }
                    }
                    
                    System.out.println("Taken\n");
                }
                
                case "take herb bushel" -> {
                    for (var i = 0; i < stored.size(); i++) {
                        if ((stored.get(i).getName()).equals("herb bushel")) {
                            self.getInventory().add(stored.remove(i--));
                        }
                    }
                    
                    System.out.println("Taken\n");
                }
                
                case "take herb" -> {
                    for (var i = 0; i < stored.size(); i++) {
                        if ((stored.get(i).getName()).equals("herb")) {
                            self.getInventory().add(stored.remove(i--));
                        }
                    }
                    
                    System.out.println("Taken\n");
                }
                
                case "take Coin" -> {
                    for (var i = 0; i < stored.size(); i++) {
                        if ((stored.get(i).getName()).equals("coin")) {
                            self.getInventory().add(stored.remove(i--));
                        }
                    }
                    
                    System.out.println("Taken\n");
                }
            }
            
            System.out.print(">");
            
            pick = input.nextLine().toLowerCase();
            
            System.out.println();
        }
    }
    
    public abstract void open(Self self);
}