package com.example.grid_robots_simulator.infrastructure.rest.mapper;

import com.example.grid_robots_simulator.domain.model.Grid;
import com.example.grid_robots_simulator.domain.model.Robot;
import com.example.grid_robots_simulator.domain.model.enums.Direction;
import com.example.grid_robots_simulator.infrastructure.rest.model.PositionDTO;
import com.example.grid_robots_simulator.infrastructure.rest.model.RobotDTO;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class RobotDTOMapperTest {

    @Test
    void shouldMapRobotDTOToDomain() {
        // Given
        PositionDTO positionDTO = PositionDTO.builder()
                .x(1)
                .y(2)
                .build();
        RobotDTO robotDTO = RobotDTO.builder()
                .initialPosition(positionDTO)
                .direction("N")
                .commands("MMR")
                .build();
        Grid grid = new Grid(5, 5, Collections.emptySet());

        // When
        Robot robot = RobotDTOMapper.mapToDomain(robotDTO, grid);

        // Then
        assertNotNull(robot);
        assertEquals(1, robot.getPosition().x());
        assertEquals(2, robot.getPosition().y());
        assertEquals(Direction.NORTH, robot.getDirection());
        assertEquals("MMR", robot.getCommands());
        assertEquals(grid, robot.getGrid());
    }

    @Test
    void constructorShouldThrowException() throws NoSuchMethodException {
        Constructor<RobotDTOMapper> constructor = RobotDTOMapper.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertEquals(IllegalStateException.class, exception.getCause().getClass());
        assertEquals("Utility class", exception.getCause().getMessage());
    }
}
