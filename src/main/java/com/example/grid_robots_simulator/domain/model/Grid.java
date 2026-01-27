package com.example.grid_robots_simulator.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Builder
public class Grid {
    private int sizeX;
    private int sizeY;
    private Set<Position> obstacles;


    public Grid(int dimensionX, int dimensionY, Set<Position> positions) {
        if (dimensionX < 1 || dimensionY < 1) {
            throw new IllegalArgumentException("Grid size must be >= 1");
        } else  {
            this.sizeX = dimensionX;
            this.sizeY = dimensionY;
            this.obstacles = positions;
        }
    }

    
    public boolean isValidMove(Position pos) {
        return pos.isValid(this.sizeX, this.sizeY) && !obstacles.contains(pos);
    }
}
