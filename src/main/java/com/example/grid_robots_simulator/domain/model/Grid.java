package com.example.grid_robots_simulator.domain.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Grid {
    private int sizeX;
    private int sizeY;
    private Set<Position> obstacles;


    public Grid(int dimensionX, int dimensionY, Set<Position> positions) {
        if (sizeX < 1 || sizeY < 1) {
            throw new IllegalArgumentException("Grid size must be >= 1");
        } else  {
            this.sizeX = dimensionX;
            this.sizeY = dimensionY;
            this.obstacles = positions;
        }
    }
    
    public static Grid create(int dimensionX, int dimensionY, Set<Position> obstacles) {
        return new Grid(dimensionX, dimensionY, Set.copyOf(Objects.requireNonNullElse(obstacles, Set.of())));
    }
    
    public boolean isValidMove(Position pos) {
        return pos.isValid(pos.x(), pos.y()) && !obstacles.contains(pos);
    }
}
