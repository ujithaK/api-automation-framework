Feature: User CRUD operations

  Background:
    Given the base URI is set

  Scenario: Create a new user
    When I create a user with name "Ujitha" and job "QA"
    Then the user should be created successfully
    And I store the user ID

  Scenario: Get user by ID
    Given I have a user ID
    When I get the user details
    Then the response status code should be 200
    And the name should be "Ujitha"

  Scenario: Update user details
    Given I have a user ID
    When I update the user name to "Updated" and job to "Lead"
    Then the response status code should be 200
    And the job should be "Lead"

  Scenario: Delete user
    Given I have a user ID
    When I delete the user
    Then the response status code should be 204

  Scenario: Negative test for non-existent user
    When I try to get user with ID 9999
    Then the response status code should be 404
