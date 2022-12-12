//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.fighter;

import kakkoiichris.nazonoshiro.Console;
import kakkoiichris.nazonoshiro.ResetValue;
import kakkoiichris.nazonoshiro.Util;
import kakkoiichris.nazonoshiro.item.kasugi.Kasugi;

import java.util.List;
import java.util.stream.Collectors;

public class Self extends Fighter {
    private String birthday = "", gender = "";
    
    private final ResetValue<Integer> key = new ResetValue<>(0);   //stores all the keys you pick up
    
    public Self(String name, String birthday, String gender) {
        super(name, (int) (Math.random() * 5) + 3, (int) (Math.random() * 5) + 3, (int) (Math.random() * 5) + 3, 50);
        
        this.birthday = birthday;
        this.gender = gender;
        
        resetGroup.add(key);
    }
    
    public Self() {
        super("Self", (int) (Math.random() * 5) + 3, (int) (Math.random() * 5) + 3, (int) (Math.random() * 5) + 3, 50);
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
    public void attack(Fighter opponent, List<String> direct, List<String> indirect, List<String> miss) {
        int aMax, aMin, dMax, dMin;
        
        if (getAttack() == 1) {
            aMax = 6;
            aMin = 1;
        }
        else if (getAttack() == 2) {
            aMax = 8;
            aMin = 3;
        }
        else {
            aMax = 10;
            aMin = 5;
        }
        
        if (opponent.getDefense() == 1) {
            dMax = 6;
            dMin = 1;
        }
        else if (opponent.getDefense() == 2) {
            dMax = 8;
            dMin = 3;
        }
        else {
            dMax = 10;
            dMin = 5;
        }
        
        var attack = (int) (Math.random() * (aMax - aMin)) + aMin;
        var defense = (int) (Math.random() * (dMax - dMin)) + dMin;
        
        if (attack + (attack - defense) < 0) {
            var message = Util.getRandom(miss);
            
            Console.writeLine(message.substring(message.indexOf('@') + 1, message.indexOf('#')) + opponent + message.substring(message.indexOf('$') + 1, message.indexOf('%')));
        }
        else if (attack + (attack - defense) < aMax) {
            var message = Util.getRandom(indirect);
            
            Console.writeLine(message.substring(message.indexOf('@') + 1, message.indexOf('#')) + opponent + message.substring(message.indexOf('$') + 1, message.indexOf('%')));
        }
        else {
            var message = Util.getRandom(direct);
            
            Console.writeLine(message.substring(message.indexOf('@') + 1, message.indexOf('#')) + opponent + message.substring(message.indexOf('$') + 1, message.indexOf('%')));
        }
        
        opponent.setHealth(attack + (attack - defense));
        
        Console.newLine();
    }
    
    public void use(Fighter opponent) {
        var kasugi = inventory
            .stream()
            .filter(item -> item instanceof Kasugi)
            .map(Kasugi.class::cast)
            .collect(Collectors.groupingBy(Kasugi::getName));
        
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