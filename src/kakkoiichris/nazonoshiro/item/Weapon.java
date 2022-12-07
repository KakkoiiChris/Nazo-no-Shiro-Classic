//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.item;

import kakkoiichris.nazonoshiro.Console;
import kakkoiichris.nazonoshiro.fighter.Fighter;

public enum Weapon implements Item {
    BO_STAFF(0, "Bo Staff", "") {
        @Override
        public void show() {
            Console.writeLine("""
                ========XXXXXX========
                       Bo Staff""".stripIndent());
        }
    },
    KATANA(0, "Bo Staff", "") {
        @Override
        public void show() {
            Console.writeLine("""
                [XXXXX{================>
                         Katana""".stripIndent());
        }
    },
    NUNCHAKU(0, "Bo Staff", "") {
        @Override
        public void show() {
            Console.writeLine("""
                |XXXX|O-oooooo-O|XXXX|
                       Nunchaku""".stripIndent());
        }
    },
    SHURIKEN(0, "Bo Staff", "") {
        @Override
        public void show() {
            Console.writeLine("""
                    O-]==>
                Shuriken Knife""".stripIndent());
        }
    },
    TANTO(0, "Bo Staff", "") {
        @Override
        public void show() {
            Console.writeLine("""
                [XXX{========>
                    Tanto""".stripIndent());
        }
    },
    WAKIZASHI(0, "Bo Staff", "") {
        @Override
        public void show() {
            Console.writeLine("""
                [XXXX{============>
                     Wakizashi""".stripIndent());
        }
    };
    
    private final int value;
    private final String name;
    private final String description;
    
    Weapon(int value, String name, String description) {
        this.value = value;
        this.name = name;
        this.description = description;
    }
    
    public abstract void show();
    
    public int getValue() {
        return value;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    @Override
    public boolean pickUp(Fighter self) {
        self.setAttack(1);
        
        return true;
    }
    
    @Override
    public boolean use(Fighter self) {
        Console.writeLine("You cannot use this.");
        
        return false;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
