package com.company.test.base;

import com.company.framework.driver.DriverManager;
import com.company.framework.driver.factory.Platform;
import com.company.framework.utils.ConfigReader;
import com.company.framework.utils.LogUtil;
import com.company.framework.utils.PlatformUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    // alwaysRun=true: without it, a suite-level <groups><run><include .../></run></groups>
    // filter (e.g. testng-regression.xml) excludes this configuration method too, since it
    // declares no groups of its own - leaving the driver uninitialized while the @Test method
    // (which IS in the included group) still runs.
    @BeforeMethod(alwaysRun = true)
    @Parameters("platform")
    public void setUp(@Optional("Android") String platformParam) throws Exception {

        String systemProperty = System.getProperty("platform");
        String resolved = (systemProperty != null && !systemProperty.isBlank())? systemProperty: (platformParam != null && !platformParam.isBlank() ? platformParam : "Android");
        Platform platform = Platform.fromString(resolved);
        LogUtil.log("Test execution started on platform: " + platform);
        PlatformUtils.setCurrentPlatform(platform);
        DriverManager.initializeDriver(platform);
        relaunchApp(platform);
        LogUtil.log("Application opened successfully");
    }

    /**
     * noReset keeps the app installed and its data intact between sessions, which means a
     * previous run can leave the app mid-flow (e.g. past the mobile-number screen). Terminating
     * and relaunching here guarantees every test starts from the same cold-launch screen without
     * losing noReset's installed-app-state behavior.
     */
    private void relaunchApp(Platform platform) {

        AppiumDriver driver = DriverManager.getDriver();
        String appId = platform == Platform.ANDROID
                ? ConfigReader.getForPlatform(platform.name(), "appPackage")
                : ConfigReader.getForPlatform(platform.name(), "bundleId");

        if (driver instanceof InteractsWithApps interactable && appId != null && !appId.isBlank()) {
            interactable.terminateApp(appId);
            interactable.activateApp(appId);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        // Failure screenshot capture lives solely in TestListener.onTestFailure now - it fires
        // before this method and is the single source of truth for the PDF report, so capturing
        // here too would violate "only ONE screenshot per failed test."
        DriverManager.quitDriver();
        PlatformUtils.clear();
    }
}
