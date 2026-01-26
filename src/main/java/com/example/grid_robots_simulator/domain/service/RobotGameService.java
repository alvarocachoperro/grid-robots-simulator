package com.example.grid_robots_simulator.domain.service;

import com.example.grid_robots_simulator.domain.model.Grid;
import com.example.grid_robots_simulator.domain.model.Position;
import com.example.grid_robots_simulator.domain.model.Robot;
import com.example.grid_robots_simulator.domain.model.enums.Direction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RobotGameService {

    public String processInput(String input) {
        String[] lines = input.split("\n");
        if (lines.length == 0) return "";

        String[] gridDims = lines[0].trim().split("\\s+");
        int maxX = Integer.parseInt(gridDims[0]);
        int maxY = Integer.parseInt(gridDims[1]);

        // Usamos maxX y maxY directamente como límites.
        // Si el input dice "5 5", el robot puede estar en (5,5).
        // Grid.isValid utiliza pos.x < gridSize && pos.y < gridSize.
        // Por lo tanto, gridSize debe ser maxX + 1.
        int gridSizeX = maxX ;//+ 1;
        int gridSizeY = maxY ;//+ 1;

        var grid = Grid.create(gridSizeX, gridSizeY, Set.of());
        List<Robot> robots = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < lines.length; i += 2) {
            if (i + 1 >= lines.length) break;

            String[] posParts = lines[i].trim().split("\\s+");
            int x = Integer.parseInt(posParts[0]);
            int y = Integer.parseInt(posParts[1]);
            Direction dir = Direction.parse(posParts[2]);

            robots.add(new Robot(i,lines[i + 1].trim(),new Position(x, y), dir, grid));
        }
        if (!robots.isEmpty()) {
            grid.setObstacles(robots.stream().map(Robot::getPosition).collect(Collectors.toSet()));
            for (Robot robot : robots) {
                robot.execute();
                result.append(robot.getPosition().x())
                      .append(" ")
                      .append(robot.getPosition().y())
                      .append(" ")
                      .append(robot.getDirection().getAbbreviation())
                      .append("\n");

            }
        }
        return result.toString().trim();
    }


    
    // Ejemplo de uso
    public static void main(String[] args) {
        var service = new RobotGameService();
        String input = "5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM";
        System.out.println(service.processInput(input));
    }
}
