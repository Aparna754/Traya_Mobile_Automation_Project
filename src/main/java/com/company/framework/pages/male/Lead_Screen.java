package com.company.framework.pages.male;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.company.framework.utils.SwipeUtils;
import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import java.time.Duration;
public class Lead_Screen {

    private final WaitUtils waitUtils;
    private final AppiumDriver driver;

    public Lead_Screen(AppiumDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Test completed')]")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement TestCompletedTodayText;
    public boolean isTestCompletedTodayTextDisplayed() {
        return waitUtils.isElementDisplayed(TestCompletedTodayText);
    }
    public boolean isTestCompletedTodayTextDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(TestCompletedTodayText, timeout);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'who matches your profile')]")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement WhoMatchesYourProfileText;
    public boolean isWhoMatchesYourProfileTextDisplayed() {
        return waitUtils.isElementDisplayed(WhoMatchesYourProfileText);
    }
    public void swipeUntilWhoMatchesYourProfileTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, WhoMatchesYourProfileText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Take Hair Test Again']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement TakeHairTestAgainText;
    public void swipeUntilTakeHairTestAgainTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, TakeHairTestAgainText);
    }
    public boolean isTakeHairTestAgainTextDisplayed() {
        return waitUtils.isElementDisplayed(TakeHairTestAgainText);
    }
    public void clickTakeHairTestAgainText() {
        waitUtils.waitForElement(TakeHairTestAgainText).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Your order is placed']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement YourOrderIsPlacedText;
    public boolean isYourOrderIsPlacedTextDisplayed() {
        return waitUtils.isElementDisplayed(YourOrderIsPlacedText);
    }

     @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/com.horcrux.svg.SvgView")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement HamburgerMenuButton;
    public boolean isHamburgerMenuButtonDisplayed() {
        return waitUtils.isElementDisplayed(HamburgerMenuButton);
    }
    public void clickHamburgerMenuButton() {
        waitUtils.waitForElement(HamburgerMenuButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Know The Root Cause Of Your Hair Loss']")
   // @iOSXCUITFindBy(accessibility = "PLACEHOLDER_continue_button") // TODO(iOS): replace once real app exists
    private WebElement knowTheRootCauseOfYourHairLossText;
    public boolean isKnowTheRootCauseOfYourHairLossTextDisplayed() {
        return waitUtils.isElementDisplayed(knowTheRootCauseOfYourHairLossText);
    }
    public boolean isKnowTheRootCauseOfYourHairLossTextDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(knowTheRootCauseOfYourHairLossText, timeout);
    }
    
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'The Hair Test')]")
   // @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement takeTheHairTestButton;

    public void clickTakeTheHairTest() {
        waitUtils.waitForElement(takeTheHairTestButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Traya Heroes']")
   // @iOSXCUITFindBy(accessibility = "PLACEHOLDER_continue_button") // TODO(iOS): replace once real app exists
    private WebElement trayaHeroesText;
    public boolean isTrayaHeroesTextDisplayed() {
        return waitUtils.isElementDisplayed(trayaHeroesText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='What Causes Hair Loss?']")
   // @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement whatCausesHairLossText;
    public void swipeUntilWhatCausesHairLossTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, whatCausesHairLossText);
    }
    public boolean isWhatCausesHairLossTextDisplayed() {
        return waitUtils.isElementDisplayed(whatCausesHairLossText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='How Traya Works']")
   // @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement howTrayaWorksText;
    public void swipeUntilHowTrayaWorksTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, howTrayaWorksText);
    }
    public boolean isHowTrayaWorksTextDisplayed() {
        return waitUtils.isElementDisplayed(howTrayaWorksText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Traya Plan Includes']")
   // @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement trayaPlanIncludesText;
    public void swipeUntilTrayaPlanIncludesTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, trayaPlanIncludesText);
    }
    public boolean isTrayaPlanIncludesTextDisplayed() {
        return waitUtils.isElementDisplayed(trayaPlanIncludesText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Need Help?']")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement needHelpText;
    public void swipeUntilNeedHelpTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, needHelpText);
    }
    public boolean isNeedHelpTextDisplayed() {
        return waitUtils.isElementDisplayed(needHelpText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Meet Our Team Of Doctors']")
    // @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement meetOurTeamOfDoctorsText;
    public void swipeUntilMeetOurTeamOfDoctorsTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, meetOurTeamOfDoctorsText);
    }
    public boolean isMeetOurTeamOfDoctorsTextDisplayed() {
        return waitUtils.isElementDisplayed(meetOurTeamOfDoctorsText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Google Reviews & Ratings']")
    // @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement googleReviewsAndRatingsText;
    public void swipeUntilGoogleReviewsAndRatingsTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, googleReviewsAndRatingsText);
    }
    public boolean isGoogleReviewsAndRatingsTextDisplayed() {
        return waitUtils.isElementDisplayed(googleReviewsAndRatingsText);
    }

}
