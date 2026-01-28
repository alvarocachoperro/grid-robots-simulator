package com.example.grid_robots_simulator.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@CucumberContextConfiguration
@SpringBootTest
@AutoConfigureMockMvc
public class RobotSteps {

    @Autowired
    private MockMvc mockMvc;

    private ResultActions resultActions;

    @Given("a robot simulator running")
    public void aRobotSimulatorRunning() {
        // Spring context is loaded automatically
    }

    @When("the following text input is sent:")
    public void theFollowingTextInputIsSent(String input) throws Exception {
        resultActions = mockMvc.perform(post("/api/robots")
                .contentType(MediaType.TEXT_PLAIN)
                .content(input));
    }

    @Then("the response should be successful with the following content:")
    public void theResponseShouldBeSuccessfulWithTheFollowingContent(String expectedOutput) throws Exception {
        resultActions.andExpect(status().isOk())
                .andExpect(content().string(expectedOutput));
    }
}
