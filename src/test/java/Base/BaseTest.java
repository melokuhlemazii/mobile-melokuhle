package Base;

import Pages.LoginPage;
import Utilities.DriverFactory;
import io.appium.java_client.AppiumDriver;
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
    }

    public void LoginToNdosiAutomation() {
        loginPage.clickBurgerMenuButton();
        loginPage.clickSignInButton();
        loginPage.enterEmail(config.getProperty("email"));
        loginPage.enterPassword(config.getProperty("password"));
        loginPage.clickLoginButton();
    }

}
