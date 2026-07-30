package com.company.framework.driver.factory;

import com.company.framework.utils.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public final class DriverFactory {

    private DriverFactory() {
    }

    public static AppiumDriver createDriver(Platform platform) throws MalformedURLException {
        return switch (platform) {
            case ANDROID -> createAndroidDriver(platform);
            case IOS -> createIOSDriver(platform);
        };
    }

    private static AndroidDriver createAndroidDriver(Platform platform) throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setCapability("appium:platformName", "Android");
        options.setCapability("appium:automationName", ConfigReader.getForPlatform(platform.name(), "automationName"));
        options.setCapability("appium:deviceName", ConfigReader.getForPlatform(platform.name(), "deviceName"));
        options.setCapability("appium:appPackage", ConfigReader.getForPlatform(platform.name(), "appPackage"));
        options.setCapability("appium:appActivity", ConfigReader.getForPlatform(platform.name(), "appActivity"));
        options.setCapability("appium:noReset", Boolean.parseBoolean(ConfigReader.getForPlatform(platform.name(), "noReset")));
        options.setCapability("appium:autoGrantPermissions", Boolean.parseBoolean(ConfigReader.getForPlatform(platform.name(), "autoGrantPermissions")));

        // udid only present for real devices; emulators are addressed via deviceName/AVD.
        String udid = ConfigReader.getForPlatform(platform.name(), "udid");
        if (udid != null && !udid.isBlank()) {
            options.setCapability("appium:udid", udid);
        }

        return new AndroidDriver(appiumServerUrl(), options);
    }

    private static IOSDriver createIOSDriver(Platform platform) throws MalformedURLException {

        XCUITestOptions options = new XCUITestOptions();
        options.setCapability("appium:platformName", "iOS");
        options.setCapability("appium:automationName", ConfigReader.getForPlatform(platform.name(), "automationName"));
        options.setCapability("appium:deviceName", ConfigReader.getForPlatform(platform.name(), "deviceName"));
        options.setCapability("appium:platformVersion", ConfigReader.getForPlatform(platform.name(), "platformVersion"));
        options.setCapability("appium:noReset", Boolean.parseBoolean(ConfigReader.getForPlatform(platform.name(), "noReset")));

        // Simulator vs real device: udid present + signing keys only apply to real devices.
        String udid = ConfigReader.getForPlatform(platform.name(), "udid");
        boolean isRealDevice = udid != null && !udid.isBlank();
        if (isRealDevice) {
            options.setCapability("appium:udid", udid);
            options.setCapability("appium:xcodeOrgId", ConfigReader.getForPlatform(platform.name(), "xcodeOrgId"));
            options.setCapability("appium:xcodeSigningId", ConfigReader.getForPlatform(platform.name(), "xcodeSigningId"));
            options.setCapability("appium:updatedWDABundleId", ConfigReader.getForPlatform(platform.name(), "updatedWDABundleId"));
        }

        // Prefer an app path (fresh install) over bundleId (already-installed app) when both are set.
        String appPath = ConfigReader.getForPlatform(platform.name(), "iosAppPath");
        String bundleId = ConfigReader.getForPlatform(platform.name(), "bundleId");
        if (appPath != null && !appPath.isBlank()) {
            options.setCapability("appium:app", appPath);
        } else if (bundleId != null && !bundleId.isBlank()) {
            options.setCapability("appium:bundleId", bundleId);
        }

        return new IOSDriver(appiumServerUrl(), options);
    }

    private static URL appiumServerUrl() throws MalformedURLException {
        String url = ConfigReader.get("appiumServerUrl");
        return URI.create(url != null && !url.isBlank() ? url : "http://127.0.0.1:4723").toURL();
    }
}
