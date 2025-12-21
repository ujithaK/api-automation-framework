package utils;

import config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

public class RequestSpecUtil {

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigManager.get("baseUrl"))
                .setContentType("application/json")
                .log(LogDetail.ALL)
                .build();
    }

}

