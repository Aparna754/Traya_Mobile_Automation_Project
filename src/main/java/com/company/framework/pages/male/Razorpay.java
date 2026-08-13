package com.company.framework.pages.male;

import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class Razorpay {

    private final WaitUtils waitUtils;

    public Razorpay(AppiumDriver driver) {
        this.waitUtils = new WaitUtils(driver);
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
}
