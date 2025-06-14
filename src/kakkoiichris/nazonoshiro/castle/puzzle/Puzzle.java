//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.puzzle;

import kakkoiichris.kotoba.Console;
import kakkoiichris.nazonoshiro.Event;
import kakkoiichris.nazonoshiro.ResetValue;
import kakkoiichris.nazonoshiro.Resettable;
import kakkoiichris.nazonoshiro.Util;

public abstract class Puzzle implements Resettable {
    private final String name;
    private final String intro;
    protected final ResetValue<Boolean> solved = new ResetValue<>(false);

    public Puzzle(String name, String intro) {
        this.name = name;
        this.intro = intro;
    }

    public static Puzzle random() {
        return switch (Util.random.nextInt(3)) {
            case 0 -> new Kurobune();

            case 1 -> new Oboeru();

            default -> new Seihoukei();
        };
    }

    public boolean isSolved() {
        return solved.get();
    }

    public String getName() {
        return name;
    }

    @Override
    public void storeState() {
        solved.storeState();
    }

    @Override
    public void resetState() {
        solved.resetState();
    }

    public void victory() {
        solved.set(true);
    }

    public boolean play(Console console) {
        console.writeLine("%s%n%n", intro);

        init(console);

        var event = Event.CONTINUE;

        while (event == Event.CONTINUE) {
            event = doRound(console);
        }

        wrapUp(console);

        return event == Event.SUCCESS;
    }

    public abstract void init(Console console);

    public abstract Event doRound(Console console);

    public abstract void wrapUp(Console console);
}