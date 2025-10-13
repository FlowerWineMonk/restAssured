Feature: Delete User API

  Background:
    Given the API base URL is loaded from configuration

  Scenario: Verify that deleting a user returns a 204 status code
    When I send a DELETE request to "/api/users/2"
    Then the response status code should be 204
