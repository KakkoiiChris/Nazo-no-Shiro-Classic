//Christian Alexander, 6/21/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.storage;

import kakkoiichris.nazonoshiro.ResetList;
import kakkoiichris.nazonoshiro.Resettable;
import kakkoiichris.nazonoshiro.Util;
import kakkoiichris.nazonoshiro.fighter.Self;
import kakkoiichris.nazonoshiro.item.Item;
import kakkoiichris.nazonoshiro.item.kasugi.Kasugi;

public abstract class Storage implements Resettable {
    private final String name;
    
    protected ResetList<Item> items = new ResetList<>();
    protected ResetList<Kasugi> kasugis = new ResetList<>();
    
    public Storage(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    @Override
    public void storeState() {
        items.storeState();
        kasugis.storeState();
    }
    
    @Override
    public void resetState() {
        items.resetState();
        kasugis.resetState();
    }
    
    public void add(Item item) {
        items.add(item);
    }
    
    public int getItemCount(String name) {
        var index = 0;
        
        for (var item : items) {
            if (item.getName().equals(name.toLowerCase())) {
                index++;
            }
        }
        
        return index;
    }
    
    public int getKasugiCount(String name) {
        var index = 0;
        
        for (var kasugi : kasugis) {
            if (kasugi.getName().equals(name)) {
                index++;
            }
        }
        
        return index;
    }
    
    public void rummage(Self self) {
        System.out.println("It opened.\n");
        
        var m = 0;
        var h = 0;
        var b = 0;
        var c = 0;
        
        for (var item : items) {
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
            
            System.out.printf("-%d coin%s%n", c, plural);
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
        
        var pick = Util.input.nextLine().toLowerCase();
        
        System.out.println();
        
        while (!pick.equals("close armoir")) {
            switch (pick) {
                case "take metal plate" -> {
                    for (var i = 0; i < items.size(); i++) {
                        if ((items.get(i).getName()).equals("metal")) {
                            self.getInventory().add(items.remove(i--));
                        }
                    }
                    
                    System.out.println("Taken\n");
                }
                
                case "take herb bushel" -> {
                    for (var i = 0; i < items.size(); i++) {
                        if ((items.get(i).getName()).equals("herb bushel")) {
                            self.getInventory().add(items.remove(i--));
                        }
                    }
                    
                    System.out.println("Taken\n");
                }
                
                case "take herb" -> {
                    for (var i = 0; i < items.size(); i++) {
                        if ((items.get(i).getName()).equals("herb")) {
                            self.getInventory().add(items.remove(i--));
                        }
                    }
                    
                    System.out.println("Taken\n");
                }
                
                case "take coin" -> {
                    for (var i = 0; i < items.size(); i++) {
                        if ((items.get(i).getName()).equals("coin")) {
                            self.getInventory().add(items.remove(i--));
                        }
                    }
                    
                    System.out.println("Taken\n");
                }
            }
            
            System.out.print(">");
            
            pick = Util.input.nextLine().toLowerCase();
            
            System.out.println();
        }
    }
    
    public abstract void open(Self self);
}