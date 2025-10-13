Feature: Update user with PUT request

  Background:
    Given the API base URL is loaded from configuration

  Scenario: Successfully update user information using PUT
    When I send a PUT request to "/api/users/2" with body:
      """
      {
          "name": "morpheus",
          "job": "zion resident"
      }
      """
    Then the response status code should be 200
    And the response body should contain:
      """
      {
          "name": "morpheus",
          "job": "zion resident"
      }
      """
    And the response should contain a field "updatedAt"