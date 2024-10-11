import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ExtentReportManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    protected Properties properties;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeMethod
    public void setUp(Method method) throws IOException {
        // Initialize properties and load the config file
        properties = new Properties();

        try (InputStream configFile = new FileInputStream("config.properties")) {
            properties.load(configFile);
        }

        // Initialize ExtentReports
        extent = ExtentReportManager.getInstance();

        // Create a test entry in the Extent report for each test method
        test = extent.createTest(method.getName());

        // WebDriver Setup
        String browser = properties.getProperty("browser");
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        // Add more browsers if needed

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(properties.getProperty("implicitWait")), TimeUnit.SECONDS);
        driver.get(properties.getProperty("baseURL"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Log test result to ExtentReports
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed: " + result.getName());
            test.log(Status.FAIL, "Reason: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Passed: " + result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Skipped: " + result.getName());
        }

        // Quit WebDriver
        if (driver != null) {
            driver.quit();
        }

        // Flush the Extent report
        extent.flush();
    }
}
