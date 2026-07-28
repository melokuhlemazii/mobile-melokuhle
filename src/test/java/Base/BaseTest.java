package Base;

import Pages.DashboardPage;
import Pages.LoginPage;
import Pages.ProfilePage;
import Utilities.DriverFactory;
import Utilities.ScreenshotUtils;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    // Declares a protected AppiumDriver variable that can be accessed by subclasses
    protected AppiumDriver driver;
    // Declares a protected Properties variable to store configuration settings accessible by subclasses
    protected Properties config;

    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;
    protected ProfilePage profilePage;

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



