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
                .log().all() // Log request
                .when()
                .post(Endpoints.USER)
                .then()
                .log().all() // Log response
                .statusCode(200)
                .body("message", notNullValue());

        test.pass("CREATE User passed for username: " + username);
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

        test.pass("GET User passed for username: " + username);
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
                .log().all()
                .when()
                .put(Endpoints.USER + "/" + username)
                .then()
                .log().all()
                .statusCode(200)
                .body("message", notNullValue());

        test.pass("UPDATE User passed for username: " + username);
    }

    @Test(priority = 4)
    public void deleteUser() {
        when()
                .delete(Endpoints.USER + "/" + username)
                .then()
                .log().all()
                .statusCode(200)
                .body("message", equalTo(username));

        test.pass("DELETE User passed for username: " + username);
    }

    @Test
    public void negativeTest() {
        when()
                .get(Endpoints.USER + "/nonExistingUser123")
                .then()
                .log().all()
                .statusCode(404);

        test.pass("NEGATIVE Test passed for non-existing user");
    }
}
