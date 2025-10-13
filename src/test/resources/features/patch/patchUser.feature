Feature: Update User API

  Background:
    Given the API base URL is loaded from configuration

  Scenario: Verify that updating a user returns a 200 status code and updated fields
    When I send a PATCH request to "/api/users/2" with body:
      """
      {
          "name": "morpheus",
          "job": "zion resident"
      }
      """
    Then the response status code should be 200
    And the response should contain the field "name"
    And the response should contain the field "job"
    And the response should contain the field "updatedAt"