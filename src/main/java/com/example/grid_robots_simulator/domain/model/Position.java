package com.example.grid_robots_simulator.domain.model;

import com.example.grid_robots_simulator.domain.model.enums.Direction;
import lombok.Builder;

@Builder
public record Position(int x, int y) {

    public Position move(Direction direction) {
        return Position.builder()
                .x(x + direction.getDx())
                .y(y + direction.getDy())
                .build();
    }
    
    public boolean isValid(int posX, int posY) {
        return x >= 0 && x <= posX && y >= 0 && y <= posY;
    }
}
