package com.company.framework.driver;

import com.company.framework.driver.factory.DriverFactory;
import com.company.framework.driver.factory.Platform;
import io.appium.java_client.AppiumDriver;

import java.net.MalformedURLException;

public final class DriverManager {

    private static final ThreadLocal<AppiumDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void initializeDriver(Platform platform) throws MalformedURLException {
        AppiumDriver driver = DriverFactory.createDriver(platform);
        driverThreadLocal.set(driver);
    }

    public static AppiumDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void quitDriver() {

        AppiumDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                // Session may already be gone (e.g. the app closed/restarted itself after a
                // completed action) - nothing left to quit, and this must not fail teardown for
                // a test whose actual assertions already passed.
            } finally {
                driverThreadLocal.remove();
            }
        }
    }
}
