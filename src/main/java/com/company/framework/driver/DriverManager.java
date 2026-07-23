package com.company.framework.driver;

import com.company.framework.utils.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class DriverManager {
    private static AndroidDriver driver;

    public static void initializeDriver() throws MalformedURLException {

        UiAutomator2Options capabilities = new UiAutomator2Options();

        capabilities.setCapability("appium:platformName", ConfigReader.get("platformName"));
        capabilities.setCapability("appium:automationName", ConfigReader.get("automationName"));
        capabilities.setCapability("appium:deviceName", ConfigReader.get("deviceName"));
        capabilities.setCapability("appium:udid", ConfigReader.get("udid"));
        capabilities.setCapability("appium:appPackage", ConfigReader.get("appPackage"));
        capabilities.setCapability("appium:appActivity", ConfigReader.get("appActivity"));
        capabilities.setCapability("appium:noReset", Boolean.parseBoolean(ConfigReader.get("noReset")));
        capabilities.setCapability("appium:autoGrantPermissions", Boolean.parseBoolean(ConfigReader.get("autoGrantPermissions")));
        driver = new AndroidDriver(URI.create("http://127.0.0.1:4723").toURL(), capabilities);
    }

    public static AndroidDriver getDriver() {

        return driver;
    }

    public static void quitDriver() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
