//Christian Alexander, 6/15/11, Pd. 6
package kakkoiichris.nazonoshiro.item;

import kakkoiichris.kotoba.Console;
import kakkoiichris.nazonoshiro.fighter.Fighter;
import kakkoiichris.nazonoshiro.fighter.Self;

public enum HealthPack implements Item {
    HERB(3, "Herb", "A tiny leaf with healing properties."),
    BUSHEL(5, "Herb Bushel", "Many tiny leaves with healing properties.");

    private final int value;
    private final String name;
    private final String description;

    HealthPack(int value, String name, String description) {
        this.value = value;
        this.name = name;
        this.description = description;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean pickUp(Console console, Self self) {
        return true;
    }

    @Override
    public boolean use(Console console, Fighter fighter) {
        return false;
    }
}
