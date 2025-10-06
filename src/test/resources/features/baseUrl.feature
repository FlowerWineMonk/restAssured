Feature: Verify Base API Availability

  Scenario: Verify the main API endpoint is reachable
    Given the base API URL is "https://reqres.in/"
    When I send a GET request to the base URL
    Then the response status code should be 200
    And the response status code should match the expected one
