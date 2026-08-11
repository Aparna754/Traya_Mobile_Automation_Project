package com.company.framework.utils;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WaitUtils {
    // WebDriver rather than AppiumDriver: the wait logic below only ever needs the base
    // WebDriver API (WebDriverWait accepts any WebDriver), so this same class works for both
    // Appium mobile page objects and plain Selenium web page objects (e.g. Login_Page) -
    // AppiumDriver already extends WebDriver, so every existing caller still compiles unchanged.
    private WebDriver driver;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElement(WebElement element) {

        // Many yes/no questions in a row reuse the same generic locator (e.g. "Yes"/"No" text) -
        // when the screen transitions to the next question, the previously-resolved element can
        // go stale mid-wait. WebDriverWait only auto-retries NoSuchElementException by default,
        // not StaleElementReferenceException, so without this the wait can abort well before the
        // real timeout even though the (new) element is visible moments later.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.ignoring(StaleElementReferenceException.class);

        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Same wait as waitForElement, but returns false instead of throwing when the element never
     * appears - for callers that need a genuine boolean probe (e.g. "if this screen is showing,
     * do X" branches), where a missing element is an expected, valid outcome, not a failure.
     */
    public boolean isElementDisplayed(WebElement element) {
        try {
            return waitForElement(element).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
