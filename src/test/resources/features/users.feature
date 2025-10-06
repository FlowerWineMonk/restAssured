Feature: Get user API

  Scenario: Verify user list API returns success
    Given the base API is "https://reqres.in"
    When I send a GET request to "/api/users/2"
    Then the response status code should be 200
    And the response should contain "email"
