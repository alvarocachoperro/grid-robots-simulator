package com.example.grid_robots_simulator.infraestructure.rest.mapper;

import com.example.grid_robots_simulator.domain.model.Grid;
import com.example.grid_robots_simulator.domain.model.Position;
import com.example.grid_robots_simulator.domain.model.Robot;
import com.example.grid_robots_simulator.domain.model.enums.Direction;
import com.example.grid_robots_simulator.infraestructure.rest.model.RobotDTO;

public class RobotDTOMapper {

    private RobotDTOMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Robot mapToDomain(RobotDTO robot, Grid grid) {
        return Robot.builder()
                .position(Position.builder()
                        .x(robot.getInitialPosition().getX())
                        .y(robot.getInitialPosition().getY())
                        .build())
                .direction(Direction.parse(robot.getDirection()))
                .commands(robot.getCommands())
                .grid(grid)
                .build();
    }

}
