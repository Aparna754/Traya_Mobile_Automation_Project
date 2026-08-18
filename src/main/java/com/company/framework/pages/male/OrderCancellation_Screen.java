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

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Forgot to apply discount/coins']")
//  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement ForgotToApplyDiscountOrCoinsReasonOption;
    public boolean isForgotToApplyDiscountOrCoinsReasonDisplayed() {
        return waitUtils.isElementDisplayed(ForgotToApplyDiscountOrCoinsReasonOption);
    }
    public void clickForgotToApplyDiscountOrCoinsReason() {
        waitUtils.waitForElement(ForgotToApplyDiscountOrCoinsReasonOption).click();
    }

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

    public void goBack() {
        org.openqa.selenium.Dimension size = driver.manage().window().getSize();
        int x = (int) (size.getWidth() * 0.062);
        int y = (int) (size.getHeight() * 0.085);
        for (int attempt = 0; attempt < 3; attempt++) {
            try {
                new ProcessBuilder("adb", "shell", "input", "tap", String.valueOf(x), String.valueOf(y))
                        .inheritIO().start().waitFor();
            } catch (Exception e) {
                throw new RuntimeException("Failed to tap back arrow via adb at (" + x + "," + y + ")", e);
            }
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
