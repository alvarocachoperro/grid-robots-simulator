package com.example.grid_robots_simulator.domain.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class RobotGameServiceTest {

    @Autowired
    private RobotGameService robotGameService;

    @Test
    void testProcessInputSingleRobot() {
        assertEquals("1 3 N", robotGameService.processInput("5 5\n1 2 N\nLMLMLMLMM"));
    }

    @Test
    void testProcessInputMultipleRobots() {
        assertEquals("1 3 N\n5 1 E", robotGameService.processInput("5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM"));
    }

    @Test
    void testProcessInputEmptyInput() {
        assertEquals("", robotGameService.processInput(""));
    }

    @Test
    void testProcessInputBoundaryMove() {
        // Robot tries to move out of the grid to the north
        assertEquals("5 5 N", robotGameService.processInput("5 5\n5 5 N\nM"));
    }

    @Test
    void testProcessInputBoundaryMoveSouth() {
        // Robot tries to move out of the grid to the south
        assertEquals("0 0 S", robotGameService.processInput("5 5\n0 0 S\nM"));
    }

    @Test
    void testProcessInputInvalidFormat() {
        assertThrows(Exception.class, () -> robotGameService.processInput("invalid"));
    }
}