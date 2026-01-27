package com.example.grid_robots_simulator.infraestructure.rest.controller;

import com.example.grid_robots_simulator.domain.model.Robot;
import com.example.grid_robots_simulator.domain.service.RobotGameService;
import com.example.grid_robots_simulator.infraestructure.rest.api.RobotSimulatorApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RobotController implements RobotSimulatorApi {
    private final RobotGameService robotGameService;

    @Override
    public ResponseEntity<String> simulate(String input) {

        List<Robot> robots = robotGameService.processInput(input != null ? input : "");
        String result = robots.stream()
                .map(r -> r.getPosition().x() + " " + r.getPosition().y() + " " + r.getDirection().getAbbreviation())
                .collect(Collectors.joining("\n"));
        return ResponseEntity.ok(result);
    }
}
