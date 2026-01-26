package com.example.grid_robots_simulator.domain.model;

import com.example.grid_robots_simulator.domain.model.enums.Direction;


public record RobotState(Position position, Direction direction) {}
