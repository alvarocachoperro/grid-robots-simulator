package com.example.grid_robots_simulator.domain.model;

import com.example.grid_robots_simulator.domain.model.enums.Direction;


public record Position(int x, int y) {

    public Position move(Direction direction) {
        return new Position(
            x + direction.getDx(),
            y + direction.getDy()
        );
    }
    
    public boolean isValid(int posX, int posY) {
        return x >= 0 && x <= posX && y >= 0 && y <= posY;
    }
}
