package com.company.framework.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

/**
 * Off-screen elements (e.g. below the fold on a long scrolling page) aren't present in the
 * accessibility tree at all until scrolled into view, so a plain WaitUtils.waitForElement() wait
 * can't find them. This repeatedly swipes and re-checks until the element appears or a max
 * attempt count is reached.
 */
public final class SwipeUtils {

    private static final int DEFAULT_MAX_SWIPES = 15;

    private SwipeUtils() {
    }

    public static WebElement swipeUntilVisible(AppiumDriver driver, WebElement element) {
        return swipeUntilVisible(driver, element, DEFAULT_MAX_SWIPES);
    }

    public static WebElement swipeUntilVisible(AppiumDriver driver, WebElement element, int maxSwipes) {

        for (int attempt = 0; attempt < maxSwipes; attempt++) {
            if (isVisible(element)) {
                return element;
            }
            scrollDown(driver, element);
        }

        if (!isVisible(element)) {
            throw new NoSuchElementException("Element not visible after " + maxSwipes + " swipe attempts");
        }
        return element;
    }

    private static boolean isVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Uses "mobile: scrollGesture" (Android) / "mobile: scroll" (iOS) - a controlled scroll with
     * no fling/momentum - NOT "mobile: swipeGesture"/"mobile: swipe", which are velocity-based
     * fling gestures. On a React Native ScrollView, a fling's momentum travels far past the
     * intended distance regardless of the requested percent, overshooting the target element
     * entirely; a plain scroll gesture moves proportionally to the requested percent as expected.
     */
    private static void scrollDown(AppiumDriver driver, WebElement element) {

        if (PlatformUtils.isIOS()) {
            // TODO(iOS): unverified against a real app build - no real iOS app exists yet to test
            // this against (see docs/ios-real-device-setup.md). Revisit once one does.
            Map<String, Object> params = new HashMap<>();
            params.put("direction", "down");
            params.put("element", element);
            driver.executeScript("mobile: scroll", params);
            return;
        }

        Dimension size = driver.manage().window().getSize();
        Map<String, Object> params = new HashMap<>();
        params.put("left", (int) (size.getWidth() * 0.1));
        params.put("top", (int) (size.getHeight() * 0.2));
        params.put("width", (int) (size.getWidth() * 0.8));
        params.put("height", (int) (size.getHeight() * 0.6));
        params.put("direction", "down");
        params.put("percent", 0.3);
        driver.executeScript("mobile: scrollGesture", params);
    }
}
