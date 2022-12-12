//Christian Alexander, 1/1/11, Pd. 6
package kakkoiichris.nazonoshiro.fighter;

import kakkoiichris.nazonoshiro.Console;
import kakkoiichris.nazonoshiro.ResetValue;
import kakkoiichris.nazonoshiro.Util;
import kakkoiichris.nazonoshiro.item.Item;
import kakkoiichris.nazonoshiro.item.Weapon;
import kakkoiichris.nazonoshiro.item.kasugi.Corrupt;
import kakkoiichris.nazonoshiro.item.kasugi.Kasugi;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Enemy extends Fighter {
    private final ResetValue<Item> droppable;
    
    public Enemy(String name, int attack, int defense, int speed, int health) {
        super(name, attack, defense, speed, health);
        
        droppable = new ResetValue<>(null);
        
        resetGroup.add(droppable);
    }
    
    public void setDrop(Fighter self, List<Weapon> weapons) {
        var wCount = 10 - self.getAttack();
        
        if (Math.random() > 0.5 && wCount > 0) {
            var temp = (int) (Math.random() * weapons.size());
            
            droppable.set(weapons.get(temp));
        }
    }
    
    public Item getDrop() {
        return droppable.get();
    }
    
    public void dropItem(Self self) {
        Console.writeLine("The %s dropped something. You pick it up.", this);
        
        try {
            var drop = getDrop();
            
            if (drop.pickUp(self)) {
                self.add(drop);
            }
            
            Console.writeLine("It's a %s!", getDrop());
        }
        catch (NullPointerException e) {
            Console.writeLine("It's just a null. Nothing worth while.");
        }
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
        
        if (!effectives.isEmpty()) {
            if (effectives.stream().anyMatch(k -> k instanceof Corrupt) && !kasugi.get("Pure").isEmpty() && getHealth() <= 10) {
                var used = kasugi.get("Pure").get(0);
                
                inventory.remove(used);
                
                Console.writeLine("They've been purified.\n");
            }
        }
    }
}