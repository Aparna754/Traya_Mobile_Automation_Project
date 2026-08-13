package com.company.framework.pages.male;

import com.company.framework.utils.SwipeUtils;
import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
//import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;
public class LoginAndDraftpage {

    private final WaitUtils waitUtils;
    private final AppiumDriver driver;

    public LoginAndDraftpage(AppiumDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Mobile number']")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_mobile_number_field") // TODO(iOS): replace once real app exists
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
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_get_otp_button") // TODO(iOS): replace once real app exists
    private WebElement getOTPButton;
    public void clickGetOTP() {
        waitUtils.waitForElement(getOTPButton).click();
    }

    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='Verify OTP'])[1]")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement verifyOTPText;
    public boolean isVerifyOTPTextDisplayed() {
        return waitUtils.isElementDisplayed(verifyOTPText);
    }
    public boolean isVerifyOTPTextDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(verifyOTPText, timeout);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Enter the OTP we’ve sent to']")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_male_radio_button") // TODO(iOS): replace once real app exists
    private WebElement enterTheOTPText;
    public boolean isEnterTheOTPTextDisplayed() {
        return waitUtils.isElementDisplayed(enterTheOTPText);
    }

    @AndroidFindBy(xpath = "(//android.widget.EditText[@resource-id='textInput'])[1]")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_lets_get_started_text") // TODO(iOS): replace once real app exists
    private WebElement verifyOTPTextfiled;
    public void enterOTP(String OTP) {
        waitUtils.waitForElement(verifyOTPTextfiled).sendKeys(OTP);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Verify OTP']/android.view.ViewGroup")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_age_field") // TODO(iOS): replace once real app exists
    private WebElement verifyOTPButton;
    public void clickVerifyOTP() {
        waitUtils.waitForElement(verifyOTPButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Skip']")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_age_field") // TODO(iOS): replace once real app exists
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
//   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_lets_get_started_text") // TODO(iOS): replace once real app exists
    private WebElement letsGetStartedText;
    public boolean isLetsGetStartedDisplayed() {
        return waitUtils.isElementDisplayed(letsGetStartedText);
    }
    public boolean isLetsGetStartedDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(letsGetStartedText, timeout);
    }

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='username-name-input']")
//    @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement nameField;
    public void enterName(String name) {
        waitUtils.waitForElement(nameField).sendKeys(name);
    }

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='username-age-input']")
    // @iOSXCUITFindBy(accessibility = "PLACEHOLDER_age_field") // TODO(iOS): replace once real app exists
    private WebElement ageField;
    public void enterAge(String age) {
        waitUtils.waitForElement(ageField).sendKeys(age);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Male']")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_male_radio_button") // TODO(iOS): replace once real app exists
    private WebElement maleRadioButton;
    public void selectMale() {
        waitUtils.waitForElement(maleRadioButton).click();
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Female']")
    // @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement femaleRadioButton;
    public void selectFemale() {
        waitUtils.waitForElement(femaleRadioButton).click();
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Continue']/android.view.ViewGroup")
   // @iOSXCUITFindBy(accessibility = "PLACEHOLDER_continue_button") // TODO(iOS): replace once real app exists
    private WebElement continueButton;
    public void clickContinue() {
        waitUtils.waitForElement(continueButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Know The Root Cause Of Your Hair Loss']")
    // @iOSXCUITFindBy(accessibility = "PLACEHOLDER_continue_button") // TODO(iOS): replace once real app exists
    private WebElement knowTheRootCauseOfYourHairLossText;
    public boolean isKnowTheRootCauseOfYourHairLossTextDisplayed() {
        return waitUtils.isElementDisplayed(knowTheRootCauseOfYourHairLossText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Traya Heroes']")
    // @iOSXCUITFindBy(accessibility = "PLACEHOLDER_continue_button") // TODO(iOS): replace once real app exists
    private WebElement trayaHeroesText;
    public boolean isTrayaHeroesTextDisplayed() {
        return waitUtils.isElementDisplayed(trayaHeroesText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='What Causes Hair Loss?']")
    // @iOSXCUITFindBy(accessibility = "PLACEHOLDER_continue_button") // TODO(iOS): replace once real app exists
    private WebElement whatCausesHairLossText;
    public void swipeUntilWhatCausesHairLossTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, whatCausesHairLossText);
    }
    public boolean isWhatCausesHairLossTextDisplayed() {
        return waitUtils.isElementDisplayed(whatCausesHairLossText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='How Traya Works']")
    // @iOSXCUITFindBy(accessibility = "PLACEHOLDER_continue_button") // TODO(iOS): replace once real app exists
    private WebElement howTrayaWorksText;
    public void swipeUntilHowTrayaWorksTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, howTrayaWorksText);
    }
    public boolean isHowTrayaWorksTextDisplayed() {
        return waitUtils.isElementDisplayed(howTrayaWorksText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Traya Plan Includes']")
    // @iOSXCUITFindBy(accessibility = "PLACEHOLDER_continue_button") // TODO(iOS): replace once real app exists
    private WebElement trayaPlanIncludesText;
    public void swipeUntilTrayaPlanIncludesTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, trayaPlanIncludesText);
    }
    public boolean isTrayaPlanIncludesTextDisplayed() {
        return waitUtils.isElementDisplayed(trayaPlanIncludesText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Need Help?']")
    // @iOSXCUITFindBy(accessibility = "PLACEHOLDER_continue_button") // TODO(iOS): replace once real app exists
    private WebElement needHelpText;
    public void swipeUntilNeedHelpTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, needHelpText);
    }
    public boolean isNeedHelpTextDisplayed() {
        return waitUtils.isElementDisplayed(needHelpText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Meet Our Team Of Doctors']")
    // @iOSXCUITFindBy(accessibility = "PLACEHOLDER_continue_button") // TODO(iOS): replace once real app exists
    private WebElement meetOurTeamOfDoctorsText;
    public void swipeUntilMeetOurTeamOfDoctorsTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, meetOurTeamOfDoctorsText);
    }
    public boolean isMeetOurTeamOfDoctorsTextDisplayed() {
        return waitUtils.isElementDisplayed(meetOurTeamOfDoctorsText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Google Reviews & Ratings']")
    // @iOSXCUITFindBy(accessibility = "PLACEHOLDER_continue_button") // TODO(iOS): replace once real app exists
    private WebElement googleReviewsAndRatingsText;
    public void swipeUntilGoogleReviewsAndRatingsTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, googleReviewsAndRatingsText);
    }
    public boolean isGoogleReviewsAndRatingsTextDisplayed() {
        return waitUtils.isElementDisplayed(googleReviewsAndRatingsText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'The Hair Test')]")
    // @iOSXCUITFindBy(accessibility = "PLACEHOLDER_continue_button") // TODO(iOS): replace once real app exists
    private WebElement takeTheHairTestButton;
    public void clickTakeTheHairTest() {
        waitUtils.waitForElement(takeTheHairTestButton).click();
    }

}

