package com.example.grid_robots_simulator.infraestructure.rest.mapper;

import com.example.grid_robots_simulator.infraestructure.rest.model.GridDTO;
import com.example.grid_robots_simulator.infraestructure.rest.model.PositionDTO;
import com.example.grid_robots_simulator.infraestructure.rest.model.RobotDTO;
import com.example.grid_robots_simulator.infraestructure.rest.model.RobotsInputDTO;

import java.util.ArrayList;
import java.util.List;

public class RobotsInputDTOMapper {

    private RobotsInputDTOMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static RobotsInputDTO mapToDTO(String textInput) {

        if (textInput == null || textInput.trim().isEmpty()) return null;
        String[] lines = textInput.split("\n");

        String[] gridDims = lines[0].trim().split("\\s+");

        var grid = GridDTO.builder()
                .dimensions(PositionDTO.builder()
                        .x(Integer.parseInt(gridDims[0]))
                        .y(Integer.parseInt(gridDims[1])).build())
                .build();

        List<RobotDTO> robots = new ArrayList<>();
        for (int i = 1; i < lines.length; i += 2) {
            if (i + 1 >= lines.length) break;

            String[] posParts = lines[i].trim().split("\\s+");

            robots.add(RobotDTO.builder()
                    .initialPosition(PositionDTO.builder()
                            .x(Integer.parseInt(posParts[0]))
                            .y(Integer.parseInt(posParts[1]))
                            .build())
                    .direction(posParts[2])
                    .commands(lines[i + 1].trim())
                    .build());
        }

        return RobotsInputDTO.builder()
                .robots(robots)
                .grid(grid)
                .build();
    }
}
