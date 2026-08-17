package com.company.framework.pages.male;

import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class Razorpay {

    private final WaitUtils waitUtils;
    private final AppiumDriver driver;

    public Razorpay(AppiumDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Razorpay Trusted Business']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement razorpayTrustedBusinessText;
    public boolean isRazorpayTrustedBusinessTextDisplayed() {
        return waitUtils.isElementDisplayed(razorpayTrustedBusinessText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Payment Options']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement paymentOptionsText;
    public boolean isPaymentOptionsTextDisplayed() {
        return waitUtils.isElementDisplayed(paymentOptionsText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='All Payment Options']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement allPaymentOptionsText;
    public boolean isAllPaymentOptionsTextDisplayed() {
        return waitUtils.isElementDisplayed(allPaymentOptionsText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Cards']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement cardsText;
    public boolean isCardsTextDisplayed() {
        return waitUtils.isElementDisplayed(cardsText);
    }
    public void clickCardsText() {
        waitUtils.waitForElement(cardsText).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Cash on Delivery']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement cashOnDeliveryText;
    public boolean isCashOnDeliveryTextDisplayed() {
        return waitUtils.isElementDisplayed(cashOnDeliveryText);
    }
    public void clickCashOnDeliveryText() {
        waitUtils.waitForElement(cashOnDeliveryText).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Netbanking']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement netbankingText;
    public boolean isNetbankingTextDisplayed() {
        return waitUtils.isElementDisplayed(netbankingText);
    }
    public void clickNetbankingText() {
        waitUtils.waitForElement(netbankingText).click();
    }

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Continue']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement continueButton;
    public boolean isContinueButtonDisplayed() {
        return waitUtils.isElementDisplayed(continueButton);
    }
    public void clickContinueButton() {
        waitUtils.waitForElement(continueButton).click();
    }
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Secured by']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement securedByText;
    public boolean isSecuredByTextDisplayed() {
        return waitUtils.isElementDisplayed(securedByText);
    }

    // --- "Are you sure you want to proceed with cash on delivery?" confirmation dialog ---
    //
    // Confirmed live to be intermittent: it shows up on some runs right after clicking the
    // Payment Options "Continue" button and not on others (same account, same flow, back-to-back
    // runs) - callers should poll for it with a short timeout rather than assume either outcome.
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Are you sure you want to proceed with cash on delivery?']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement codConfirmDialogText;
    public boolean isCodConfirmDialogDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(codConfirmDialogText, timeout);
    }

    // A real native android.widget.Button (unlike the plain TextViews used elsewhere on this
    // dialog/screen) - confirmed live via page source, clickable="true" on the node itself.
    @AndroidFindBy(xpath = "//android.widget.Button[@text='Continue with COD']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement continueWithCODButton;
    // A plain .click() on this dialog's confirm action was NOT reliably tested live (the dialog
    // itself only appeared intermittently during exploration), and every other confirm-style
    // button discovered in this same "modal on top of a checkout/order screen" family (the order
    // cancellation bottom sheet's own confirm button) turned out to need a genuine W3C tap instead
    // of a plain click - so this uses the same proven-safe tap rather than risk a silent no-op.
    public void clickContinueWithCODButton() {
        waitUtils.waitForElement(continueWithCODButton);
        org.openqa.selenium.Rectangle rect = continueWithCODButton.getRect();
        w3cTap(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);
    }

    // --- "Order Confirmed" transient screen (auto-redirects to the Thank You screen) ---
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Order Confirmed']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement orderConfirmedText;
    public boolean isOrderConfirmedTextDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(orderConfirmedText, timeout);
    }

    private void w3cTap(int x, int y) {
        org.openqa.selenium.interactions.PointerInput finger =
                new org.openqa.selenium.interactions.PointerInput(org.openqa.selenium.interactions.PointerInput.Kind.TOUCH, "finger");
        org.openqa.selenium.interactions.Sequence tap = new org.openqa.selenium.interactions.Sequence(finger, 0);
        tap.addAction(finger.createPointerMove(Duration.ZERO, org.openqa.selenium.interactions.PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new org.openqa.selenium.interactions.Pause(finger, Duration.ofMillis(100)));
        tap.addAction(finger.createPointerUp(org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(tap));
    }
}
