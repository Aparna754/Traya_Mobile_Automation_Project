package com.company.test.base;

import com.company.framework.driver.DriverManager;
import com.company.framework.utils.LogUtil;
import com.company.framework.utils.ScreenshotUtil;
import io.appium.java_client.android.AndroidDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void setUp() throws Exception {

        LogUtil.log("Test execution started");
        DriverManager.initializeDriver();
        LogUtil.log("Application opened successfully");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        AndroidDriver driver = DriverManager.getDriver();
        if (result.getStatus() == ITestResult.FAILURE) {
            LogUtil.log("TEST FAILED: " + result.getName() + " | Error: " + result.getThrowable());

            if (driver != null) {
                ScreenshotUtil.takeScreenshot(driver, result.getName());
            }
        }

        if (result.getStatus() == ITestResult.SUCCESS) {
            LogUtil.log("TEST PASSED: " + result.getName());
        }
        //    ScreenshotUtil.takeScreenshot(DriverManager.getDriver(), "ManualScreenshotTest");

        DriverManager.quitDriver();
    }
}
