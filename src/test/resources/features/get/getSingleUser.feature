Feature: Get Single User API

  Background:
    Given the API base URL is loaded from configuration

  Scenario: Verify the single user API returns success
    When I send a GET request to "/api/users/2"
    Then the response status code should be 200
    And the response should contain the field "email"
