//Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.room;

import kakkoiichris.kotoba.Console;
import kakkoiichris.nazonoshiro.ResetGroup;
import kakkoiichris.nazonoshiro.ResetValue;
import kakkoiichris.nazonoshiro.Resettable;
import kakkoiichris.nazonoshiro.Resources;
import kakkoiichris.nazonoshiro.castle.Direction;
import kakkoiichris.nazonoshiro.castle.Wall;
import kakkoiichris.nazonoshiro.fighter.Self;
import kakkoiichris.nazonoshiro.item.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room implements Resettable {
    private final String name;

    private final int floor;
    private final int row;
    private final int column;
    private final int lock;
    private final int key;

    private final Map<Direction, Wall> walls = new HashMap<>();

    private Direction exit = Direction.NONE;

    private final ResetValue<Boolean> visited = new ResetValue<>(false);

    private final ResetValue<Boolean> locked;

    protected final ResetGroup resetGroup;

    private final String descriptionBefore;

    private final String descriptionAfter;

    public Room(String name, int floor, int row, int column, int key, int lock, boolean locked) {
        this.name = name;
        this.floor = floor;
        this.row = row;
        this.column = column;
        this.key = key;
        this.lock = lock;
        this.locked = new ResetValue<>(locked);

        resetGroup = ResetGroup.of(visited, this.locked);

        var fileName = "before%d-%d-%d.txt".formatted(floor, row, column);

        descriptionBefore = Resources.tryGetString(fileName).orElse("Default room before text.");

        fileName = "after%d-%d-%d.txt".formatted(floor, row, column);

        descriptionAfter = Resources.tryGetString(fileName).orElse("Default room after text.");
    }

    public String getName() {
        return name;
    }

    public int getFloor() {
        return floor;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getKey() {
        return key;
    }

    public int getLock() {
        return lock;
    }

    public Wall getWall(Direction direction) {
        return walls.get(direction);
    }

    public void setWall(Direction direction, Wall wall) {
        resetGroup.add(wall);

        walls.put(direction, wall);
    }

    public boolean hasWall(Direction direction) {
        return walls.containsKey(direction);
    }

    public Map<Direction, Wall> getWalls() {
        return walls;
    }

    public Direction getExit() {
        return exit;
    }

    public void setExit(Direction exit) {
        this.exit = exit;
    }

    public int size() {
        return walls.size();
    }

    public boolean isVisited() {
        return visited.get();
    }

    public void setVisited() {
        visited.set(true);
    }

    public boolean isLocked() {
        return locked.get();
    }

    public void unlock() {
        locked.set(false);
    }

    @Override
    public void storeState() {
        resetGroup.storeState();
    }

    @Override
    public void resetState() {
        resetGroup.resetState();
    }

    public String getDescription() {
        return visited.get() ? descriptionAfter : descriptionBefore;
    }

    public void enter(Console console) {
        console.writeLine("Enter\n");
    }

    public void explore(Console console) {
        console.writeLine("Explore\n");
    }

    public void exit(Console console) {
        console.writeLine("Exit\n");
    }

    public void look(Console console, Direction direction) {
        if (hasWall(direction)) {
            var wall = getWall(direction);

            var storage = wall.getStorage();

            console.write("You see a '%s' in that direction.%n%n", storage.getName());

            return;
        }

        console.writeLine("You see a door in that direction.\n");
    }

    public void search(Console console, Direction direction, Self self) {
        if (walls.isEmpty()) {
            console.writeLine("There's nothing there.\n");

            return;
        }

        if (hasWall(direction)) {
            var wall = getWall(direction);

            var storage = wall.getStorage();

            if (storage.isEmpty()) {
                console.writeLine("The %s is empty.%n", storage.getName());

                return;
            }

            storage.open(console, self);

            return;
        }

        console.writeLine("Nothing to search there.\n");
    }

    public void distributeItems(List<Item> items) {
        for (var wall : walls.values()) {
            if (items.isEmpty()) return;

            wall.getStorage().add(items.remove(0));
        }
    }

    public String toString() {
        return name;
    }
}