package com.company.framework.pages.female;

import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

/**
 * In-app "Cancel Order" flow, reached from the dashboard's "Your order is placed" banner ->
 * Order Summary's "Cancel Order" row - confirmed live to share the exact same text/locators as
 * the male flow's OrderCancellation_Screen ("Cancel Order" appearing twice - once to open the
 * reason list, once as the last-in-DOM confirm button - and the "Forgot to apply discount/coins"
 * reason option).
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
    private WebElement CancelOrderButton;
    public boolean isCancelOrderButtonDisplayed() {
        return waitUtils.isElementDisplayed(CancelOrderButton);
    }
    public void clickCancelOrderButton() {
        waitUtils.waitForElement(CancelOrderButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Forgot to apply discount/coins']")
    private WebElement ForgotToApplyDiscountOrCoinsReasonOption;
    public boolean isForgotToApplyDiscountOrCoinsReasonDisplayed() {
        return waitUtils.isElementDisplayed(ForgotToApplyDiscountOrCoinsReasonOption);
    }
    public void clickForgotToApplyDiscountOrCoinsReason() {
        waitUtils.waitForElement(ForgotToApplyDiscountOrCoinsReasonOption).click();
    }

    // The reason-picker's own submit button is a second, distinct "Cancel Order" TextView (last
    // one in document order) rather than a differently-labeled confirm button - confirmed live.
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
        waitUtils.waitForElement(confirmButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Order Cancel Requested Successfully']")
    private WebElement OrderCancelSuccessMessage;
    public boolean isOrderCancelSuccessMessageDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(OrderCancelSuccessMessage, timeout);
    }
}
