//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.item;

import kakkoiichris.kotoba.Console;
import kakkoiichris.nazonoshiro.fighter.Fighter;
import kakkoiichris.nazonoshiro.fighter.Self;

public enum Weapon implements Item {
    BO_STAFF(0, "Bo Staff", "") {
        @Override
        public void show(Console console) {
            console.writeLine("""
                ========XXXXXX========
                       Bo Staff""".stripIndent());
        }
    },
    KATANA(0, "Katana", "") {
        @Override
        public void show(Console console) {
            console.writeLine("""
                [XXXXX{================>
                         Katana""".stripIndent());
        }
    },
    NUNCHAKU(0, "Nunchaku", "") {
        @Override
        public void show(Console console) {
            console.writeLine("""
                |XXXX|O-oooooo-O|XXXX|
                       Nunchaku""".stripIndent());
        }
    },
    SHURIKEN(0, "Shuriken", "") {
        @Override
        public void show(Console console) {
            console.writeLine("""
                    O-]==>
                Shuriken Knife""".stripIndent());
        }
    },
    TANTO(0, "Tanto", "") {
        @Override
        public void show(Console console) {
            console.writeLine("""
                [XXX{========>
                    Tanto""".stripIndent());
        }
    },
    WAKIZASHI(0, "Wakizashi", "") {
        @Override
        public void show(Console console) {
            console.writeLine("""
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
    
    public abstract void show(Console console);
    
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
    public boolean pickUp(Console console, Self self) {
        self.setAttack(1);
        
        return true;
    }
    
    @Override
    public boolean use(Console console, Fighter fighter) {
        console.writeLine("You cannot use this.");
        
        return false;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
