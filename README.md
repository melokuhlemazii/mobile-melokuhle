# Melokuhle Appium - Mobile Automation Testing

> **📚 Educational Purpose**: This project was created for educational purposes to demonstrate mobile app automation testing using Appium and the Page Object Model design pattern.

## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Architecture & Design Patterns](#architecture--design-patterns)
- [Key Components](#key-components)
- [Best Practices](#best-practices)
- [Troubleshooting](#troubleshooting)
- [Additional Resources](#additional-resources)

---

## 🎯 Project Overview

**Melokuhle Appium** is an automated testing framework for mobile applications built using Appium and Java. This project demonstrates:

- **Mobile Test Automation** using Appium with Java
- **Page Object Model (POM)** design pattern for maintainable test code
- **Cross-platform support** for Android and iOS devices
- **Execution modes**: Native app and Mobile web testing
- **TestNG framework** for test organization and execution
- **Configuration-driven testing** for flexibility and scalability

### Learning Objectives

Through this project, developers can learn:
- Fundamental concepts of mobile app automation
- How to structure a test automation framework
- Appium capabilities for Android and iOS testing
- Best practices in test design and maintainability
- Configuration management in automation projects

---

## 🛠 Technology Stack

| Component | Version | Purpose |
|-----------|---------|---------|
| **Java** | 21 | Programming language |
| **Maven** | Latest | Build & dependency management |
| **Appium Java Client** | 10.1.1 | Mobile automation framework |
| **Selenium WebDriver** | 4.39.0 | WebDriver implementation |
| **TestNG** | 7.9.0 | Test framework & test execution |

### Supporting Tools & Platforms

- **Android SDK** - Android device automation
- **XCode/iOS SDK** - iOS device automation
- **Appium Server** - Mobile automation backend
- **Node.js** - Appium server runtime

---

## 📁 Project Structure

```
Melokuhle_Appium/
├── README.md                        # This file
├── pom.xml                          # Maven configuration
├── src/
│   ├── main/
│   │   ├── Apps/
│   │   │   └── app-qa-release.apk   # Android test application
│   │   ├── java/                    # Main source code
│   │   └── resources/               # Main resources
│   │
│   └── test/
│       ├── java/
│       │   ├── Base/
│       │   │   └── BaseTest.java             # Base test class with setup/teardown
│       │   │
│       │   ├── Basics/
│       │   │   └── StartNdosiMobileApp.java # Basic app launch demonstration
│       │   │
│       │   ├── Pages/
│       │   │   └── LoginPage.java           # Login page object model
│       │   │
│       │   ├── Tests/
│       │   │   └── DashboardTest.java       # Dashboard test cases
│       │   │
│       │   └── Utilities/
│       │       └── DriverFactory.java       # Driver initialization factory
│       │
│       └── resources/
│           └── configs/
│               └── config.properties        # Test configuration properties
│
└── target/                          # Compiled output (auto-generated)
```

---

## 📋 Prerequisites

### System Requirements

- **Operating System**: Windows, macOS, or Linux
- **Java**: JDK 21 or higher
- **Maven**: 3.6.0 or higher

### Mobile Device Requirements

#### For Android Testing
- Android device or emulator (Android 8.0 or higher)
- USB debugging enabled
- Android SDK installed and configured
- ADB (Android Debug Bridge) in PATH

#### For iOS Testing
- Mac OS X machine
- Xcode and iOS SDK installed
- iOS device or simulator (iOS 14 or higher)
- WebDriverAgent properly configured

### Server & Runtime Requirements
- **Appium Server**: 2.0 or higher
- **Node.js**: 16.0 or higher (required for Appium)

---

## 🚀 Installation & Setup

### Step 1: Install Java & Maven

**Windows (using Chocolatey):**
```powershell
choco install openjdk21 maven
```

**macOS (using Homebrew):**
```bash
brew install openjdk@21 maven
```

**Linux (Ubuntu/Debian):**
```bash
sudo apt-get install openjdk-21-jdk maven
```

### Step 2: Clone/Download the Project

```bash
cd /path/to/your/workspace
# Clone or extract the project
```

### Step 3: Install Node.js and Appium

**Install Node.js:**
- Download from https://nodejs.org/ (LTS version)
- Or use package manager: `brew install node` (macOS) or `choco install nodejs` (Windows)

**Install Appium:**
```bash
npm install -g appium
npm install -g appium-doctor  # To check Appium setup
```

**Install Appium Drivers:**
```bash
appium driver install uiautomator2  # For Android
appium driver install xcuitest       # For iOS (macOS only)
```

### Step 4: Verify Appium Installation

```bash
appium-doctor --android
appium-doctor --ios  # macOS only
```

### Step 5: Build the Project

```bash
cd Melokuhle_Appium
mvn clean install
```

### Step 6: Start Appium Server

```bash
appium
```

Expected output:
```
[Appium] Welcome to Appium v2.x.x
[Appium] Appium REST http interface listening on 127.0.0.1:4723
```

---

## ⚙️ Configuration

### Configuration File Location

`src/test/resources/configs/config.properties`

### Configuration Parameters

```properties
# Execution Type: "nativeApp" or "mobileWeb"
executionType=nativeApp

# Web URL (used for mobile web testing)
webUrl=https://ndosisimplifiedautomation.vercel.app/

# Android Capabilities
browserName=chrome
automationName=UiAutomator2
platformName=Android
appPath=src/main/Apps/app-qa-release.apk

# iOS Capabilities (uncomment for iOS testing)
#platformName=iOS
#automationName=XCUITest
#browserName=safari

# Appium Server Configuration
appiumServer=http://127.0.0.1:4723

# Test Credentials
email=
password=
```

### Configuration Options Explained

| Parameter | Values | Description |
|-----------|--------|-------------|
| `executionType` | `nativeApp`, `mobileWeb` | Type of application to test |
| `platformName` | `Android`, `iOS` | Target mobile platform |
| `automationName` | `UiAutomator2`, `XCUITest` | Native automation engine |
| `appPath` | File path | Path to APK or IPA file |
| `appiumServer` | URL | Appium server address and port |

---

## 🧪 Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=DashboardTest
```

### Run Specific Test Method
```bash
mvn test -Dtest=DashboardTest#Test
```

### Run Tests with Different Configuration
Edit `config.properties` to change execution type or platform, then run tests.

### View Detailed Test Results
```bash
mvn test -X  # Enable debug logging
```

---

## 🏗 Architecture & Design Patterns

### Page Object Model (POM) Pattern

This project follows the **Page Object Model** design pattern, which is an industry best practice for test automation:

```
┌─────────────────────────────────────────────────────────┐
│                    Test Classes                         │
│              (DashboardTest, etc.)                      │
│                                                         │
│  Contains: Test scenarios and assertions               │
│  Focus: Business logic and test workflows              │
└────────────────────┬────────────────────────────────────┘
                     │ Uses
                     ↓
┌─────────────────────────────────────────────────────────┐
│                   Page Objects                          │
│                 (LoginPage, etc.)                       │
│                                                         │
│  Contains: Element locators and UI interactions        │
│  Focus: Encapsulation of UI elements                   │
└────────────────────┬────────────────────────────────────┘
                     │ Uses
                     ↓
┌─────────────────────────────────────────────────────────┐
│              Selenium WebDriver/Appium                   │
│                                                         │
│  Contains: Low-level browser/app interactions          │
│  Focus: Technical implementation                       │
└─────────────────────────────────────────────────────────┘
```

### Benefits of POM

- ✅ **Maintainability**: UI changes only require updating page objects
- ✅ **Reusability**: Page methods can be used across multiple tests
- ✅ **Readability**: Tests focus on business logic, not technical details
- ✅ **Scalability**: Easy to add new page objects and tests
- ✅ **Reduced Duplication**: Common actions defined once, reused everywhere

### Design Patterns Used

#### 1. **Singleton Pattern** (DriverFactory)
Ensures only one driver instance exists throughout test execution:
```java
static AppiumDriver driver;
public static AppiumDriver getDriver() {
    return driver;
}
```

#### 2. **Factory Pattern** (DriverFactory)
Encapsulates driver creation logic and handles platform-specific initialization:
```java
public static void initDriver(Properties config) {
    // Platform-specific driver initialization
}
```

#### 3. **Base Test Pattern** (BaseTest)
Provides common setup, teardown, and reusable methods for all tests:
```java
@BeforeClass
public void setUpAndLogin() {
    // Common setup logic
}
```

---

## 🔧 Key Components

### 1. BaseTest.java

**Purpose**: Base class for all test classes

**Responsibilities**:
- Initialize AppiumDriver before each test
- Load configuration properties
- Perform login setup
- Clean up resources after tests

**Usage**: All test classes extend BaseTest to inherit setup/teardown logic

---

### 2. LoginPage.java

**Purpose**: Page Object Model for the login screen

**Key Methods**:
- `clickBurgerMenuButton()` - Click navigation menu
- `clickSignInButton()` - Click sign-in/sign-up button
- `enterEmail(String email)` - Enter email address
- `enterPassword(String password)` - Enter password
- `clickLoginButton()` - Click login button

**Features**:
- Supports both native app and mobile web locators
- Implements explicit waits for element visibility
- Automatically detects execution type from configuration

---

### 3. DriverFactory.java

**Purpose**: Central factory for AppiumDriver initialization

**Key Methods**:
- `initDriver(Properties config)` - Initialize driver based on configuration
- `getDriver()` - Retrieve current driver instance
- `quitDriver()` - Cleanup and close driver

**Features**:
- Handles Android and iOS driver setup
- Supports native app and web execution modes
- Manages Appium server connection
- Provides automatic URL navigation for web mode

---

### 4. DashboardTest.java

**Purpose**: Contains test cases for dashboard functionality

**Note**: This is a template test class ready for dashboard test implementation

---

### 5. StartNdosiMobileApp.java

**Purpose**: Demonstrates basic app launch without Page Object Model

**Usage**: Educational example showing direct Appium usage

---

## 📚 Best Practices

### 1. Element Locator Strategy

**✅ GOOD**: Use stable locators that don't change frequently
```java
By.xpath("//android.widget.Button[@content-desc='Login']")
By.id("login-email")
```

**❌ AVOID**: Fragile locators dependent on dynamic content
```java
By.xpath("//android.widget.Button[text()='Login']")  // Text may change
```

### 2. Explicit Waits

**✅ GOOD**: Use WebDriverWait for reliable synchronization
```java
wait.until(ExpectedConditions.elementToBeClickable(locator));
```

**❌ AVOID**: Hard-coded thread sleep (causes flaky tests)
```java
Thread.sleep(5000);  // Unpredictable timing
```

### 3. Configuration Management

**✅ GOOD**: Externalize configuration for different environments
```java
String email = config.getProperty("email");
String appiumServer = config.getProperty("appiumServer");
```

**❌ AVOID**: Hard-coded values scattered in code
```java
String email = "test@example.com";  // Not maintainable
```

### 4. Page Object Methods

**✅ GOOD**: Business-focused action methods
```java
public void login(String email, String password) {
    enterEmail(email);
    enterPassword(password);
    clickLoginButton();
}
```

**❌ AVOID**: Exposing low-level Selenium operations
```java
public WebElement getEmailField() { ... }
```

### 5. Test Independence

**✅ GOOD**: Each test can run independently
```java
@BeforeClass
public void setup() {
    // Initialize fresh state
}
```

**❌ AVOID**: Tests dependent on execution order
```java
// Test A creates data, Test B expects it to exist
```

---

## 🔍 Troubleshooting

### Problem: Appium Server Connection Failed

**Error**: `Failed to connect to Appium server at http://127.0.0.1:4723`

**Solutions**:
1. Verify Appium server is running: `appium --version`
2. Check port 4723 is available: `netstat -an` (Windows/macOS) or `lsof -i :4723` (Linux)
3. Restart Appium server
4. Check firewall/network settings

---

### Problem: Element Not Found

**Error**: `org.openqa.selenium.NoSuchElementException`

**Solutions**:
1. Use Appium Inspector to verify element locator is correct
2. Increase WebDriverWait timeout in LoginPage.java
3. Verify element is visible and enabled before interaction
4. Try alternative locator strategies (ID, XPath, accessibility ID)

---

### Problem: APK Installation Fails

**Error**: `INSTALL_FAILED_INVALID_APK` or similar

**Solutions**:
1. Verify APK file exists at configured path
2. Ensure device has sufficient storage space
3. Clear existing app: `adb shell pm clear com.package.name`
4. Uninstall previous version: `adb uninstall com.package.name`

---

### Problem: Tests Timeout

**Error**: `TimeoutException: Timed out after 20 seconds`

**Solutions**:
1. Increase WebDriverWait timeout value in LoginPage.java
2. Check device performance and responsiveness
3. Verify network connectivity (for mobile web tests)
4. Use Appium Inspector to confirm element is present

---

### Problem: Maven Build Failure

**Error**: `BUILD FAILURE` or dependency resolution errors

**Solutions**:
1. Clear Maven cache: `mvn clean`
2. Update dependencies: `mvn dependency:resolve`
3. Check Maven settings.xml for repository configuration
4. Verify internet connection for downloading dependencies

---

## 📚 Additional Resources

### Official Documentation
- [Appium Documentation](https://appium.io/docs/latest/) - Comprehensive Appium guide
- [Selenium WebDriver Docs](https://www.selenium.dev/documentation/) - WebDriver capabilities
- [TestNG Documentation](https://testng.org/doc/) - Test framework usage
- [Maven Documentation](https://maven.apache.org/guides/) - Build tool guide

### Helpful Tools
- [Appium Inspector](https://github.com/appium/appium-inspector) - GUI for element inspection and locator discovery
- [Android Studio](https://developer.android.com/studio) - Android emulator and SDK tools
- [Xcode](https://developer.apple.com/xcode/) - iOS simulator and development tools

### Learning Resources
- Mobile app testing concepts
- Appium framework capabilities
- Selenium WebDriver fundamentals
- Test automation best practices

---

## 📝 Educational Disclaimer

**This project is for educational purposes only.**

- Use only with applications you have permission to test
- Do not use sensitive credentials or production data
- Test only on devices/emulators you own or have explicit permission to use
- Respect application terms of service and security policies
- This is a learning tool to understand mobile automation concepts

---

**Project Status**: ✅ Complete for Educational Learning  
**Last Updated**: July 19, 2026  
**Created For**: Learning Mobile Test Automation with Appium

