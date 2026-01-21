package stepdefs;

import constants.Endpoints;
import io.restassured.response.Response;
import pojo.User;
import utils.RequestSpecUtil;
import utils.ResponseSpecUtil;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import io.cucumber.java.en.*;

public class UserSteps {

    private User user;
    private String username;
    private Response response;

    // ------------------ CREATE USER ------------------
    @Given("I have user details with username {string}, firstName {string}, lastName {string}, email {string}, phone {string}, password {string}, status {int}")
    public void i_have_user_details(String username, String firstName, String lastName,
                                    String email, String phone, String password, int status) {
        this.username = username;
        this.user = new User(username, firstName, lastName, email, phone, password, status);
    }

    @When("I send a POST request to create the user")
    public void i_send_post_request_to_create_user() {
        response = given()
                .spec(RequestSpecUtil.requestSpec())
                .body(user)
                .when()
                .post(Endpoints.USER);

        response.then().spec(ResponseSpecUtil.responseSpec200());
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer statusCode) {
        assertEquals(response.getStatusCode(), statusCode.intValue());
    }

    @Then("the user should be created successfully")
    public void the_user_should_be_created() {
        String message = response.jsonPath().getString("message");
        assertEquals(message.contains(username), true, "User creation message mismatch!");
    }

    // ------------------ GET USER ------------------
    @Given("I have an existing username {string}")
    public void i_have_existing_username(String username) {
        this.username = username;
    }

    @When("I send a GET request to fetch the user")
    public void i_send_get_request_to_fetch_user() {
        response = given()
                .spec(RequestSpecUtil.requestSpec())
                .when()
                .get(Endpoints.USER + "/" + username);
    }

    @Then("the response username should be {string}")
    public void the_response_username_should_be(String expectedUsername) {
        User fetchedUser = response.as(User.class);
        assertEquals(fetchedUser.getUsername(), expectedUsername, "Fetched username mismatch!");
    }

    // ------------------ UPDATE USER ------------------
    @Given("I update user details with firstName {string}, lastName {string}, email {string}, phone {string}, password {string}, status {int}")
    public void i_update_user_details(String firstName, String lastName, String email,
                                      String phone, String password, int status) {
        user = new User(username, firstName, lastName, email, phone, password, status);
    }

    @When("I send a PUT request to update the user")
    public void i_send_put_request_to_update_user() {
        response = given()
                .spec(RequestSpecUtil.requestSpec())
                .body(user)
                .when()
                .put(Endpoints.USER + "/" + username);

        response.then().spec(ResponseSpecUtil.responseSpec200());
    }

    // ------------------ DELETE USER ------------------
    @When("I send a DELETE request to delete the user")
    public void i_send_delete_request_to_delete_user() {
        response = given()
                .spec(RequestSpecUtil.requestSpec())
                .when()
                .delete(Endpoints.USER + "/" + username);

        response.then().spec(ResponseSpecUtil.responseSpec200());
    }

    @Then("the deleted username should be {string}")
    public void the_deleted_username_should_be(String expectedUsername) {
        String message = response.jsonPath().getString("message");
        assertEquals(message, expectedUsername, "Deleted username mismatch!");
    }

    // ------------------ NEGATIVE TEST ------------------
    @Given("I have a non-existing username {string}")
    public void i_have_non_existing_username(String username) {
        this.username = username;
    }

    @Then("the response message should be {string}")
    public void the_response_message_should_be(String message) {
        assertEquals(response.jsonPath().getString("User not found"), message, "Error message mismatch!");
    }
}
