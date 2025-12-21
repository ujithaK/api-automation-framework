package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class BaseTest {

    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeSuite
    public void setupSuite() {
        // Configure ExtentReports
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ApiTestReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeClass
    public void setup() {
        // Base URI and Path for REST Assured
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }

    @BeforeMethod
    public void startTest(Method method) {
        // Create a test node in ExtentReports for each test method
        test = extent.createTest(method.getName());
    }

    @AfterSuite
    public void tearDownSuite() {
        // Flush reports after all tests
        extent.flush();
    }
}
