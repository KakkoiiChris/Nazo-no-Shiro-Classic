//Christian Alexander, 1/1/11, Pd. 6
package kakkoiichris.nazonoshiro.fighter;

import kakkoiichris.nazonoshiro.Console;
import kakkoiichris.nazonoshiro.ResetValue;
import kakkoiichris.nazonoshiro.Resources;
import kakkoiichris.nazonoshiro.Util;
import kakkoiichris.nazonoshiro.item.Item;
import kakkoiichris.nazonoshiro.item.kusuri.Corrupt;
import kakkoiichris.nazonoshiro.item.kusuri.Kusuri;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Enemy extends Fighter {
    private static final List<String> direct = Resources.getLines("directBlock.txt");
    private static final List<String> indirect = Resources.getLines("indirectBlock.txt");
    private static final List<String> miss = Resources.getLines("missBlock.txt");
    
    private final ResetValue<Item> droppable;
    
    public Enemy(String name, double attack, double power, double defense, double speed, double luck, double health) {
        super(name, attack, power, defense, speed, luck, health);
        
        droppable = new ResetValue<>(null);
        
        resetGroup.add(droppable);
    }
    
    public Item getDrop() {
        return droppable.get();
    }
    
    @Override
    public void attack(Fighter opponent) {
        var sa = getAttack();
        var sp = getPower();
        var ss = getSpeed();
    
        var ed = opponent.getDefense();
        var es = opponent.getSpeed();
    
        var damage = sa * 100 / (100 + ed);
    
        Console.writeLine("%s takes %02f damage!%n", opponent.name, damage);
    
        var list = (damage < 0) ? miss : direct;
        
        var message = Util.getRandom(list);
    
        Console.writeLine(message, opponent.name);
    
        Console.newLine();
    
        opponent.setHealth(opponent.getHealth() - damage);
    
        Console.newLine();
    }
    
    public void use(Fighter opponent) {
        var kasugi = inventory
            .stream()
            .filter(item -> item instanceof Kusuri)
            .map(Kusuri.class::cast)
            .collect(Collectors.groupingBy(Kusuri::getName));
        
        if (!effectives.isEmpty()) {
            if (effectives.stream().anyMatch(k -> k instanceof Corrupt) && !kasugi.get("Pure").isEmpty() && getHealth() <= 10) {
                var used = kasugi.get("Pure").get(0);
                
                inventory.remove(used);
                
                Console.writeLine("They've been purified.\n");
            }
        }
    }
}