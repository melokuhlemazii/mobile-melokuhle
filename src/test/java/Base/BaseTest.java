package Base;

import Pages.DashboardPage;
import Pages.LoginPage;
import Pages.ProfilePage;
import Reports.ExtentReportManager;
import Utilities.DriverFactory;
import Utilities.ScreenshotUtils;
import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

public class BaseTest {

    // Declares a protected AppiumDriver variable that can be accessed by subclasses
    protected AppiumDriver driver;
    // Declares a protected Properties variable to store configuration settings accessible by subclasses
    protected Properties config;
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;
    protected ProfilePage profilePage;
    protected ExtentTest extentTest;

    @BeforeClass
    // Method that sets up the driver and loads configuration before running tests
    public void setUpAndLogin() throws IOException {
        // Initializes a new Properties object to store configuration data
        config = new Properties();
        // Creates a FileInputStream to read the configuration properties file from the resources directory
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/configs/config.properties");
        // Loads the properties from the configuration file into the Properties object
        config.load(fis);

        // Initializes the AppiumDriver using the DriverFactory with the loaded configuration
        DriverFactory.initDriver(config);
        // Retrieves the initialized driver from the DriverFactory and assigns it to the driver variable
        driver = DriverFactory.getDriver();

        loginPage = new LoginPage(driver, config);
        LoginToNdosiAutomation();

        dashboardPage = new DashboardPage(driver,config);
        profilePage = new ProfilePage(driver,config);
    }

    @BeforeMethod
    public void createReportTest (Method method){
        extentTest = ExtentReportManager.getExtentReports().createTest(method.getName());
        extentTest.info("Test execution started");
    }

    @AfterMethod(alwaysRun = true)
    public void recordTestResult(ITestResult result)
    {
        String testName = result.getMethod().getMethodName();
        try{
            if (result.getStatus() == ITestResult.FAILURE) {
                if (result.getThrowable() != null) {
                    extentTest.fail(result.getThrowable());
                } else {
                    extentTest.fail("Test failed");
                }

                AppiumDriver driver = DriverFactory.getDriver();
                if (driver != null) {
                    ScreenshotUtils.captureScreenshot(driver, testName);
                }
                File originalScreenshot = new File("screenshots/" + testName + ".png");
                if (originalScreenshot.exists()) {
                    Path reportScreenshotFolder = Path.of("target", "reports", "screenshots");
                    Files.createDirectories(reportScreenshotFolder);

                    Path reportScreenshot = reportScreenshotFolder.resolve(testName + ".png");
                    Files.copy(originalScreenshot.toPath(), reportScreenshot, StandardCopyOption.REPLACE_EXISTING);

                    extentTest.addScreenCaptureFromPath("screenshots/" + testName + ".png", "Failure Screenshot");
                    extentTest.info("Screenshot attached successfully");
                } else {
                    extentTest.warning("Screenshot was not created: " + originalScreenshot.getAbsolutePath());
                }
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                extentTest.pass("Test passed successfully");
            } else if (result.getStatus() == ITestResult.SKIP) {
                extentTest.pass("Test was Skipped");
            }
        } catch (Exception e) {
            System.out.println("Could not attach screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void generateReport() {
        ExtentReportManager.getExtentReports().flush();
    }

    public void LoginToNdosiAutomation() {
        loginPage.clickBurgerMenuButton();
        ScreenshotUtils.captureScreenshot(driver, "Burger Menu Clicked");

        loginPage.clickSignInButton();
        ScreenshotUtils.captureScreenshot(driver, "Sign In Button Clicked");

        loginPage.enterEmail(config.getProperty("email"));
        ScreenshotUtils.captureScreenshot(driver, "Email Entered");

        loginPage.enterPassword(config.getProperty("password"));
        ScreenshotUtils.captureScreenshot(driver, "Password Entered");

        loginPage.clickLoginButton();
        ScreenshotUtils.captureScreenshot(driver, "Login Button Clicked");

        Assert.assertTrue(loginPage.isLoginSuccess(),"Login successful!");
    }

    @AfterClass
    public void teardown(){
        DriverFactory.quitDriver();
    }

}



