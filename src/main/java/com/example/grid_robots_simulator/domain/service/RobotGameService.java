package com.example.grid_robots_simulator.domain.service;

import com.example.grid_robots_simulator.domain.model.Grid;
import com.example.grid_robots_simulator.domain.model.Position;
import com.example.grid_robots_simulator.domain.model.Robot;
import com.example.grid_robots_simulator.domain.model.enums.Direction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RobotGameService {

    public List<Robot> processInput(String input) {
        if (input == null || input.trim().isEmpty()) return Collections.emptyList();
        String[] lines = input.split("\n");

        String[] gridDims = lines[0].trim().split("\\s+");
        int maxX = Integer.parseInt(gridDims[0]);
        int maxY = Integer.parseInt(gridDims[1]);

        int gridSizeX = maxX ;
        int gridSizeY = maxY ;

        var grid = Grid.create(gridSizeX, gridSizeY, Set.of());
        List<Robot> robots = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < lines.length; i += 2) {
            if (i + 1 >= lines.length) break;

            String[] posParts = lines[i].trim().split("\\s+");
            int x = Integer.parseInt(posParts[0]);
            int y = Integer.parseInt(posParts[1]);
            Direction dir = Direction.parse(posParts[2]);

            robots.add(new Robot(lines[i + 1].trim(),new Position(x, y), dir, grid));
        }
        if (!robots.isEmpty()) {
            grid.setObstacles(robots.stream().map(Robot::getPosition).collect(Collectors.toSet()));
            for (Robot robot : robots) {
                robot.execute();
            }
        }
        return robots;
    }
}
