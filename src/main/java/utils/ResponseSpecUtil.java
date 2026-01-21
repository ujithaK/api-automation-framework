package utils;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecUtil {

    // Generic response spec
    public static ResponseSpecification responseSpec() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    // Response spec with expected status
    public static ResponseSpecification responseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(LogDetail.ALL)
                .build();
    }

    // Convenience method for 200 OK
    public static ResponseSpecification responseSpec200() {
        return responseSpec(200);
    }

    // Convenience method for 404
    public static ResponseSpecification responseSpec404() {
        return responseSpec(404);
    }

    // **Add this method for backward compatibility**
    public static ResponseSpecification expectStatus(int statusCode) {
        return responseSpec(statusCode);
    }
}
