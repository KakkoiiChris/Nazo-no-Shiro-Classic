//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.fighter;

import kakkoiichris.nazonoshiro.item.Item;
import kakkoiichris.nazonoshiro.item.kasugi.Kasugi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Fighter {
    private String name;
    protected int attack,
            defense,
            speed,
            health,
            x,
            y,
            attackLast,
            defenseLast,
            speedlast,
            healthLast,
            xLast,
            yLast;

    protected Scanner input = new Scanner(System.in);
    private List<Kasugi> effectives = new ArrayList<>();
    protected List<Kasugi> usable = new ArrayList<>();
    private List<Item> inventory = new ArrayList<>();
    private List<Item> inventoryLast = new ArrayList<>();

    public Fighter(String name, int attack, int defense, int speed, int health) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int n) {
        attack += n;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int n) {
        defense = n;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int n) {
        speed = n;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int a) {
        health = health - a;
    }

    public void heal(int value) {
        health += value;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public boolean hasItem(String a) {
        for (var item : inventory) {
            if (item.getName().equalsIgnoreCase(a)) {
                return true;
            }
        }
        return false;
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
        System.out.print(name + ": " + health + " [");
        for (var i = 0; i < health; i++) {
            System.out.print("=");
        }
        System.out.println("]");
        System.out.println();
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

    public abstract void storeState();

    public abstract void resetState();

    public List<Kasugi> getEffectives() {
        return effectives;
    }

    public void setEffectives(List<Kasugi> effectives) {
        this.effectives = effectives;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public List<Item> getInventoryLast() {
        return inventoryLast;
    }

    public void setInventoryLast(List<Item> inventoryLast) {
        this.inventoryLast = inventoryLast;
    }
}