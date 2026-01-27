package com.example.grid_robots_simulator.infrastructure.rest.mapper;

import com.example.grid_robots_simulator.domain.model.Robot;

import java.util.List;
import java.util.stream.Collectors;

public final class RobotsResponseMapper {
  private RobotsResponseMapper() {
    throw new IllegalStateException("Utility class");
  }
  public static String toPlainText(List<Robot> robots) {
    return robots.stream()
            .map(r -> r.getPosition().x() + " " + r.getPosition().y() + " " + r.getDirection().getAbbreviation())
            .collect(Collectors.joining("\n"));
  }
}