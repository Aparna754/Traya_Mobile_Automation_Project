package com.company.framework.pages.male;

import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

/**
 * "Order Summary" order-details screen, reached by tapping the "Your order is placed" (or
 * "Your order is cancelled") banner on the dashboard - shows the order's line items and offers
 * "Cancel Order", which opens a "Why do you want to cancel this order?" reason picker followed by
 * a confirm button and a success toast. Confirmed live via adb/uiautomator that this whole screen
 * renders as a single WebView (the page source only exposes one full-screen container around the
 * header - no granular back-icon element the way the native RN screens elsewhere in this app
 * have), which is also why its buttons don't always respond to a plain click() or Appium's W3C
 * touch actions - see the tap methods below for what was actually confirmed to work live.
 */
public class OrderCancellation_Screen {

    private final WaitUtils waitUtils;
    private final AppiumDriver driver;

    public OrderCancellation_Screen(AppiumDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Cancel Order']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement CancelOrderButton;
    public boolean isCancelOrderButtonDisplayed() {
        return waitUtils.isElementDisplayed(CancelOrderButton);
    }
    public void clickCancelOrderButton() {
        waitUtils.waitForElement(CancelOrderButton).click();
    }

    // --- "Why do you want to cancel this order?" reason picker bottom sheet ---

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Forgot to apply discount/coins']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement ForgotToApplyDiscountOrCoinsReasonOption;
    public boolean isForgotToApplyDiscountOrCoinsReasonDisplayed() {
        return waitUtils.isElementDisplayed(ForgotToApplyDiscountOrCoinsReasonOption);
    }
    public void clickForgotToApplyDiscountOrCoinsReason() {
        waitUtils.waitForElement(ForgotToApplyDiscountOrCoinsReasonOption).click();
    }

    // The sheet's own confirm button shares the exact text "Cancel Order" with the page-level
    // button above - it's the LAST such node in document order. A plain click() on it was
    // confirmed live to silently no-op (the sheet just sits there); only a genuine W3C tap on its
    // resolved coordinates actually submits the cancellation.
    public boolean isConfirmCancelOrderButtonDisplayed() {
        try {
            List<WebElement> buttons = driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='Cancel Order']"));
            return !buttons.isEmpty() && waitUtils.isElementDisplayed(buttons.get(buttons.size() - 1));
        } catch (Exception e) {
            return false;
        }
    }
    public void clickConfirmCancelOrderButton() {
        List<WebElement> buttons = driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='Cancel Order']"));
        WebElement confirmButton = buttons.get(buttons.size() - 1);
        waitUtils.waitForElement(confirmButton);
        Rectangle rect = confirmButton.getRect();
        w3cTap(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Order Cancel Requested Successfully']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement OrderCancelSuccessMessage;
    public boolean isOrderCancelSuccessMessageDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(OrderCancelSuccessMessage, timeout);
    }

    // --- Leaving this screen ---
    //
    // There is no accessible back-icon element here at all (confirmed live via adb/uiautomator -
    // just one full-screen WebView container node sits where the header's "<-" arrow is drawn),
    // and driver.navigate().back() (the Android hardware BACK action) was confirmed live to be a
    // no-op on this specific screen too. A genuine W3C tap on the arrow's own drawn position was
    // ALSO confirmed live to no-op here - only a real system-injected touch (adb shell input tap,
    // bypassing Appium's session entirely) actually navigates back. Coordinates are derived as a
    // fraction of the window size rather than hardcoded, since that's the only part of this that
    // should vary between devices.
    public void goBack() {
        org.openqa.selenium.Dimension size = driver.manage().window().getSize();
        int x = (int) (size.getWidth() * 0.062);
        int y = (int) (size.getHeight() * 0.085);
        // A single tap was seen to occasionally miss (this screen can still be mid-transition
        // right after the cancel-success toast appears) - retry a few times, checking this
        // screen's own "Cancel Order" row to see whether we've actually left yet.
        for (int attempt = 0; attempt < 3; attempt++) {
            try {
                new ProcessBuilder("adb", "shell", "input", "tap", String.valueOf(x), String.valueOf(y))
                        .inheritIO().start().waitFor();
            } catch (Exception e) {
                throw new RuntimeException("Failed to tap back arrow via adb at (" + x + "," + y + ")", e);
            }
            // A short timeout here on purpose - this is a "have we left yet" branch probe, not an
            // assertion that the element must be present, so it shouldn't eat a full 20s default
            // wait on every attempt once the screen is genuinely gone.
            if (!waitUtils.isElementDisplayed(CancelOrderButton, Duration.ofSeconds(3))) {
                return;
            }
        }
    }

    private void w3cTap(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 0);
        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(100)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(tap));
    }
}
