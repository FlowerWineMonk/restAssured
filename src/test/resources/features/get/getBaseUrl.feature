Feature: Verify Base API Availability

  Background:
    Given the API base URL is loaded from configuration

  Scenario: Verify the main API endpoint is reachable
    When I send a GET request to the base URL
    Then the response status code should be 200
