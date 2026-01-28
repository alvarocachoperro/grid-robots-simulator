package com.example.grid_robots_simulator.infrastructure.rest.mapper;

import com.example.grid_robots_simulator.infrastructure.rest.model.RobotsInputDTO;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class RobotsInputDTOMapperTest {

    @Test
    void shouldMapTextInputToDTO() {
        String input = "5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM";

        RobotsInputDTO result = RobotsInputDTOMapper.mapToDTO(input);

        assertNotNull(result);
        assertNotNull(result.getGrid());
        assertEquals(5, result.getGrid().getDimensions().getX());
        assertEquals(5, result.getGrid().getDimensions().getY());

        assertEquals(2, result.getRobots().size());

        assertEquals(1, result.getRobots().get(0).getInitialPosition().getX());
        assertEquals(2, result.getRobots().get(0).getInitialPosition().getY());
        assertEquals("N", result.getRobots().get(0).getDirection());
        assertEquals("LMLMLMLMM", result.getRobots().get(0).getCommands());

        assertEquals(3, result.getRobots().get(1).getInitialPosition().getX());
        assertEquals(3, result.getRobots().get(1).getInitialPosition().getY());
        assertEquals("E", result.getRobots().get(1).getDirection());
        assertEquals("MMRMMRMRRM", result.getRobots().get(1).getCommands());
    }

    @Test
    void shouldReturnNullWhenInputIsNullOrEmpty() {
        assertNull(RobotsInputDTOMapper.mapToDTO(null));
        assertNull(RobotsInputDTOMapper.mapToDTO(""));
        assertNull(RobotsInputDTOMapper.mapToDTO("   "));
    }

    @Test
    void constructorShouldThrowException() throws NoSuchMethodException {
        Constructor<RobotsInputDTOMapper> constructor = RobotsInputDTOMapper.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertInstanceOf(IllegalStateException.class, exception.getCause());
        assertEquals("Utility class", exception.getCause().getMessage());
    }
}
