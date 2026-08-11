package com.company.framework.pages.male;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ThankYou_Screen {

    private final WaitUtils waitUtils;

    public ThankYou_Screen(AppiumDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Order placed, thanks!']")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement ThankYouText;
    public boolean isThankYouTextDisplayed() {
        return waitUtils.isElementDisplayed(ThankYouText);
    }

     @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Book A Call']")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement BookACallToggle_button;
    public boolean isBookACallToggle_buttonDisplayed() {
        return waitUtils.isElementDisplayed(BookACallToggle_button);
    }
    public void clickBookACallToggle_button() {
        waitUtils.waitForElement(BookACallToggle_button).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Book A Call']")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement BookACallButton;
    public boolean isBookACallButtonDisplayed() {
        return waitUtils.isElementDisplayed(BookACallButton);
    }
    public void clickBookACallButton() {
        waitUtils.waitForElement(BookACallButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Call Booked']")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement CallBookedText;
    public boolean isCallBookedTextDisplayed() {
        return waitUtils.isElementDisplayed(CallBookedText);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Okay']")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement OkayButton;
    public boolean isOkayButtonDisplayed() {
        return waitUtils.isElementDisplayed(OkayButton);
    }
    public void clickOkayButton() {
        waitUtils.waitForElement(OkayButton).click();
    }
}
