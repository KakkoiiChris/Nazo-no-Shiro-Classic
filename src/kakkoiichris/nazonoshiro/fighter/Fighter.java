//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.fighter;

import kakkoiichris.nazonoshiro.Console;
import kakkoiichris.nazonoshiro.ResetGroup;
import kakkoiichris.nazonoshiro.ResetList;
import kakkoiichris.nazonoshiro.Resettable;
import kakkoiichris.nazonoshiro.item.Item;
import kakkoiichris.nazonoshiro.item.kusuri.Kusuri;

public abstract class Fighter implements Resettable {
    public enum FightResult {
        WIN,
        RUN,
        LOSE
    }
    
    public static FightResult fight(Self self, Enemy enemy) {
        Console.writeLine("A %s stands before you.%n", enemy);
        
        //determines who's attacking and who's defending in battle
        var yourTurn = self.getSpeed() > enemy.getSpeed() || self.getLuck() / 3 > Math.random() * 100;
        
        while (!(self.isDead() || enemy.isDead())) {
            self.showHP(yourTurn);
            Console.newLine();
            
            enemy.showHP(!yourTurn);
            Console.newLine();
            
            if (yourTurn) {
                self.allAffect();
                self.filter();
                
                Console.setPrompt("Attack / Use / Run > ");
                
                var action = Console.readLine().toLowerCase();
                
                Console.newLine();
                
                if (action.matches("a(ttack)?")) {
                    self.attack(enemy);
                }
                else if (action.matches("u(se)?")) {
                    self.use(enemy);
                }
                else if (action.matches("r(un)?")) {
                    return FightResult.RUN;
                }
                else {
                    Console.writeLine("Can't do that...");
                    
                    continue;
                }
            }
            else {
                enemy.allAffect();
                
                enemy.filter();
                
                enemy.use(self);
                
                enemy.attack(self);
            }
            
            yourTurn = !yourTurn;
            
            Console.pause(2);
        }
        
        if (self.isDead()) {
            return FightResult.LOSE;
        }
        
        return FightResult.WIN;
    }
    
    protected final String name;
    
    protected final Stat attack, power, defense, speed, luck, health;
    
    protected final ResetList<Item> inventory = new ResetList<>();
    
    protected final ResetList<Kusuri> effectives = new ResetList<>();
    
    protected final ResetGroup resetGroup;
    
    public Fighter(String name, double attack, double power, double defense, double speed, double luck, double health) {
        this.name = name;
        
        this.attack = new Stat("Attack", 0, attack);
        this.power = new Stat("Power", 0, power);
        this.defense = new Stat("Defense", 0, defense);
        this.speed = new Stat("Speed", 0, speed);
        this.luck = new Stat("Luck", 0, luck);
        this.health = new Stat("Health", 0, health);
        
        resetGroup = ResetGroup.of(this.attack, this.power, this.defense, this.speed, this.luck, this.health, inventory, effectives);
    }
    
    public String getName() {
        return name;
    }
    
    public Stat getAttackStat() {
        return attack;
    }
    
    public Stat getPowerStat() {
        return power;
    }
    
    public Stat getDefenseStat() {
        return defense;
    }
    
    public Stat getSpeedStat() {
        return speed;
    }
    
    public Stat getLuckStat() {
        return luck;
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
    
    public double getPower() {
        return power.getNow().get();
    }
    
    public void setPower(double value) {
        power.getNow().set(value);
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
    
    public double getLuck() {
        return luck.getNow().get();
    }
    
    public void setLuck(double value) {
        luck.getNow().set(value);
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
    
    public void showHP(boolean active) {
        var blocks = " ▘▚▜█";
        
        var max = health.getMax().get().intValue();
        var now = health.getNow().get().intValue();
        
        Console.write("%s %s (%03d / %03d) ║", name, active ? "*" : " ", now, max);
        
        var i = now;
        
        if (i > 0) {
            while (i - 4 > 0) {
                Console.write(blocks.charAt(4));
                
                i -= 4;
            }
            
            Console.write(blocks.charAt(i));
        }
        
        i = max - now;
        
        while (i - 4 >= 0) {
            Console.write(' ');
            
            i -= 4;
        }
        
        Console.writeLine('║');
    }
    
    public boolean isDead() {
        return getHealth() <= 0;
    }
    
    public ResetList<Item> getInventory() {
        return inventory;
    }
    
    public ResetList<Kusuri> getEffectives() {
        return effectives;
    }
    
    public void add(Item item) {
        inventory.add(item);
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
    
    public abstract void attack(Fighter opponent);
    
    public abstract void use(Fighter opponent);
    
    public String toString() {
        return name;
    }
}
