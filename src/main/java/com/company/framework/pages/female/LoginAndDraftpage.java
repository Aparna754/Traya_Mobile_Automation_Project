package com.company.framework.pages.female;

import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

/**
 * Login/OTP + "Let's get started" (name/age/gender) screen - identical underlying app screens as
 * com.company.framework.pages.male.LoginAndDraftpage (shared, not gender-specific), duplicated
 * into this package per project convention so the female flow doesn't depend on male's package.
 */
public class LoginAndDraftpage {

    private final WaitUtils waitUtils;
    private final AppiumDriver driver;

    public LoginAndDraftpage(AppiumDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Mobile number']")
    private WebElement mobileNumberField;
    public void enterMobileNumber(String mobileNumber) {
        waitUtils.waitForElement(mobileNumberField).sendKeys(mobileNumber);
    }
    public boolean isMobileNumberFieldDisplayed() {
        return waitUtils.isElementDisplayed(mobileNumberField);
    }
    public boolean isMobileNumberFieldDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(mobileNumberField, timeout);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Get OTP']/android.view.ViewGroup")
    private WebElement getOTPButton;
    public void clickGetOTP() {
        waitUtils.waitForElement(getOTPButton).click();
    }

    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='Verify OTP'])[1]")
    private WebElement verifyOTPText;
    public boolean isVerifyOTPTextDisplayed() {
        return waitUtils.isElementDisplayed(verifyOTPText);
    }
    public boolean isVerifyOTPTextDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(verifyOTPText, timeout);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Enter the OTP we’ve sent to']")
    private WebElement enterTheOTPText;
    public boolean isEnterTheOTPTextDisplayed() {
        return waitUtils.isElementDisplayed(enterTheOTPText);
    }

    @AndroidFindBy(xpath = "(//android.widget.EditText[@resource-id='textInput'])[1]")
    private WebElement verifyOTPTextfiled;
    public void enterOTP(String OTP) {
        waitUtils.waitForElement(verifyOTPTextfiled).sendKeys(OTP);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Verify OTP']/android.view.ViewGroup")
    private WebElement verifyOTPButton;
    public void clickVerifyOTP() {
        waitUtils.waitForElement(verifyOTPButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Skip']")
    private WebElement skipButton;
    public void clickSkipButton() {
        waitUtils.waitForElement(skipButton).click();
    }
    public boolean isSkipButtonDisplayed() {
        return waitUtils.isElementDisplayed(skipButton);
    }
    public boolean isSkipButtonDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(skipButton, timeout);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Let’s get started']")
    private WebElement letsGetStartedText;
    public boolean isLetsGetStartedDisplayed() {
        return waitUtils.isElementDisplayed(letsGetStartedText);
    }
    public boolean isLetsGetStartedDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(letsGetStartedText, timeout);
    }

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='username-name-input']")
    private WebElement nameField;
    public void enterName(String name) {
        waitUtils.waitForElement(nameField).sendKeys(name);
    }

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='username-age-input']")
    private WebElement ageField;
    public void enterAge(String age) {
        waitUtils.waitForElement(ageField).sendKeys(age);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Female']")
    private WebElement femaleRadioButton;
    public void selectFemale() {
        waitUtils.waitForElement(femaleRadioButton).click();
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Continue']/android.view.ViewGroup")
    private WebElement continueButton;
    public void clickContinue() {
        waitUtils.waitForElement(continueButton).click();
    }
}
