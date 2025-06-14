//Christian Alexander, 9/13/2011
package kakkoiichris.nazonoshiro.item.kusuri;

import kakkoiichris.kotoba.Console;
import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Brace extends Kusuri {
    public Brace() {
        super("Brace", "", 3, 3, true);
    }

    @java.lang.Override
    public boolean use(Console console, Fighter fighter) {
        return true;
    }

    @java.lang.Override
    public void affect(Console console, Fighter fighter) {
        if (timer != 0) {
            var pronoun = fighter.getPronoun();

            console.writeLine("%s've braced for impact!%n", pronoun);

            fighter.setDefense(fighter.getDefense() - 3);
        }
        else {
            console.writeLine("Brace has worn off.\n");
        }

        timer--;
    }
}