//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.fighter;

import kakkoiichris.nazonoshiro.Console;
import kakkoiichris.nazonoshiro.ResetValue;
import kakkoiichris.nazonoshiro.Resources;
import kakkoiichris.nazonoshiro.Util;
import kakkoiichris.nazonoshiro.item.kusuri.Kusuri;

import java.util.List;
import java.util.stream.Collectors;

public class Self extends Fighter {
    private static final List<String> direct = Resources.getLines("directHit.txt");
    private static final List<String> indirect = Resources.getLines("indirectHit.txt");
    private static final List<String> miss = Resources.getLines("missHit.txt");
    
    private String birthday = "", gender = "";
    
    private final ResetValue<Integer> key = new ResetValue<>(0);   //stores all the keys you pick up
    
    public Self(String name, String birthday, String gender) {
        super(name, 50, 50, 50, 50, 50, 50);
        
        this.birthday = birthday;
        this.gender = gender;
        
        resetGroup.add(key);
    }
    
    public Self() {
        super("Self", 50, 50, 50, 50, 50, 50);
    }
    
    public String getBirthday() {
        return birthday;
    }
    
    public String getGender() {
        return gender;
    }
    
    public int getKey() {
        return key.get();
    }
    
    public void setKey(int value) {
        key.set(Math.max(key.get(), value));
    }
    
    @Override
    public void attack(Fighter opponent) {
        var sa = getAttack();
        var sp = getPower();
        var ss = getSpeed();
        
        var ed = opponent.getDefense();
        var es = opponent.getSpeed();
        
        var damage = sa * 100 / (100 + ed);
    
        Console.writeLine("%s takes %02f damage!", opponent.name, damage);
    
        String message;
        
        if (damage < 0) {
            message = Util.getRandom(miss);
        }
        else {
            message = Util.getRandom(direct);
        }
        
        Console.writeLine(message.substring(message.indexOf('@') + 1, message.indexOf('#')) + opponent + message.substring(message.indexOf('$') + 1, message.indexOf('%')));
    
        opponent.setHealth(opponent.getHealth() - damage);
        
        Console.newLine();
    }
    
    public void use(Fighter opponent) {
        var kasugi = inventory
            .stream()
            .filter(item -> item instanceof Kusuri)
            .map(Kusuri.class::cast)
            .collect(Collectors.groupingBy(Kusuri::getName));
        
        if (kasugi.isEmpty()) {
            Console.writeLine("You have no kasugi to use.%n");
            
            return;
        }
        
        Console.writeLine("Kasugi:");
        
        for (var key : kasugi.keySet()) {
            Console.writeLine("%s: [%d]", key, kasugi.get(key).size());
        }
        
        Console.newLine();
        
        while (true) {
            var choice = Console.read();
            
            Console.writeLine("\n");
            
            if (!kasugi.containsKey(choice)) {
                Console.writeLine("You have no %s's to use.%n", choice);
                
                continue;
            }
            
            var used = kasugi.get(choice).get(0);
            
            inventory.remove(used);
            
            if (used.isForYou()) {
                getEffectives().add(used);
            }
            else {
                opponent.getEffectives().add(used);
            }
            
            break;
        }
    }
}