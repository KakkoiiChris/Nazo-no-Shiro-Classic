//Christian Alexander, 1/1/11, Pd. 6
package kakkoiichris.nazonoshiro.fighter;

import kakkoiichris.nazonoshiro.item.Item;
import kakkoiichris.nazonoshiro.item.kasugi.*;
import kakkoiichris.nazonoshiro.item.weapon.Weapon;

import java.util.List;

public abstract class Enemy extends Fighter {
    private Item droppable, droppableLast;

    public Enemy(String name, int attack, int defense, int speed, int health) {
        super(name, attack, defense, speed, health);
    }

    public void setDrop(Fighter self, List<Weapon> weapons) {
        int wCount = 10 - self.getAttack();

        if (Math.random() > 0.5 && wCount > 0) {
            int temp = (int) (Math.random() * weapons.size());

            droppable = weapons.get(temp);
        }
    }

    public Item getDrop() {
        return droppable;
    }

    public void storeState() {
    }

    public void resetState() {
    }

    public void dropItem(Fighter self) {
        System.out.printf("The %s dropped something. You pick it up.%n", this);

        try {
            this.getDrop().pickUp(self);

            System.out.printf("It's a %s!%n", this.getDrop());
        }
        catch (NullPointerException e) {
            System.out.println("It's just a null. Nothing worth while.");
        }
    }

    public void attack(Fighter enemy, String[] list4, String[] list5, String[] list6) {
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

        if (enemy.getDefense() == 1) {
            dMax = 6;
            dMin = 1;
        }
        else if (enemy.getDefense() == 2) {
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
            temp = pickWord(list6);

            System.out.printf("%s%s%s%n", list6[temp].substring(list6[temp].indexOf('@') + 1, list6[temp].indexOf('#')), enemy, list6[temp].substring(list6[temp].indexOf('$') + 1, list6[temp].indexOf('%')));
        }
        else if (attack + (attack - defense) < aMax) {
            temp = pickWord(list5);

            System.out.printf("%s%s%s%n", list5[temp].substring(list5[temp].indexOf('@') + 1, list5[temp].indexOf('#')), enemy, list5[temp].substring(list5[temp].indexOf('$') + 1, list5[temp].indexOf('%')));
        }
        else {
            temp = pickWord(list4);

            System.out.printf("%s%s%s%n", list4[temp].substring(list4[temp].indexOf('@') + 1, list4[temp].indexOf('#')), enemy, list4[temp].substring(list4[temp].indexOf('$') + 1, list4[temp].indexOf('%')));
        }

        enemy.setHealth(attack + (attack - defense));

        System.out.println();
    }

    public void use(Fighter self) {
        int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0, h = 0, i = 0;

        var done = false;

        for (var j = 0; j < useable.size(); j++) {
            switch (useable.get(i).getName()) {
                case "Blind" -> a++;

                case "Brace" -> b++;

                case "Burn" -> c++;

                case "Corrupt" -> d++;

                case "Fixer" -> e++;

                case "Pure" -> f++;

                case "Ultra" -> g++;

                case "Velocity" -> h++;

                default -> i++;
            }
        }

        if (getEffectives().size() > 0) {
            for (var j = 0; j < getEffectives().size(); j++) {
                if (getEffectives().get(j).getName().equals("Corrupt") && health <= 10) {
                    var index = search("Pure", useable);

                    if (index >= 0) {
                        useable.remove(index);
                    }
                    else {
                        System.err.println("NO!!!!!");
                    }

                    index = search("Corrupt", getEffectives());

                    if (index >= 0) {
                        getEffectives().remove(index);
                    }
                    else {
                        System.err.println("NO!!!!!");
                    }

                    System.out.println("They've been purified.\n");

                    done = true;
                }
            }

            if (!done) {
                var rand = (int) (Math.random() * 3) + 1;

                switch (rand) {
                    case 1 -> {
                        for (var k = 0; k < useable.size(); k++) {
                            if (useable.get(k).getName().equals("Ultra")) {
                                getEffectives().add(useable.remove(k));

                                done = true;

                                break;
                            }
                        }
                    }

                    case 2 -> {
                        for (var k = 0; k < useable.size(); k++) {
                            if (useable.get(k).getName().equals("Brace")) {
                                getEffectives().add(new Brace());

                                useable.remove(k);

                                done = true;

                                break;
                            }
                        }
                    }

                    case 3 -> {
                        for (var k = 0; k < useable.size(); k++) {
                            if (useable.get(k).getName().equals("Velocity")) {
                                getEffectives().add(new Velocity());

                                useable.remove(k);

                                done = true;

                                break;
                            }
                        }
                    }
                }
            }

            if (!done) {
                var rand = (int) (Math.random() * 3) + 1;

                switch (rand) {
                    case 1 -> {
                        for (var k = 0; k < useable.size(); k++) {
                            if (useable.get(k).getName().equals("Sub")) {
                                getEffectives().add(new Sub());

                                useable.remove(k);

                                done = true;

                                break;
                            }
                        }
                    }

                    case 2 -> {
                        for (var k = 0; k < useable.size(); k++) {
                            if (useable.get(k).getName().equals("Intimidate")) {
                                getEffectives().add(new Intimidate());

                                useable.remove(k);

                                done = true;

                                break;
                            }
                        }
                    }

                    case 3 -> {
                        for (var k = 0; k < useable.size(); k++) {
                            if (useable.get(k).getName().equals("Blind")) {
                                getEffectives().add(new Blind());

                                useable.remove(k);

                                done = true;

                                break;
                            }
                        }
                    }
                }
            }

            if (!done) {
                done = true;
            }
        }
    }

    public int search(String type, List<Kasugi> any) {
        for (var k = 0; k < any.size(); k++) {
            if (((any.get(k)).getName()).equals(type)) {
                return k;
            }
        }

        return -5;
    }
}