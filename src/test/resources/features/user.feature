Feature: User CRUD operations on Petstore API

  Scenario Outline: Create a new user
    Given I have user details with username "<username>", firstName "<firstName>", lastName "<lastName>", email "<email>", phone "<phone>", password "<password>", status <status>
    When I send a POST request to create the user
    Then the response status should be 200
    And the user should be created successfully

    Examples:
      | username       | firstName | lastName | email             | phone      | password     | status |
      | ujitha123      | Ujitha    | Yuzu     | ujitha@example.com| 1234567890 | password123  | 1      |
      | test123        | Test      | User     | test@example.com  | 9876543210 | testpass     | 1      |

  Scenario: Get an existing user
    Given I have an existing username "<username>"
    When I send a GET request to fetch the user
    Then the response status should be 200
    And the response username should be "<username>"

  Scenario: Update an existing user
    Given I have an existing username "<username>"
    And I update user details with firstName "UpdatedFirstName", lastName "UpdatedLastName", email "updated@example.com", phone "9999999999", password "newpass", status 2
    When I send a PUT request to update the user
    Then the response status should be 200

  Scenario: Delete an existing user
    Given I have an existing username "<username>"
    When I send a DELETE request to delete the user
    Then the response status should be 200
    And the deleted username should be "<username>"

  Scenario: Negative test for non-existing user
    Given I have a non-existing username "nonExistingUserXYZ"
    When I send a GET request to fetch the user
    Then the response status should be 404
    And the response message should be "User not found"
