Feature: Get User List API

  Background:
    Given the API base URL is loaded from configuration

  Scenario: Verify that the user list API returns a valid list of users
    When I send a GET request to "/api/users?page=2"
    Then the response status code should be 200
    And the response should contain a list under "data"
    And each object in "data" should have the fields:
      | id         |
      | email      |
      | first_name |
      | last_name  |
    And one of the users should have the email "michael.lawson@reqres.in"
