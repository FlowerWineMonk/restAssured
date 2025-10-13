Feature: Create User API

  Background:
    Given the API base URL is loaded from configuration

  Scenario: Verify that creating a user returns a 201 status code and correct fields
    When I send a POST request to "/api/users" with body:
      """
      {
          "name": "morpheus",
          "job": "leader"
      }
      """
    Then the response status code should be 201
    And the response should contain the field "name"
    And the response should contain the field "job"
    And the response should contain the field "id"
    And the response should contain the field "createdAt"
