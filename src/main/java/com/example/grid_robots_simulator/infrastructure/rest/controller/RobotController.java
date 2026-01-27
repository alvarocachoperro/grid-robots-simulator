package com.example.grid_robots_simulator.infrastructure.rest.controller;

import com.example.grid_robots_simulator.domain.model.Robot;
import com.example.grid_robots_simulator.domain.service.RobotGameService;
import com.example.grid_robots_simulator.infrastructure.rest.api.RobotSimulatorApi;
import com.example.grid_robots_simulator.infrastructure.rest.mapper.GridDTOMapper;
import com.example.grid_robots_simulator.infrastructure.rest.mapper.RobotDTOMapper;
import com.example.grid_robots_simulator.infrastructure.rest.mapper.RobotsInputDTOMapper;
import com.example.grid_robots_simulator.infrastructure.rest.mapper.RobotsResponseMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RobotController implements RobotSimulatorApi {
    RobotGameService robotGameService;

    @Override
    public ResponseEntity<String> simulate(String input) {
        var robotAggregate = RobotsInputDTOMapper.mapToDTO(input);
        var grid = GridDTOMapper.mapToDomain(robotAggregate.getGrid());
        List<Robot> robots = robotGameService.processInput(
                robotAggregate.getRobots().stream().map(robot -> RobotDTOMapper.mapToDomain(robot, grid)).toList(),
                grid);
        return ResponseEntity.ok(RobotsResponseMapper.toPlainText(robots));
    }
}
