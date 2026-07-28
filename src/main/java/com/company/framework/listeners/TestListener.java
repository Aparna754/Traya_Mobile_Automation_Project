package com.company.framework.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.company.framework.driver.DriverManager;
import com.company.framework.reports.ExtentManager;
import com.company.framework.utils.LogUtil;
import com.company.framework.utils.ScreenshotUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final ExtentReports extentReports = ExtentManager.getExtentReports();
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        String testName = result.getMethod().getMethodName();
        System.out.println("TEST STARTED: " + testName);
        LogUtil.log("TEST STARTED: " + testName);
        ExtentTest test = extentReports.createTest(testName);
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        String testName = result.getMethod().getMethodName();
        System.out.println("TEST PASSED: " + testName);
        LogUtil.log("TEST PASSED: " + testName);
        extentTest.get().pass("Test passed successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        String testName = result.getMethod().getMethodName();
        Throwable failure = result.getThrowable();
        System.out.println("TEST FAILED: " + testName);
        LogUtil.log("TEST FAILED: " + testName);
        if (failure != null) {
            LogUtil.log("Failure Reason: " + failure.getMessage());
        }
        if (DriverManager.getDriver() != null) {
            ScreenshotUtil.takeScreenshot(DriverManager.getDriver(), result.getName());
        }

        try {
            String screenshotPath = ScreenshotUtil.takeScreenshot(DriverManager.getDriver(), testName);
            extentTest.get().fail("Test failed").addScreenCaptureFromPath(screenshotPath);
            LogUtil.log("Screenshot saved at: " + screenshotPath);

        } catch (Exception e) {

            LogUtil.log("Could not capture screenshot: " + e.getMessage());
            e.printStackTrace();
        }

        if (failure != null) {

            extentTest.get().fail(failure);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        String testName = result.getMethod().getMethodName();
        LogUtil.log("TEST SKIPPED: " + testName);
        extentTest.get().skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {

        extentReports.flush();
        LogUtil.log("Extent report generated successfully");
    }
}