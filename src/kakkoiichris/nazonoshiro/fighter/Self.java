//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.fighter;

import kakkoiichris.nazonoshiro.ResetValue;
import kakkoiichris.nazonoshiro.Util;

import java.util.List;

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
        this.key.set(Math.max(this.key.get(), value));
    }
    
    @Override
    public void attack(Fighter enemy, List<String> direct, List<String> indirect, List<String> miss) {
        int aMax, aMin, dMax, dMin;
        
        if (this.getAttack() == 1) {
            aMax = 6;
            aMin = 1;
        }
        else if (this.getAttack() == 2) {
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
            var message = Util.getRandom(miss);
            
            System.out.println(message.substring(message.indexOf('@') + 1, message.indexOf('#')) + enemy + message.substring(message.indexOf('$') + 1, message.indexOf('%')));
        }
        else if (attack + (attack - defense) < aMax) {
            var message = Util.getRandom(indirect);
            
            System.out.println(message.substring(message.indexOf('@') + 1, message.indexOf('#')) + enemy + message.substring(message.indexOf('$') + 1, message.indexOf('%')));
        }
        else {
            var message = Util.getRandom(direct);
            
            System.out.println(message.substring(message.indexOf('@') + 1, message.indexOf('#')) + enemy + message.substring(message.indexOf('$') + 1, message.indexOf('%')));
        }
        
        enemy.setHealth(attack + (attack - defense));
        
        System.out.println();
    }
    
    public void use(Fighter enemy) {
        int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0, h = 0, i = 0;
        
        var done = false;
        
        System.out.println("Kasugi:");
        
        for (var j = 0; j < usable.size(); j++) {
            switch (usable.get(i).getName()) {
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
        
        if (a > 0) {
            System.out.printf("Blind: [%d]%n", a);
        }
        
        if (b > 0) {
            System.out.printf("Brace: [%d]%n", b);
        }
        
        if (c > 0) {
            System.out.printf("Burn: [%d]%n", c);
        }
        
        if (d > 0) {
            System.out.printf("Corrupt: [%d]%n", d);
        }
        
        if (e > 0) {
            System.out.printf("Fixer: [%d]%n", e);
        }
        
        if (f > 0) {
            System.out.printf("Pure: [%d]%n", f);
        }
        
        if (g > 0) {
            System.out.printf("Ultra: [%d]%n", g);
        }
        
        if (h > 0) {
            System.out.printf("Velocity: [%d]%n", h);
        }
        
        if (i > 0) {
            System.out.printf("Volatile: [%d]%n", i);
        }
        
        System.out.println();
        
        while (!done) {
            System.out.print("> ");
            
            var temp = Util.input.next();
            
            System.out.println("\n");
            
            while (!temp.equals("Blind") && !temp.equals("Brace") && !temp.equals("Burn") && !temp.equals("Corrupt") && !temp.equals("Fixer") && !temp.equals("Pure") && !temp.equals("Ultra") && !temp.equals("Velocity") && !temp.equals("Volatile")) {
                System.out.print("What?\n> ");
                
                temp = Util.input.nextLine();
                
                System.out.println("\n");
            }
            
            var k = 0;
            
            for (var kasugi : usable) {
                if (temp.equals(kasugi.getName())) {
                    if (kasugi.isForYou()) {
                        this.getEffectives().add(kasugi);
                    }
                    else {
                        enemy.getEffectives().add(kasugi);
                    }
                    
                    k = 0;
                    done = true;
                }
                else {
                    k++;
                }
            }
            
            if (k == usable.size() && !done) {
                System.out.printf("You don't have any %ss to use.\n%n", temp);
            }
        }
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
}