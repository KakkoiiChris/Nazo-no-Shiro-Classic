//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.fighter;

import kakkoiichris.nazonoshiro.ResetGroup;
import kakkoiichris.nazonoshiro.ResetList;
import kakkoiichris.nazonoshiro.ResetValue;
import kakkoiichris.nazonoshiro.Resettable;
import kakkoiichris.nazonoshiro.item.Item;
import kakkoiichris.nazonoshiro.item.kasugi.Kasugi;

import java.util.List;

public abstract class Fighter implements Resettable {
    protected final String name;
    
    protected final ResetValue<Integer> attack, defense, speed, health;
    
    protected final ResetList<Kasugi> effectives = new ResetList<>();
    protected final ResetList<Kasugi> usable = new ResetList<>();
    protected final ResetList<Item> inventory = new ResetList<>();
    
    protected final ResetGroup resetGroup;
    
    public Fighter(String name, int attack, int defense, int speed, int health) {
        this.name = name;
        
        this.attack = new ResetValue<>(attack);
        this.defense = new ResetValue<>(defense);
        this.speed = new ResetValue<>(speed);
        this.health = new ResetValue<>(health);
        
        resetGroup = ResetGroup.of(this.attack, this.defense, this.speed, this.health, effectives, usable, inventory);
    }
    
    public String getName() {
        return name;
    }
    
    public int getAttack() {
        return attack.get();
    }
    
    public void setAttack(int value) {
        attack.set(value);
    }
    
    public int getDefense() {
        return defense.get();
    }
    
    public void setDefense(int value) {
        defense.set(value);
    }
    
    public int getSpeed() {
        return speed.get();
    }
    
    public void setSpeed(int value) {
        speed.set(value);
    }
    
    public int getHealth() {
        return health.get();
    }
    
    public void setHealth(int value) {
        health.set(value);
    }
    
    public void heal(int value) {
        health.set(health.get() + value);
    }
    
    public boolean isDead() {
        return health.get() <= 0;
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
        System.out.printf("%s %s [", name, health);
        
        for (var i = 0; i < health.get(); i++) {
            System.out.print("=");
        }
        
        System.out.println("]\n");
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
