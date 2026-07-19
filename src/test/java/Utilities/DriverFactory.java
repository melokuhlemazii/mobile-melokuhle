package Utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Properties;

public class DriverFactory {

    // Declares a static AppiumDriver variable to hold the driver instance across the application
    static AppiumDriver driver;

    // Method to initialize the AppiumDriver based on platform and execution type from configuration
    public static void initDriver(Properties config) throws MalformedURLException {
        // Returns early if the driver is already initialized to avoid re-initialization
        if (driver != null) return;

        // Retrieves the platform name from the configuration properties and removes whitespace
        String platformName = config.getProperty("platformName").trim();

        // Retrieves the execution type (mobileWeb or nativeApp) from configuration and removes whitespace
        String executionType = config.getProperty("executionType").trim();

        // Retrieves the Appium server URL from configuration and removes whitespace
        String appiumUrl = config.getProperty("appiumServer").trim();

        // Checks if the platform is Android and initializes the Android driver accordingly
        if (platformName.equalsIgnoreCase("Android")) {

            // Calls the initAndroidDriver method to set up Android-specific driver configuration
            initAndroidDriver(config, executionType, appiumUrl);

            // Checks if the platform is iOS and initializes the iOS driver accordingly
        } else if (platformName.equalsIgnoreCase("iOS")) {

            // Calls the initIOSDriver method to set up iOS-specific driver configuration
            initIOSDriver(config, executionType, appiumUrl);

            // Throws an exception if the platform is not supported
        } else {
            throw new RuntimeException("Unsupported platformName: " + platformName);
        }

    }


    // Private method to initialize AppiumDriver for Android platform with specified execution type
    private static void initAndroidDriver(Properties config, String executionType, String appiumUrl)
            throws MalformedURLException {

        // Creates UiAutomator2Options object for Android automation configuration
        UiAutomator2Options options = new UiAutomator2Options()
                // Sets the platform name property from configuration
                .setPlatformName(config.getProperty("platformName"))
                // Sets the automation engine name property from configuration
                .setAutomationName(config.getProperty("automationName"));

        // Checks if execution type is mobile web browser automation
        if (executionType.equalsIgnoreCase("mobileWeb")) {
            // Sets the browser name property for web automation from configuration
            options.withBrowserName(config.getProperty("browserName"));
            // Prints message indicating Android Chrome browser launch
            System.out.println("Launching the Android Chrome browser");
            // Checks if execution type is native app automation
        } else if (executionType.equalsIgnoreCase("nativeApp")) {
            // Constructs the full app path by combining the project directory with the app path from configuration
            String appPath = System.getProperty("user.dir") + "/" + config.getProperty("appPath");
            // Sets the app path for native app automation in the options
            options.setApp(appPath);
            // Prints message indicating Android native app launch
            System.out.println("Launching Android native app...");
            // Throws exception if execution type is not supported for Android
        } else {
            throw new RuntimeException("Unsupported executionType for Android: " + executionType);
        }

        // Creates a new AppiumDriver instance with the Appium server URL and configured options
        driver = new AppiumDriver(URI.create(appiumUrl).toURL(), options);
        // Checks if execution type is mobile web to navigate to the web URL
        if (executionType.equalsIgnoreCase("mobileWeb")) {
            // Retrieves the web URL to navigate from the configuration properties
            String webUrl = config.getProperty("webUrl");
            // Navigates the driver to the specified web URL
            driver.get(webUrl);
        }
    }

    // Private method to initialize AppiumDriver for iOS platform with specified execution type
    private static void initIOSDriver(Properties config, String executionType, String appiumUrl)
            throws MalformedURLException {

        // Creates XCUITestOptions object for iOS automation configuration
        XCUITestOptions options = new XCUITestOptions()
                // Sets the platform name property from configuration
                .setPlatformName(config.getProperty("platformName"))
                // Sets the automation engine name property from configuration
                .setAutomationName(config.getProperty("automationName"));

        // Checks if execution type is mobile web browser automation
        if (executionType.equalsIgnoreCase("mobileWeb")) {
            // Sets the browser name property for web automation from configuration
            options.withBrowserName(config.getProperty("browserName"));
            // Prints message indicating Safari browser launch
            System.out.println("Launching the Safari browser");
            // Checks if execution type is native app automation
        } else if (executionType.equalsIgnoreCase("nativeApp")) {
            // Constructs the full app path by combining the project directory with the app path from configuration
            String appPath = System.getProperty("user.dir") + "/" + config.getProperty("appPath");
            // Sets the app path for native app automation in the options
            options.setApp(appPath);
            // Prints message indicating iOS native app launch
            System.out.println("Launching iOS native app...");
            // Throws exception if execution type is not supported for iOS
        } else {
            throw new RuntimeException("Unsupported executionType for iOS: " + executionType);
        }

        // Creates a new AppiumDriver instance with the Appium server URL and configured options
        driver = new AppiumDriver(URI.create(appiumUrl).toURL(), options);
        // Checks if execution type is mobile web to navigate to the web URL
        if (executionType.equalsIgnoreCase("mobileWeb")) {
            // Retrieves the web URL to navigate from the configuration properties
            String webUrl = config.getProperty("webUrl");
            // Navigates the driver to the specified web URL
            driver.get(webUrl);
        }
    }

    // Public method to retrieve the initialized AppiumDriver instance
    public static AppiumDriver getDriver() {
        // Returns the static driver instance to the caller
        return driver;
    }

    // Public method to quit the AppiumDriver and clean up resources
    public static void quitDriver() {
        // Checks if the driver is initialized before attempting to quit
        if (driver != null) {
            // Quits the driver session and closes the application
            driver.quit();
            // Sets the driver to null to clear the reference
            driver = null;
        }
    }
}
