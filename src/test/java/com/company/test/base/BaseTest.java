package com.company.test.base;

import com.company.framework.driver.DriverManager;
import com.company.framework.utils.AppResetUtils;
import com.company.framework.utils.ConfigReader;
import com.company.framework.utils.LogUtil;
import com.company.framework.utils.ScreenshotUtil;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.List;

public class BaseTest {

    @BeforeMethod
    public void setUp() throws Exception {

        LogUtil.log("Test execution started");
        AppResetUtils.resetApp(ConfigReader.get("appPackage"));
        DriverManager.initializeDriver();
        dismissSystemDialogIfPresent();
        LogUtil.log("Application opened successfully");
    }

    /**
     * A fresh app-data reset can trigger a one-time OS dialog (e.g. a page-size
     * compatibility notice) on top of the app. It's not always present, so this
     * only dismisses it when found instead of waiting/failing when it's not.
     */
    private void dismissSystemDialogIfPresent() {

        AndroidDriver driver = DriverManager.getDriver();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        List<WebElement> okButtons = driver.findElements(AppiumBy.id("android:id/button2"));
        if (!okButtons.isEmpty()) {
            okButtons.get(0).click();
        }
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
