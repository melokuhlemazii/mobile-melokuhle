package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class ProfilePage {
    AppiumDriver driver;
    WebDriverWait wait;
    Properties config;

    public ProfilePage (AppiumDriver driver, Properties config) {
        this.driver = driver;
        this.config = config;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private By MenuButtonNativeLocator = By.xpath("//android.widget.Button");
    private By MenuButtonWebLocator = By.xpath("//button[@class='nav-mobile-account']");

    private WebElement GetLocators (By NativeLocator, By WebLocator) {
        String execType = config.getProperty("executionType");

        if (execType.equalsIgnoreCase("nativeApp")){
            return wait.until(ExpectedConditions.elementToBeClickable(NativeLocator));
        } else if (execType.equalsIgnoreCase("webApp")){
            return wait.until(ExpectedConditions.elementToBeClickable(WebLocator));
        } else {
            throw new RuntimeException("Unsupported executionType: " + execType);
        }
    }

    public void clickMenuButton(){
        GetLocators(MenuButtonNativeLocator, MenuButtonWebLocator).click();
    }
}
