package utils;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecUtil {

    public static ResponseSpecification responseSpec() {
        return new ResponseSpecBuilder()
                .log(io.restassured.filter.log.LogDetail.ALL)
                .build();
    }
}
