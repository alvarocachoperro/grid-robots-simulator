package com.example.grid_robots_simulator.infrastructure.rest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RobotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldProcessRobotsInput() throws Exception {
        String input = """
                5 5
                1 2 N
                LMLMLMLMM
                3 3 E
                MMRMMRMRRM""";

        String expectedOutput = """
                1 3 N
                5 1 E""";

        mockMvc.perform(post("/api/robots")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(input))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedOutput));
    }
}
