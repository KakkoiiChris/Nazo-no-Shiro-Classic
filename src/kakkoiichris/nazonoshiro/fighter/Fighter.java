//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.fighter;

import kakkoiichris.nazonoshiro.Console;
import kakkoiichris.nazonoshiro.ResetGroup;
import kakkoiichris.nazonoshiro.ResetList;
import kakkoiichris.nazonoshiro.Resettable;
import kakkoiichris.nazonoshiro.item.Item;
import kakkoiichris.nazonoshiro.item.kasugi.Kasugi;

import java.util.List;

public abstract class Fighter implements Resettable {
    protected final String name;
    
    protected final Stat attack, defense, speed, health;
    
    protected final ResetList<Kasugi> effectives = new ResetList<>();
    protected final ResetList<Kasugi> usable = new ResetList<>();
    protected final ResetList<Item> inventory = new ResetList<>();
    
    protected final ResetGroup resetGroup;
    
    public Fighter(String name, double attack, double defense, double speed, double health) {
        this.name = name;
        
        this.attack = new Stat("Attack", 0, attack);
        this.defense = new Stat("Defense", 0, defense);
        this.speed = new Stat("Speed", 0, speed);
        this.health = new Stat("Health", 0, health);
        
        resetGroup = ResetGroup.of(this.attack, this.defense, this.speed, this.health, effectives, usable, inventory);
    }
    
    public String getName() {
        return name;
    }
    
    public Stat getAttackStat() {
        return attack;
    }
    
    public Stat getDefenseStat() {
        return defense;
    }
    
    public Stat getSpeedStat() {
        return speed;
    }
    
    public Stat getHealthStat() {
        return health;
    }
    
    public double getAttack() {
        return attack.getNow().get();
    }
    
    public void setAttack(double value) {
        attack.getNow().set(value);
    }
    
    public double getDefense() {
        return defense.getNow().get();
    }
    
    public void setDefense(double value) {
        defense.getNow().set(value);
    }
    
    public double getSpeed() {
        return speed.getNow().get();
    }
    
    public void setSpeed(double value) {
        speed.getNow().set(value);
    }
    
    public double getHealth() {
        return health.getNow().get();
    }
    
    public void setHealth(double value) {
        health.getNow().set(value);
    }
    
    public void heal(int value) {
        setHealth(getHealth() + value);
    }
    
    public boolean isDead() {
        return getHealth() <= 0;
    }
    
    @Override
    public void storeState() {
        resetGroup.storeState();
    }
    
    @Override
    public void resetState() {
        resetGroup.resetState();
    }
    
    public <T extends Item> boolean hasItem(Class<T> clazz) {
        return inventory.stream().anyMatch(clazz::isInstance);
    }
    
    public void filter() {
        for (var i = 0; i < getEffectives().size(); i++) {
            if (getEffectives().get(i).getTimer() == -1) {
                getEffectives().remove(i--);
            }
        }
    }
    
    public void allAffect() {
        for (var i = 0; i < getEffectives().size(); i++) {
            getEffectives().get(i).affect(this);
        }
    }
    
    public String toString() {
        return name;
    }
    
    public void showHP() {
        Console.write("%s %s [", name, health);
        
        for (var i = 0; i < getHealth(); i++) {
            Console.write("=");
        }
        
        Console.writeLine("]\n");
    }
    
    public int getCount(String name) {
        var temp = 0;
        
        for (var i = 0; i < getInventory().size(); i++) {
            if (getInventory().get(i).getName().equals(name.toLowerCase())) {
                temp++;
            }
        }
        
        return temp;
    }
    
    public int getCountB(String name) {
        var temp = 0;
        
        for (var kasugi : usable) {
            if (kasugi.getName().equals(name.toLowerCase())) {
                temp++;
            }
        }
        
        return temp;
    }
    
    public void addKasugi(Kasugi kasugi) {
        usable.add(kasugi);
    }
    
    public abstract void attack(Fighter enemy, List<String> direct, List<String> indirect, List<String> miss);
    
    public abstract void use(Fighter enemy);
    
    public ResetList<Kasugi> getEffectives() {
        return effectives;
    }
    
    
    public ResetList<Item> getInventory() {
        return inventory;
    }
}
