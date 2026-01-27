package com.example.grid_robots_simulator.infrastructure.rest.mapper;

import com.example.grid_robots_simulator.domain.model.Grid;
import com.example.grid_robots_simulator.infrastructure.rest.model.GridDTO;

import java.util.Set;

public class GridDTOMapper {

    private GridDTOMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Grid mapToDomain(GridDTO grid) {
        return Grid.builder()
                .sizeX(grid.getDimensions().getX())
                .sizeY(grid.getDimensions().getY())
                .obstacles(Set.of())
                .build();
    }
}
