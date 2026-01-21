package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import config.ConfigManager;
import io.restassured.RestAssured;
import org.testng.annotations.*;

public class BaseTest {

    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeSuite
    public void setupSuite() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ApiTestReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigManager.get("base.url");
    }

    @BeforeMethod
    public void startTest(java.lang.reflect.Method method) {
        test = extent.createTest(method.getName());
    }

    @AfterSuite
    public void tearDownSuite() {
        extent.flush();
    }
}
