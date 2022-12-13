//Christian Alexander, 6/21/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.storage;

import kakkoiichris.nazonoshiro.Console;
import kakkoiichris.nazonoshiro.ResetList;
import kakkoiichris.nazonoshiro.Resettable;
import kakkoiichris.nazonoshiro.fighter.Self;
import kakkoiichris.nazonoshiro.item.Coin;
import kakkoiichris.nazonoshiro.item.Item;
import kakkoiichris.nazonoshiro.item.kasugi.Kasugi;

import java.util.regex.Pattern;
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
        Console.writeLine("The %s opened.%n", name);
        
        while (true) {
            if (items.isEmpty()) {
                Console.writeLine("It is empty...");
                
                break;
            }
            
            var coins = items
                .stream()
                .filter(item -> item instanceof Coin)
                .map(Coin.class::cast)
                .toList();
            
            if (!coins.isEmpty()) {
                Console.writeLine("- Coins (%s)", Coin.getTotalString(coins));
            }
            
            var allItems = items
                .stream()
                .filter(item -> !(item instanceof Coin))
                .collect(Collectors.groupingBy(item -> item.getName().toLowerCase()));
            
            for (var key : allItems.keySet()) {
                var list = allItems.get(key);
                
                var name = list.get(0).getName();
                var count = list.size();
                
                Console.writeLine("- %s (%d)", name, count);
            }
            
            Console.newLine();
            
            var pick = Console.readLine().toLowerCase();
            
            Console.newLine();
            
            if (pick.matches("take all|empty")) {
                Console.writeLine("You stuffed everything into your backpack.");
                
                self.getInventory().addAll(items);
                
                items.clear();
                
                break;
            }
            
            var matcher = Pattern.compile("take (\\w+)").matcher(pick);
            
            if (matcher.find()) {
                var choice = matcher.group(1);
                
                if (choice.equals("coins")) {
                    Console.writeLine("You scooped up all the coins at the bottom.");
                    
                    items.removeAll(coins);
                    
                    self.getInventory().addAll(coins);
                }
                else {
                
                }
            }
        }
    }
    
    public abstract void open(Self self);
}