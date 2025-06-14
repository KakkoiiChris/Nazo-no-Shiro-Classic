//Christian Alexander, 9/13/2011
package kakkoiichris.nazonoshiro.item.kusuri;

import kakkoiichris.kotoba.Console;
import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Ultra extends Kusuri {
    public Ultra() {
        super("Ultra", "", 3, 3, true);
    }

    @java.lang.Override
    public boolean use(Console console, Fighter fighter) {
        return true;
    }

    @java.lang.Override
    public void affect(Console console, Fighter fighter) {
        if (timer != 0) {
            var pronoun = fighter.getPronoun();

            console.writeLine("%s feel reinvigorated!%n", pronoun);

            fighter.setHealth(-3);
        }
        else {
            console.writeLine("Ultra has worn off.");
        }

        timer--;
    }
}