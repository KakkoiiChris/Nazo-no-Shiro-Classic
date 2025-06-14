//Christian Alexander, 9/13/2011
package kakkoiichris.nazonoshiro.item.kusuri;

import kakkoiichris.kotoba.Console;
import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Blind extends Kusuri {
    public Blind() {
        super("Blind", "", 3, 3, false);
    }

    @java.lang.Override
    public boolean use(Console console, Fighter fighter) {
        return true;
    }

    @java.lang.Override
    public void affect(Console console, Fighter fighter) {
        if (timer != 0) {
            var pronoun = fighter.getPronoun();

            console.writeLine("%s've been blinded!%n", pronoun);

            fighter.setSpeed(fighter.getSpeed() - 3);
        }
        else {
            console.writeLine("Blind has worn off.\n");

            fighter.setSpeed(fighter.getSpeed() + 3);
        }

        timer--;
    }
}