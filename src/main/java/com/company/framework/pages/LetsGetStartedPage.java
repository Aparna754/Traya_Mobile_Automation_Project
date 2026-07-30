package com.company.framework.pages;

import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
public class LetsGetStartedPage {

    private final WaitUtils waitUtils;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Mobile number']")
    @iOSXCUITFindBy(accessibility = "PLACEHOLDER_mobile_number_field") // TODO(iOS): replace once real app exists
    private WebElement mobileNumberField;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Get OTP']/android.view.ViewGroup")
    @iOSXCUITFindBy(accessibility = "PLACEHOLDER_get_otp_button") // TODO(iOS): replace once real app exists
    private WebElement getOTPButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Let’s get started']")
    @iOSXCUITFindBy(accessibility = "PLACEHOLDER_lets_get_started_text") // TODO(iOS): replace once real app exists
    private WebElement letsGetStartedText;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='username-name-input']")
    @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement nameField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='username-age-input']")
    @iOSXCUITFindBy(accessibility = "PLACEHOLDER_age_field") // TODO(iOS): replace once real app exists
    private WebElement ageField;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Male']")
    @iOSXCUITFindBy(accessibility = "PLACEHOLDER_male_radio_button") // TODO(iOS): replace once real app exists
    private WebElement maleRadioButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Female']")
    @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement femaleRadioButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Continue']/android.view.ViewGroup")
    @iOSXCUITFindBy(accessibility = "PLACEHOLDER_continue_button") // TODO(iOS): replace once real app exists
    private WebElement continueButton;

    public LetsGetStartedPage(AppiumDriver driver) {

        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void enterMobileNumber(String mobileNumber) {
        waitUtils.waitForElement(mobileNumberField).sendKeys(mobileNumber);
    }

    public void clickGetOTP() {
        waitUtils.waitForElement(getOTPButton).click();
    }

    public boolean isLetsGetStartedDisplayed() {
        return waitUtils.waitForElement(letsGetStartedText).isDisplayed();
    }

    public void enterName(String name) {
        waitUtils.waitForElement(nameField).sendKeys(name);
    }

    public void enterAge(String age) {
        waitUtils.waitForElement(ageField).sendKeys(age);
    }

    public void selectMale() {
        waitUtils.waitForElement(maleRadioButton).click();
    }

    public void selectFemale() {
        waitUtils.waitForElement(femaleRadioButton).click();
    }

    public void clickContinue() {
        waitUtils.waitForElement(continueButton).click();
    }

}
