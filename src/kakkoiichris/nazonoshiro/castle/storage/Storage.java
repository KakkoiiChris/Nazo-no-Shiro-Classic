//Christian Alexander, 6/21/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.storage;

import kakkoiichris.nazonoshiro.Console;
import kakkoiichris.nazonoshiro.ResetList;
import kakkoiichris.nazonoshiro.Resettable;
import kakkoiichris.nazonoshiro.fighter.Self;
import kakkoiichris.nazonoshiro.item.Item;
import kakkoiichris.nazonoshiro.item.kasugi.Kasugi;

import java.util.stream.Collectors;

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
        Console.writeLine("The %s opened.%n%nIt contains:%n", name);
    
        var allItems = items
            .stream()
            .collect(Collectors.groupingBy(Item::getName));
        
        for (var key:allItems.keySet()) {
            var count = allItems.get(key).size();
            
            Console.writeLine("- %s (%d)", key, count);
        }
        
        Console.write(">");
        
        var pick = Console.readLine().toLowerCase();
        
        Console.newLine();
        
        while (!pick.equals("close armoir")) {
            switch (pick) {
                case "take metal plate" -> {
                    for (var i = 0; i < items.size(); i++) {
                        if ((items.get(i).getName()).equals("metal")) {
                            self.getInventory().add(items.remove(i--));
                        }
                    }
                    
                    Console.writeLine("Taken\n");
                }
                
                case "take herb bushel" -> {
                    for (var i = 0; i < items.size(); i++) {
                        if ((items.get(i).getName()).equals("herb bushel")) {
                            self.getInventory().add(items.remove(i--));
                        }
                    }
                    
                    Console.writeLine("Taken\n");
                }
                
                case "take herb" -> {
                    for (var i = 0; i < items.size(); i++) {
                        if ((items.get(i).getName()).equals("herb")) {
                            self.getInventory().add(items.remove(i--));
                        }
                    }
                    
                    Console.writeLine("Taken\n");
                }
                
                case "take coin" -> {
                    for (var i = 0; i < items.size(); i++) {
                        if ((items.get(i).getName()).equals("coin")) {
                            self.getInventory().add(items.remove(i--));
                        }
                    }
                    
                    Console.writeLine("Taken\n");
                }
            }
            
            Console.write(">");
            
            pick = Console.readLine().toLowerCase();
            
            Console.newLine();
        }
    }
    
    public abstract void open(Self self);
}