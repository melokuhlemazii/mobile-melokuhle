package Listener;

import Utilities.DriverFactory;
import Utilities.ScreenshotUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result){
        takeScreenshot(result, "FAILURE");
        System.out.println("Test failed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result){
        System.out.println("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result){
        System.out.println("Test skipped: " + result.getMethod().getMethodName());
    }

    private void takeScreenshot(ITestResult result, String status)
    {
        ScreenshotUtils.captureScreenshot(DriverFactory.getDriver(),result.getName() +"_"+status);
    }

}
