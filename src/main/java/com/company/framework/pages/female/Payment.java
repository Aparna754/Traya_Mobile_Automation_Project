package com.company.framework.pages.female;

import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

/**
 * Two distinct payment screens are confirmed live for the female flow, depending on entry path:
 *   1. "Payment Methods" (plural) - reached via Checkout_Screen's "Proceed to Pay" (the post-hair-
 *      -test "Buy Now" path). "Cash On Delivery" (capital O), "Proceed to Pay" TextView, no
 *      separate "Continue" button.
 *   2. "Payment Method" (singular) - reached via OrderSummary_Screen's "Continue" (the returning-
 *      -customer "Buy Again" repeat-purchase path). This is the standard Razorpay SDK checkout UI,
 *      structurally the same as the male flow's Razorpay screen (Cards / Pay on delivery / Net
 *      banking sections) - but still "Cash on delivery" lowercase here, and "Proceed to Pay" (not
 *      male's "Continue") appears only after selecting a payment option.
 * Fields for (1) are listed first, then (2) below, each commented with which screen it belongs to.
 */
public class Payment {

    private final WaitUtils waitUtils;

    public Payment(AppiumDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Payment Methods']")
    private WebElement PaymentMethodsText;
    public boolean isPaymentMethodsTextDisplayed() {
        return waitUtils.isElementDisplayed(PaymentMethodsText);
    }
    public boolean isPaymentMethodsTextDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(PaymentMethodsText, timeout);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Cash On Delivery']")
    private WebElement CashOnDeliveryText;
    public boolean isCashOnDeliveryTextDisplayed() {
        return waitUtils.isElementDisplayed(CashOnDeliveryText);
    }
    public void clickCashOnDeliveryText() {
        waitUtils.waitForElement(CashOnDeliveryText).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Proceed to Pay']")
    private WebElement ProceedToPayButton;
    public boolean isProceedToPayButtonDisplayed() {
        return waitUtils.isElementDisplayed(ProceedToPayButton);
    }
    public void clickProceedToPayButton() {
        waitUtils.waitForElement(ProceedToPayButton).click();
    }

    // Intermittent (confirmed on the male flow too - same underlying payment provider) - callers
    // should poll for it with a short timeout rather than assume either outcome.
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Are you sure you want to proceed with cash on delivery?']")
    private WebElement codConfirmDialogText;
    public boolean isCodConfirmDialogDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(codConfirmDialogText, timeout);
    }

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Continue with COD']")
    private WebElement continueWithCODButton;
    public void clickContinueWithCODButton() {
        waitUtils.waitForElement(continueWithCODButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Order Confirmed']")
    private WebElement orderConfirmedText;
    public boolean isOrderConfirmedTextDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(orderConfirmedText, timeout);
    }

    // --- "Payment Method" (singular) screen - the standard Razorpay SDK checkout UI, reached via
    // OrderSummary_Screen's "Continue" (returning-customer "Buy Again" path). Confirmed live. ---

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Payment Method']")
    private WebElement PaymentMethodHeaderText;
    public boolean isPaymentMethodHeaderDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(PaymentMethodHeaderText, timeout);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Cards']")
    private WebElement CardsHeaderText;
    public boolean isCardsHeaderDisplayed() {
        return waitUtils.isElementDisplayed(CardsHeaderText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Pay on delivery']")
    private WebElement PayOnDeliveryHeaderText;
    public boolean isPayOnDeliveryHeaderDisplayed() {
        return waitUtils.isElementDisplayed(PayOnDeliveryHeaderText);
    }

    // Lowercase "delivery" here - confirmed live to differ from the other screen's "Cash On
    // Delivery" (capital O) above, even within the same female flow.
    @AndroidFindBy(xpath = "//*[@content-desc='Cash on delivery']")
    private WebElement CashOnDeliveryRadioOption;
    public boolean isCashOnDeliveryRadioOptionDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(CashOnDeliveryRadioOption, timeout);
    }
    public void clickCashOnDeliveryRadioOption() {
        waitUtils.waitForElement(CashOnDeliveryRadioOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Net banking']")
    private WebElement NetBankingHeaderText;
    public boolean isNetBankingHeaderDisplayed() {
        return waitUtils.isElementDisplayed(NetBankingHeaderText);
    }
}
