package com.example.grid_robots_simulator.infra.rest;
import com.example.grid_robots_simulator.domain.service.RobotGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/robots")
@RequiredArgsConstructor
public class RobotController {
    private final RobotGameService robotGameService;
    @PostMapping(consumes = "text/plain", produces = "text/plain")
    public String simulate(@RequestBody String input) {
        return robotGameService.processInput(input);
    }
}
