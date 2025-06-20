package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.DriverFactory;
import utils.ExtentManager;
import utils.ScreenshotUtil;

import java.io.IOException;

public class ExtentTestNGListener implements ITestListener {
    private static ExtentReports extent = ExtentManager.getInstance();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    public static ThreadLocal<ExtentTest> node = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
                "Detailed test steps for " + result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String screenshotPath = ScreenshotUtil.takeScreenshot(DriverFactory.getDriver(), result.getMethod().getMethodName());
        test.get().addScreenCaptureFromPath(screenshotPath);
        test.get().log(Status.PASS, "✅ Test Passed");

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String screenshotPath = ScreenshotUtil.takeScreenshot(DriverFactory.getDriver(), result.getMethod().getMethodName());
        test.get().log(Status.FAIL, "❌ Test Failed:" + result.getThrowable());
        test.get().addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onFinish(ITestContext context) {
        DriverFactory.getDriver().quit();
        extent.flush();
    }

    public static void info(String message) {
        if (test.get() != null) {
            test.get().info(message);
        }
    }

    //Helper for steps with or without screenshot:
    public static void logStep(String message) {
        if (test.get() != null) {
            test.get().info(message);
        }
    }

    public static void logStepWithScreenshot(String message) {
        if (test.get() != null) {
            String path = ScreenshotUtil.takeScreenshot(DriverFactory.getDriver(), message);
            test.get().info(message).addScreenCaptureFromPath(path);
        }
    }


}
