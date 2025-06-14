//Christian Alexander, 9/13/2011
package kakkoiichris.nazonoshiro.item.kusuri;

import kakkoiichris.kotoba.Console;
import kakkoiichris.nazonoshiro.fighter.Fighter;
import kakkoiichris.nazonoshiro.fighter.Self;
import kakkoiichris.nazonoshiro.item.Item;

public abstract class Kusuri implements Item {
    protected String name, description;

    protected int value, duration, timer;

    protected boolean forYou;

    public Kusuri(String name, String description, int value, int duration, boolean forYou) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.duration = timer = duration;
        this.forYou = forYou;
    }

    @java.lang.Override
    public String getName() {
        return name;
    }

    @java.lang.Override
    public String getDescription() {
        return description;
    }

    @java.lang.Override
    public int getValue() {
        return value;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public boolean isForYou() {
        return forYou;
    }

    @java.lang.Override
    public boolean pickUp(Console console, Self self) {
        console.writeLine("You pick up the %s.", name);

        return true;
    }

    public abstract void affect(Console console, Fighter fighter);

    public String toString() {
        return name;
    }
}