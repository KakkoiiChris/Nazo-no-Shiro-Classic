//Christian Alexander, 9/13/2011
package kakkoiichris.nazonoshiro.item.kusuri;

import kakkoiichris.kotoba.Console;
import kakkoiichris.nazonoshiro.fighter.Fighter;

public class Pure extends Kusuri {
    public Pure() {
        super("Pure", "", 0, 0, true);

        timer = 99;
    }

    @java.lang.Override
    public boolean use(Console console, Fighter fighter) {
        return true;
    }

    @java.lang.Override
    public void affect(Console console, Fighter fighter) {
        var pronoun = fighter.getPronoun();

        console.writeLine("%s used an antidote!%n", pronoun);
    }
}