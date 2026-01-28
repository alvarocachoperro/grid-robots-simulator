package com.example.grid_robots_simulator.infrastructure.rest.mapper;

import com.example.grid_robots_simulator.domain.model.Grid;
import com.example.grid_robots_simulator.domain.model.Position;
import com.example.grid_robots_simulator.domain.model.Robot;
import com.example.grid_robots_simulator.domain.model.enums.Direction;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RobotsResponseMapperTest {

    @Test
    void shouldMapRobotsToPlainText() {
        Grid grid = new Grid(5, 5, Collections.emptySet());
        Robot robot1 = new Robot(new Position(1, 2), Direction.NORTH, grid, "LMLM");
        Robot robot2 = new Robot(new Position(3, 3), Direction.EAST, grid, "MMR");

        List<Robot> robots = List.of(robot1, robot2);

        String result = RobotsResponseMapper.toPlainText(robots);

        assertEquals("1 2 N\n3 3 E", result);
    }

    @Test
    void shouldReturnEmptyStringWhenListIsEmpty() {
        String result = RobotsResponseMapper.toPlainText(Collections.emptyList());

        assertEquals("", result);
    }

    @Test
    void constructorShouldThrowException() throws NoSuchMethodException {
        Constructor<RobotsResponseMapper> constructor = RobotsResponseMapper.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertInstanceOf(IllegalStateException.class, exception.getCause());
        assertEquals("Utility class", exception.getCause().getMessage());
    }
}
