package com.example.grid_robots_simulator.domain.model;

import com.example.grid_robots_simulator.domain.model.enums.Direction;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Robot {
    private Position position;
    private Direction direction;
    private int num;
    private final Grid grid;
    private final String commands;
    private final List<RobotState> history = new ArrayList<>();
    
    public Robot(int numero, String moves, Position initialPos, Direction initialDir, Grid grid) {
        this.num = numero;
        this.commands = moves;
        this.position = initialPos;
        this.direction = initialDir;
        this.grid = grid;
        validateInitialPosition();
        this.history.add(new RobotState(position, direction, 'I'));
    }
    
    private void validateInitialPosition() {
        if (!grid.isValidMove(position)) {
            throw new IllegalArgumentException("Invalid initial position: " + position);
        }
    }
    
    public void execute() {
        if (commands == null) return;
        for (char cmd : commands.toCharArray()) {
            executeCommand(cmd);
        }
    }
    
    private void executeCommand(char cmd) {
        switch (cmd) {
            case 'M':
                move();
                break;
            case 'L':
                turnLeft();
                break;
            case 'R':
                turnRight();
                break;
            default:/* Ignore invalid commands */
        }
    }
    
    private void move() {
        Position newPosition = position.move(direction);
        Position oldPosition = position;
        if (grid.isValidMove(newPosition)) {
            boolean removed = grid.getObstacles()
                    .removeIf(o -> o.x() == oldPosition.x() && o.y() == oldPosition.y());

            if (removed) {
                grid.getObstacles().add(newPosition);
            }
            position = newPosition;
        }
        history.add(new RobotState(position, direction, 'M'));

    }
    
    private void turnLeft() {
        direction = direction.turnLeft();
        history.add(new RobotState(position, direction, 'L'));
    }
    
    private void turnRight() {
        direction = direction.turnRight();
        history.add(new RobotState(position, direction, 'R'));
    }
    

}
