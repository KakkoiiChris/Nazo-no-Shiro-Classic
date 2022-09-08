package kakkoiichris.nazonoshiro.castle;

import java.util.Arrays;

// Christian Alexander, 9/4/2022package kakkoiichris.nazonoshiro.castle;
public enum Direction {
    NONE {
        @Override
        public Direction getInverse() {
            return NONE;
        }
    },
    
    UP {
        @Override
        public Direction getInverse() {
            return DOWN;
        }
    },
    
    DOWN {
        @Override
        public Direction getInverse() {
            return UP;
        }
    },
    
    NORTH {
        @Override
        public Direction getInverse() {
            return SOUTH;
        }
    },
    
    EAST {
        @Override
        public Direction getInverse() {
            return WEST;
        }
    },
    
    SOUTH {
        @Override
        public Direction getInverse() {
            return NORTH;
        }
    },
    
    WEST {
        @Override
        public Direction getInverse() {
            return EAST;
        }
    };
    
    public abstract Direction getInverse();
    
    public static boolean isValid(String direction) {
        return Arrays.stream(values()).anyMatch(d -> d.name().equals(direction.toUpperCase()));
    }
    
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}