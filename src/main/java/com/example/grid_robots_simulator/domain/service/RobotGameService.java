package com.example.grid_robots_simulator.domain.service;

import com.example.grid_robots_simulator.domain.model.Grid;
import com.example.grid_robots_simulator.domain.model.Robot;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RobotGameService {

    public List<Robot> processInput(List<Robot> robots, Grid grid) {
        robots.forEach(robot -> robot.setGrid(grid));
        if (!robots.isEmpty()) {
            grid.setObstacles(robots.stream().map(Robot::getPosition).collect(Collectors.toSet()));
            for (Robot robot : robots) {
                robot.execute();
            }
        }
        return robots;
    }
}
