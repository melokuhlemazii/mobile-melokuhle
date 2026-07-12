package Utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Properties;

public class DriverFactory {
    static AppiumDriver driver;

    private static void initAndroidDriver(Properties config, String executionType, String appiumUrl) throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName(config.getProperty("platformName"))
                .setAutomationName(config.getProperty("automationName"));

        if (executionType.equalsIgnoreCase("mobileWeb")) {
            options.withBrowserName(config.getProperty("browserName"));
            System.out.println("Launching the Android Chrome browser");
        } else if (executionType.equalsIgnoreCase("nativeApp")) {
            String appPath = System.getProperty("user.dir") + "/" + config.getProperty("appPath");
            options.setApp(appPath);
            System.out.println("Launching Android native app...");
        } else {
            throw new RuntimeException("Unsupported executionType for Android: " + executionType);
        }

        driver = new AppiumDriver(URI.create(appiumUrl).toURL(), options);
        if (executionType.equalsIgnoreCase("mobileWeb")) {
            String webUrl = config.getProperty("webUrl");
            driver.get(webUrl);
        }
    }
}
