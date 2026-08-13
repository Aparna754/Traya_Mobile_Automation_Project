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
        return waitForElement(element, Duration.ofSeconds(20));
    }

    public WebElement waitForElement(WebElement element, Duration timeout) {

        // Many yes/no questions in a row reuse the same generic locator (e.g. "Yes"/"No" text) -
        // when the screen transitions to the next question, the previously-resolved element can
        // go stale mid-wait. WebDriverWait only auto-retries NoSuchElementException by default,
        // not StaleElementReferenceException, so without this the wait can abort well before the
        // real timeout even though the (new) element is visible moments later.
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.ignoring(StaleElementReferenceException.class);

        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Same wait as waitForElement, but returns false instead of throwing when the element never
     * appears - for callers that need a genuine boolean probe (e.g. "if this screen is showing,
     * do X" branches), where a missing element is an expected, valid outcome, not a failure.
     */
    public boolean isElementDisplayed(WebElement element) {
        return isElementDisplayed(element, Duration.ofSeconds(20));
    }

    /**
     * Short-timeout variant of isElementDisplayed, for "which of these mutually exclusive
     * screens/elements am I on" probes (e.g. a chain of if/else-if branches). The default 20s
     * timeout is sized for genuinely waiting on a slow-to-render element; reusing it for a probe
     * that's expected to be false most of the time means eating a full 20s of dead time per
     * false branch. Only use a shorter timeout here for checks that gate branching logic, not for
     * checks that assert an element must be present.
     */
    public boolean isElementDisplayed(WebElement element, Duration timeout) {
        try {
            return waitForElement(element, timeout).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
