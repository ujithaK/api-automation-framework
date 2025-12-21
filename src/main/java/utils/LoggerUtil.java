package utils;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class LoggerUtil {

    private static PrintStream logStream;

    static {
        try {
            logStream = new PrintStream(
                    new FileOutputStream("target/api-logs.txt", true)
            );
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Unable to create log file", e);
        }
    }

    /**
     * Logs full request details
     */
    public static RequestLoggingFilter requestLogger() {
        return new RequestLoggingFilter(LogDetail.ALL, logStream);
    }

    /**
     * Logs full response details
     */
    public static ResponseLoggingFilter responseLogger() {
        return new ResponseLoggingFilter(LogDetail.ALL, logStream);
    }
}
