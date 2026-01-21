package tests;

import base.BaseTest;
import constants.Endpoints;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojo.User;
import utils.RequestSpecUtil;
import utils.ResponseSpecUtil;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class UserCrudTest extends BaseTest {

    private String username;

    // ---------- DATA PROVIDER ----------
    @DataProvider(name = "userData")
    public Object[][] userData() {
        long timestamp = System.currentTimeMillis();
        return new Object[][]{
                {"ujitha" + timestamp, "Ujitha", "Yuzu", "ujitha@example.com", "1234567890", "password123", 1},
                {"manasa" + timestamp, "manasa", "Mansu", "manu@example.com", "9876543210", "manu123", 1}
        };
    }

    // ---------- CREATE USER ----------
    @Test(priority = 1, dataProvider = "userData")
    public void testCreateUser(String username, String firstName, String lastName,
                               String email, String phone, String password, int status) {

        this.username = username;

        User user = new User(username, firstName, lastName, email, phone, password, status);

        Response response = given()
                .spec(RequestSpecUtil.requestSpec())
                .body(user)
                .log().all()
                .when()
                .post(Endpoints.USER);

        response.then().log().all().spec(ResponseSpecUtil.responseSpec200());

        test.pass("User created successfully: " + username);
    }

    // ---------- GET USER ----------
    @Test(priority = 2, dependsOnMethods = "testCreateUser")
    public void testGetUser() {
        Response response = given()
                .spec(RequestSpecUtil.requestSpec())
                .log().all()
                .when()
                .get(Endpoints.USER + "/" + username);

        response.then().log().all().spec(ResponseSpecUtil.responseSpec200());

        User fetchedUser = response.as(User.class);

        assertEquals(fetchedUser.getUsername(), username);

        // Schema validation
        response.then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/user-schema.json"));

        test.pass("User fetched successfully: " + username);
    }

    // ---------- UPDATE USER ----------
    @Test(priority = 3, dependsOnMethods = "testGetUser")
    public void testUpdateUser() {
        User updatedUser = new User(username, "UpdatedFirstName", "UpdatedLastName",
                "updated@example.com", "9999999999", "newpass", 2);

        // PUT request to update user
        given()
                .spec(RequestSpecUtil.requestSpec())
                .body(updatedUser)
                .log().all()
                .when()
                .put(Endpoints.USER + "/" + username)
                .then()
                .log().all()
                .spec(ResponseSpecUtil.responseSpec200());
    }

    // ---------- DELETE USER ----------
    @Test(priority = 4, dependsOnMethods = "testUpdateUser")
    public void testDeleteUser() {
        // DELETE request
        Response response = given()
                .spec(RequestSpecUtil.requestSpec())
                .log().all()
                .when()
                .delete(Endpoints.USER + "/" + username);

        response.then().log().all().spec(ResponseSpecUtil.responseSpec200());
        assertEquals(response.jsonPath().getString("message"), username);
    }

    // ---------- NEGATIVE TEST ----------
    @Test(priority = 5)
    public void testNegativeUserNotFound() {
        String invalidUser = "nonExistingUserXYZ";

        Response response = given()
                .spec(RequestSpecUtil.requestSpec())
                .log().all()
                .when()
                .get(Endpoints.USER + "/" + invalidUser);

        response.then().log().all().spec(ResponseSpecUtil.responseSpec404());

        assertEquals(response.jsonPath().getString("message"), "User not found");

        test.pass("Negative test passed for non-existing user: " + invalidUser);
    }
}
