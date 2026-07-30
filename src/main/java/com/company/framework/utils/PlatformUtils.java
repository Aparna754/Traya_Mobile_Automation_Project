package com.company.framework.utils;

import com.company.framework.driver.factory.Platform;

public final class PlatformUtils {

    private static final ThreadLocal<Platform> currentPlatform = new ThreadLocal<>();

    private PlatformUtils() {
    }

    public static void setCurrentPlatform(Platform platform) {
        currentPlatform.set(platform);
    }

    public static Platform getCurrentPlatform() {
        Platform platform = currentPlatform.get();
        if (platform == null) {
            // Fallback for contexts that run before BaseTest has set it (e.g. TestListener.onTestStart,
            // which TestNG fires before @BeforeMethod).
            platform = Platform.fromString(ConfigReader.resolvePlatform());
        }
        return platform;
    }

    public static boolean isAndroid() {
        return getCurrentPlatform() == Platform.ANDROID;
    }

    public static boolean isIOS() {
        return getCurrentPlatform() == Platform.IOS;
    }

    public static void clear() {
        currentPlatform.remove();
    }
}
