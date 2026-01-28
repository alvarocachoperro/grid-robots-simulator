Feature: Grid Robot Simulator
  As a user of the simulator
  I want to send the grid dimensions and robot instructions
  To get their final positions

  Scenario: Process robots input successfully
    Given a robot simulator running
    When the following text input is sent:
      """
      5 5
      1 2 N
      LMLMLMLMM
      3 3 E
      MMRMMRMRRM
      """
    Then the response should be successful with the following content:
      """
      1 3 N
      5 1 E
      """
