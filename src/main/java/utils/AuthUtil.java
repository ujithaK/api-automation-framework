package utils;

import io.restassured.specification.RequestSpecification;

public class AuthUtil {

    public static RequestSpecification basicAuth(RequestSpecification req) {
        return req.auth().basic("user", "password");
    }

    public static RequestSpecification bearerAuth(RequestSpecification req, String token) {
        return req.header("Authorization", "Bearer " + token);
    }
}
