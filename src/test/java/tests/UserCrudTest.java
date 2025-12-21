package tests;

import base.BaseTest;
import constants.Endpoints;
import org.testng.annotations.Test;
import pojo.User;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserCrudTest extends BaseTest {

    String username = "ujitha" + System.currentTimeMillis(); // unique username for testing

    @Test(priority = 1)
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

        given()
                .contentType("application/json")
                .body(user)
                .when()
                .post(Endpoints.USER)
                .then()
                .statusCode(200)
                .body("message", notNullValue());
    }

    @Test(priority = 2)
    public void getUser() {
        given()
                .log().all()
                .when()
                .get(Endpoints.USER + "/" + username)
                .then()
                .log().all()
                .statusCode(200)
                .body("username", equalTo(username));
    }

    @Test(priority = 3)
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

        given()
                .contentType("application/json")
                .body(updatedUser)
                .when()
                .put(Endpoints.USER + "/" + username)
                .then()
                .statusCode(200)
                .body("message", notNullValue());
    }

    @Test(priority = 4)
    public void deleteUser() {
        when()
                .delete(Endpoints.USER + "/" + username)
                .then()
                .statusCode(200)
                .body("message", equalTo(username));
    }

    @Test
    public void negativeTest() {
        when()
                .get(Endpoints.USER + "/nonExistingUser123")
                .then()
                .statusCode(404);
    }
}
