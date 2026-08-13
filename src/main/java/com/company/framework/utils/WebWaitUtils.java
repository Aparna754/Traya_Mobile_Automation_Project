package com.company.framework.utils;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Web-page-scoped counterpart to WaitUtils, used only by com.company.framework.pages.web page
 * objects - kept as a separate class rather than changing WaitUtils in place, so the mobile page
 * objects' existing wait semantics stay untouched.
 *
 * isElementDisplayed() still checks plain visibility (same as WaitUtils) - a visible-but-disabled
 * element (e.g. "Confirm Cancellation" before its form is filled in) is a legitimate "displayed"
 * state, not a bug. waitUntilClickable() is the addition: real web apps commonly render an
 * interactive element visible-but-disabled for a beat while client-side JS finishes initializing
 * (e.g. a "Sign in with Google" button disabled until Google's SDK attaches its handler).
 * Clicking/typing on visibility alone races that window and intermittently hits
 * ElementClickInterceptedException or a click/sendKeys that silently no-ops - use this
 * immediately before any click() or sendKeys() instead.
 */
public class WebWaitUtils {

    private final WebDriver driver;

    public WebWaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementDisplayed(WebElement element) {
        return isElementDisplayed(element, Duration.ofSeconds(45));
    }

    /**
     * Short-timeout variant of isElementDisplayed, for "which of these mutually exclusive
     * screens/elements am I on" probes (e.g. a chain of if/else-if branches). The default 45s
     * timeout exists because real modals here do server round-trips; reusing it for a probe
     * that's expected to be false most of the time means eating a full 45s of dead time per
     * false branch. Only use a shorter timeout here for checks that gate branching logic, not
     * for checks that assert an element must be present.
     */
    public boolean isElementDisplayed(WebElement element, Duration timeout) {
        try {
            return waitForVisible(element, timeout).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click that survives a still-fading overlay (e.g. a Bootstrap offcanvas backdrop) sitting
     * on top of an already visible-and-enabled element for the last moments of its transition.
     * elementToBeClickable() only checks visible+enabled, not "nothing is currently drawn on top
     * of it" - a real click can still get ElementClickInterceptedException in that gap. Falls
     * back to a JS-dispatched click (fires the element's click handler directly, bypassing the
     * browser's obscured-by-another-element check) rather than adding a fixed sleep to guess how
     * long the transition takes.
     */
    public void forceClick(WebElement element) {
        WebElement target = waitUntilClickable(element);
        try {
            target.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", target);
        }
    }

    public WebElement waitUntilClickable(WebElement element) {
        // 45s rather than WaitUtils' 20s: this class backs live web-app pages (erp.traya.health)
        // whose modals populate via real server round-trips (e.g. the "Cancel Order" modal's
        // Order ID/Category/Subcategory/remark dropdown), observed taking noticeably longer to
        // finish rendering than any transition in this project's mobile screens.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
        wait.ignoring(StaleElementReferenceException.class);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private WebElement waitForVisible(WebElement element) {
        return waitForVisible(element, Duration.ofSeconds(45));
    }

    private WebElement waitForVisible(WebElement element, Duration timeout) {
        // 45s rather than WaitUtils' 20s: this class backs live web-app pages (erp.traya.health)
        // whose modals populate via real server round-trips (e.g. the "Cancel Order" modal's
        // Order ID/Category/Subcategory/remark dropdown), observed taking noticeably longer to
        // finish rendering than any transition in this project's mobile screens.
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.ignoring(StaleElementReferenceException.class);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}
