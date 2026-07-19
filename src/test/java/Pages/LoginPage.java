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

public class LoginPage {
    // Declares an AppiumDriver variable to control the mobile application
    AppiumDriver driver;
    // Declares a Properties variable to store configuration settings
    Properties config;
    // Declares a WebDriverWait variable for explicit waits on elements
    WebDriverWait wait;

    // Constructor that initializes the LoginPage with an AppiumDriver and configuration Properties
    public LoginPage(AppiumDriver driver, Properties config)
    {
        // Assigns the passed AppiumDriver to the instance variable
        this.driver = driver;
        // Assigns the passed Properties to the instance variable
        this.config = config;
        // Creates a WebDriverWait instance with a timeout of 20 seconds for explicit waits
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // Initializes page elements using PageFactory and AppiumFieldDecorator for Appium support
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    // Defines a By locator for the burger menu button in native applications
    private By burgerMenuButtonNative = By.xpath("//android.widget.Button");
    // Defines a By locator for the burger menu button in web applications
    private By burgerMenuButtonWeb = By.xpath("//button[@class='nav-burger']");

    private By signInButtonNativeLocator = By.xpath("//android.widget.Button[@content-desc='Login / Sign Up']");
    private By signInButtonWebLocator = By.xpath("//button[@class='mobile-menu-item']");

    private By emailFieldNativeLocator = By.xpath("//android.widget.EditText[@hint='Email']");
    private By emailFieldWebLocator = By.id("login-email");

    private By passwordFieldNativeLocator = By.xpath("//android.widget.EditText[@hint='Password']");
    private By passwordFieldWebLocator = By.id("login-password");

    private By loginButtonNative = By.xpath("//android.widget.Button[@content-desc='Login']");
    private By loginButtonWeb = By.id("login-button");

    private WebElement getElement(By nativeLocator, By webLocator) {
        String execType = config.getProperty("executionType");

        if (execType.equalsIgnoreCase("nativeApp")){
            return wait.until(ExpectedConditions.elementToBeClickable(nativeLocator));
        } else if (execType.equalsIgnoreCase("mobileWeb")){
            return wait.until(ExpectedConditions.elementToBeClickable(webLocator));
        } else {
            throw new RuntimeException("Unsupported executionType: " + execType);
        }
    }

    public void clickBurgerMenuButton() {
        getElement(burgerMenuButtonNative, burgerMenuButtonWeb).click();
    }

    public void clickSignInButton() {
        getElement(signInButtonNativeLocator, signInButtonWebLocator).click();
    }

    public void enterEmail(String email) {
        WebElement emailElement = getElement(emailFieldNativeLocator, emailFieldWebLocator);
        emailElement.click();
        emailElement.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = getElement(passwordFieldNativeLocator, passwordFieldWebLocator);
        passwordElement.click();
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton() {
        getElement(loginButtonNative, loginButtonWeb).click();
    }


    // End of LoginPage class
}
