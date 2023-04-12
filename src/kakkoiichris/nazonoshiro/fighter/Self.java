//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.fighter;

import kakkoiichris.kotoba.Console;
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
    public void attack(Console console, Fighter opponent) {
        var sa = getAttack();
        var sp = getPower();
        var ss = getSpeed();
        
        var ed = opponent.getDefense();
        var es = opponent.getSpeed();
        
        var damage = sa * 100 / (100 + ed);
        
        console.writeLine("%s takes %02f damage!%n", opponent.name, damage);
        
        var list = (damage < 0) ? miss : direct;
        
        var message = Util.getRandom(list);
        
        console.writeLine(message, opponent.name);
        
        console.newLine();
        
        opponent.setHealth(opponent.getHealth() - damage);
        
        console.newLine();
    }
    
    public void use(Console console, Fighter opponent) {
        var kasugi = inventory
            .stream()
            .filter(item -> item instanceof Kusuri)
            .map(Kusuri.class::cast)
            .collect(Collectors.groupingBy(Kusuri::getName));
        
        if (kasugi.isEmpty()) {
            console.writeLine("You have no kusuri to use.%n");
            
            return;
        }
        
        console.writeLine("Kusuri:");
        
        for (var key : kasugi.keySet()) {
            console.writeLine("%s: [%d]", key, kasugi.get(key).size());
        }
        
        console.newLine();
        
        while (true) {
            var choice = console.readLine().orElseThrow();
            
            console.newLine();
            
            if (!kasugi.containsKey(choice)) {
                console.writeLine("You have no %s's to use.%n", choice);
                
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