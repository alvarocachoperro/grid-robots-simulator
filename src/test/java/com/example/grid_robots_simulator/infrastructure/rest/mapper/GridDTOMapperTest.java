package com.example.grid_robots_simulator.infrastructure.rest.mapper;

import com.example.grid_robots_simulator.domain.model.Grid;
import com.example.grid_robots_simulator.infrastructure.rest.model.GridDTO;
import com.example.grid_robots_simulator.infrastructure.rest.model.PositionDTO;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GridDTOMapperTest {

    @Test
    void shouldMapGridDTOToDomain() {
        // Given
        PositionDTO dimensions = PositionDTO.builder()
                .x(10)
                .y(10)
                .build();
        GridDTO gridDTO = GridDTO.builder()
                .dimensions(dimensions)
                .build();

        // When
        Grid result = GridDTOMapper.mapToDomain(gridDTO);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getSizeX()).isEqualTo(10);
        assertThat(result.getSizeY()).isEqualTo(10);
        assertThat(result.getObstacles()).isEmpty();
    }

    @Test
    void constructorShouldThrowException() throws NoSuchMethodException {
        Constructor<GridDTOMapper> constructor = GridDTOMapper.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThatThrownBy(constructor::newInstance)
                .isInstanceOf(InvocationTargetException.class)
                .hasCauseInstanceOf(IllegalStateException.class)
                .hasStackTraceContaining("Utility class");
    }
}
