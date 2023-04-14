//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.fighter;

import kakkoiichris.kotoba.Console;
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
    
    public static FightResult fight(Console console, Self self, Enemy enemy) {
        console.write("A %s stands before you.%n%n", enemy);
        
        //determines who's attacking and who's defending in battle
        var yourTurn = self.getSpeed() > enemy.getSpeed() || self.getLuck() / 3 > Math.random() * 100;
        
        while (!(self.isDead() || enemy.isDead())) {
            self.showHP(console, yourTurn);
            console.newLine();
            
            enemy.showHP(console, !yourTurn);
            console.newLine();
            
            if (yourTurn) {
                self.allAffect(console);
                self.filter();
                
                console.setPrompt("Attack / Use / Run > ");
                
                var action = console.readLine().orElseThrow().toLowerCase();
                
                console.newLine();
                
                if (action.matches("a(ttack)?")) {
                    self.attack(console, enemy);
                }
                else if (action.matches("u(se)?")) {
                    self.use(console, enemy);
                }
                else if (action.matches("r(un)?")) {
                    var speedDiff = (enemy.getSpeed() - self.getSpeed()) / self.getSpeed();
                    
                    if (speedDiff >= 1) {
                        console.writeLine("You've been cornered.\n");
                    }
                    else if (speedDiff >= 0) {
                        if (Math.random() > speedDiff) {
                            console.writeLine("You barely scraped by.\n");
                            
                            return FightResult.RUN;
                        }
                        else {
                            console.writeLine("You've been cut off.\n");
                        }
                    }
                    else {
                        console.writeLine("You made a clean getaway.\n");
                        
                        return FightResult.RUN;
                    }
                }
                else {
                    console.writeLine("Can't do that...\n");
                    
                    continue;
                }
            }
            else {
                enemy.allAffect(console);
                
                enemy.filter();
                
                enemy.use(console, self);
                
                enemy.attack(console, self);
            }
            
            yourTurn = !yourTurn;
            
            console.pause(2);
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
    
    public void showHP(Console console, boolean active) {
        var blocks = " ▘▚▜█";
        
        var max = health.getMax().get().intValue();
        var now = health.getNow().get().intValue();
        
        console.write("%s %s (%03d / %03d) ║", active ? ">" : " ", name, now, max);
        
        var i = now;
        
        if (i > 0) {
            while (i - 4 > 0) {
                console.write(blocks.charAt(4));
                
                i -= 4;
            }
            
            console.write(blocks.charAt(i));
        }
        
        i = max - now;
        
        while (i - 4 >= 0) {
            console.write(' ');
            
            i -= 4;
        }
        
        console.writeLine('║');
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
    
    public void allAffect(Console console) {
        for (var i = 0; i < getEffectives().size(); i++) {
            getEffectives().get(i).affect(console, this);
        }
    }
    
    public abstract void attack(Console console, Fighter opponent);
    
    public abstract void use(Console console, Fighter opponent);
    
    public String toString() {
        return name;
    }
}
