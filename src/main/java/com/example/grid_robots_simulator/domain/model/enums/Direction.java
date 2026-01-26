package com.example.grid_robots_simulator.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Direction {
    NORTH(0, 1, "N"),
    EAST(1, 0, "E"),
    SOUTH(0, -1, "S"),
    WEST(-1, 0, "W");
    
    private final int dx;
    private final int dy;
    private final String abbreviation;
    
    public Direction turnLeft() {
        return switch (this) {
            case NORTH -> WEST;
            case EAST -> NORTH;
            case SOUTH -> EAST;
            case WEST -> SOUTH;
        };
    }
    
    public Direction turnRight() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        };
    }

    @Override
    public String toString() {
        return abbreviation;
    }

    public static Direction parse(String d) {
        return switch (d) {
            case "N" -> Direction.NORTH;
            case "E" -> Direction.EAST;
            case "S" -> Direction.SOUTH;
            case "W" -> Direction.WEST;
            default -> throw new IllegalArgumentException("Invalid direction: " + d);
        };
    }

    public static String format(Direction d) {
        return switch (d) {
            case NORTH -> "N";
            case EAST -> "E";
            case SOUTH -> "S";
            case WEST -> "W";
        };
    }
}
