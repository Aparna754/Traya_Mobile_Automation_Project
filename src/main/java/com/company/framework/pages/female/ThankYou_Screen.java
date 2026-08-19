package com.company.framework.pages.female;

import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

/**
 * Post-payment "Thank You" screen - same generic screen as the male flow's ThankYou_Screen
 * (order confirmation text, Book A Call action), duplicated here per project convention.
 */
public class ThankYou_Screen {

    private final WaitUtils waitUtils;
    private final AppiumDriver driver;

    public ThankYou_Screen(AppiumDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Order placed, thanks!']")
    private WebElement ThankYouText;
    public boolean isThankYouTextDisplayed() {
        return waitUtils.isElementDisplayed(ThankYouText);
    }
    public boolean isThankYouTextDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(ThankYouText, timeout);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Book A Call']")
    private WebElement BookACallButton;
    public boolean isBookACallButtonDisplayed() {
        return waitUtils.isElementDisplayed(BookACallButton);
    }
    public void clickBookACallButton() {
        waitUtils.waitForElement(BookACallButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Call Booked']")
    private WebElement CallBookedText;
    public boolean isCallBookedTextDisplayed() {
        return waitUtils.isElementDisplayed(CallBookedText);
    }
    public boolean isCallBookedTextDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(CallBookedText, timeout);
    }

    // Confirmed live: unlike the male flow's "Call Booked" confirmation (a plain content-desc
    // "Okay" button), this modal closes via a circular "X" icon floating top-right over the
    // dialog with no confirmed text/content-desc of its own - closed via a coordinate tap
    // (relative to window size) rather than a locator.
    public void clickCloseCallBookedModal() {
        org.openqa.selenium.Dimension size = driver.manage().window().getSize();
        int x = (int) (size.getWidth() * 0.94);
        int y = (int) (size.getHeight() * 0.24);
        org.openqa.selenium.interactions.PointerInput finger =
                new org.openqa.selenium.interactions.PointerInput(org.openqa.selenium.interactions.PointerInput.Kind.TOUCH, "finger");
        org.openqa.selenium.interactions.Sequence tap = new org.openqa.selenium.interactions.Sequence(finger, 0);
        tap.addAction(finger.createPointerMove(java.time.Duration.ZERO, org.openqa.selenium.interactions.PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new org.openqa.selenium.interactions.Pause(finger, java.time.Duration.ofMillis(100)));
        tap.addAction(finger.createPointerUp(org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(java.util.List.of(tap));
    }
}
