package com.example.grid_robots_simulator.domain.service;

import com.example.grid_robots_simulator.domain.model.Grid;
import com.example.grid_robots_simulator.domain.model.Position;
import com.example.grid_robots_simulator.domain.model.Robot;
import com.example.grid_robots_simulator.domain.model.enums.Direction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RobotGameServiceTest {

    @Autowired
    private RobotGameService robotGameService;

    private static void assertRobot(Robot robot, int x, int y, Direction direction) {
        assertEquals(x, robot.getPosition().x());
        assertEquals(y, robot.getPosition().y());
        assertEquals(direction, robot.getDirection());
    }

    @Test
    void testProcessInputSingleRobot() {
        var grid = Grid.builder().sizeX(5).sizeY(5).obstacles(Set.of()).build();
        var robot = Robot.builder()
                .position(Position.builder()
                        .x(1)
                        .y(2).build())
                .direction(Direction.NORTH)
                .commands("LMLMLMLMM")
                .grid(grid)
                .build();
        List<Robot> robots = robotGameService.processInput(Collections.singletonList(robot), grid);
        assertEquals(1, robots.size());
        assertRobot(robots.getFirst(), 1, 3, Direction.NORTH);
    }

    @Test
    void testProcessInputMultipleRobots() {
        var grid = Grid.builder().sizeX(5).sizeY(5).obstacles(Set.of()).build();
        var robotN = Robot.builder()
                .position(Position.builder()
                        .x(1)
                        .y(2).build())
                .direction(Direction.NORTH)
                .commands("LMLMLMLMM")
                .grid(grid)
                .build();
        var robotE = Robot.builder()
                .position(Position.builder()
                        .x(3)
                        .y(3).build())
                .direction(Direction.EAST)
                .commands("MMRMMRMRRM")
                .grid(grid)
                .build();
        List<Robot> robots = robotGameService.processInput(Arrays.asList(robotN, robotE), grid);
        assertEquals(2, robots.size());
        assertRobot(robots.get(0), 1, 3, Direction.NORTH);
        assertRobot(robots.get(1), 5, 1, Direction.EAST);
    }

    @Test
    void testProcessInputEmptyInput() {
        assertEquals(List.of(), robotGameService.processInput(
                List.of(),
                Grid.builder().sizeX(1).sizeY(1).obstacles(Set.of()).build()));
    }

    @Test
    void testProcessInputBoundaryMove() {
        var grid = Grid.builder().sizeX(5).sizeY(5).obstacles(Set.of()).build();
        var robotN = Robot.builder()
                .position(Position.builder()
                        .x(5)
                        .y(5).build())
                .direction(Direction.NORTH)
                .commands("M")
                .grid(grid)
                .build();
        // Robot tries to move out of the grid to the north
        List<Robot> robots = robotGameService.processInput(Collections.singletonList(robotN), grid);
        assertEquals(1, robots.size());
        assertRobot(robots.getFirst(), 5, 5, Direction.NORTH);
    }

    @Test
    void testProcessInputBoundaryMoveSouth() {
        var grid = Grid.builder().sizeX(5).sizeY(5).obstacles(Set.of()).build();
        var robotS = Robot.builder()
                .position(Position.builder()
                        .x(0)
                        .y(0).build())
                .direction(Direction.SOUTH)
                .commands("M")
                .grid(grid)
                .build();
        // Robot tries to move out of the grid to the south
        List<Robot> robots = robotGameService.processInput(Collections.singletonList(robotS), grid);
        assertEquals(1, robots.size());
        assertRobot(robots.getFirst(), 0, 0, Direction.SOUTH);
    }
}
