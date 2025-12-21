package stepdefs;

import constants.Endpoints;
import io.cucumber.java.en.*;
import pojo.User;
import io.restassured.response.Response;
import base.BaseTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserSteps extends BaseTest {

    private static String username;
    private static Response response;

    @Given("I have a unique username")
    public void generateUniqueUsername() {
        username = "ujitha" + System.currentTimeMillis();
    }

    @When("I create a user with all details")
    public void createUser() {
        User user = new User(
                username,
                "Ujitha",
                "Yuzu",
                "ujitha@example.com",
                "1234567890",
                "password123",
                1
        );

        response = given()
                .contentType("application/json")
                .body(user)
                .when()
                .post(Endpoints.USER);
    }

    @Then("the user should be created successfully")
    public void verifyUserCreated() {
        response.then()
                .statusCode(200)
                .body("message", notNullValue());
    }

    @When("I get the user details")
    public void getUser() {
        response = given()
                .log().all()
                .when()
                .get(Endpoints.USER + "/" + username);
    }

    @Then("the user details should match the username")
    public void verifyUsername() {
        response.then()
                .log().all()
                .statusCode(200)
                .body("username", equalTo(username));
    }

    @When("I update the user with new details")
    public void updateUser() {
        User updatedUser = new User(
                username,
                "UpdatedFirstName",
                "UpdatedLastName",
                "updated@example.com",
                "9876543210",
                "newpassword",
                2
        );

        response = given()
                .contentType("application/json")
                .body(updatedUser)
                .when()
                .put(Endpoints.USER + "/" + username);
    }

    @Then("the user should be updated successfully")
    public void verifyUserUpdated() {
        response.then()
                .statusCode(200)
                .body("message", notNullValue());
    }

    @When("I delete the user")
    public void deleteUser() {
        response = when()
                .delete(Endpoints.USER + "/" + username);
    }

    @Then("the user should be deleted successfully")
    public void verifyUserDeleted() {
        response.then()
                .statusCode(200)
                .body("message", equalTo(username));
    }

    @When("I try to get a non-existing user {string}")
    public void negativeTest(String nonExistingUser) {
        response = when()
                .get(Endpoints.USER + "/" + nonExistingUser);
    }

    @Then("the response status code should be {int}")
    public void verifyNegativeStatusCode(int statusCode) {
        response.then()
                .statusCode(statusCode);
    }
}
