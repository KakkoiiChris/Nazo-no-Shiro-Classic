package kakkoiichris.nazonoshiro.castle;

import java.util.Arrays;

// Christian Alexander, 9/4/2022
public enum Direction {
    NONE(0, 0, 0) {
        @Override
        public Direction getInverse() {
            return NONE;
        }
    },

    UP(1, 0, 0) {
        @Override
        public Direction getInverse() {
            return DOWN;
        }
    },

    DOWN(-1, 0, 0) {
        @Override
        public Direction getInverse() {
            return UP;
        }
    },

    NORTH(0, -1, 0) {
        @Override
        public Direction getInverse() {
            return SOUTH;
        }
    },

    EAST(0, 0, 1) {
        @Override
        public Direction getInverse() {
            return WEST;
        }
    },

    SOUTH(0, 1, 0) {
        @Override
        public Direction getInverse() {
            return NORTH;
        }
    },

    WEST(0, 0, -1) {
        @Override
        public Direction getInverse() {
            return EAST;
        }
    };

    private final int deltaFloor;
    private final int deltaRow;
    private final int deltaColumn;

    Direction(int deltaFloor, int deltaRow, int deltaColumn) {
        this.deltaFloor = deltaFloor;
        this.deltaRow = deltaRow;
        this.deltaColumn = deltaColumn;
    }

    public int getDeltaFloor() {
        return deltaFloor;
    }

    public int getDeltaRow() {
        return deltaRow;
    }

    public int getDeltaColumn() {
        return deltaColumn;
    }

    public abstract Direction getInverse();

    public static boolean isValid(String direction) {
        return Arrays.stream(values()).anyMatch(d -> d.name().equals(direction.toUpperCase()));
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}