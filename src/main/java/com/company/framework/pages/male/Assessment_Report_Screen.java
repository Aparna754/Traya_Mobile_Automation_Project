package com.company.framework.pages.male;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.company.framework.utils.SwipeUtils;
import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Assessment_Report_Screen {

    private final WaitUtils waitUtils;
    private final AppiumDriver driver;

    public Assessment_Report_Screen(AppiumDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

      @AndroidFindBy(xpath = "//android.widget.TextView[@text='Assessment Report']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement AssessmentReportText;
    public boolean isAssessmentReportTextDisplayed() {
        return waitUtils.isElementDisplayed(AssessmentReportText);
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

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Your Hair Loss Root Causes']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement YourHairLossRootCausesText;
    public boolean isYourHairLossRootCausesTextDisplayed() {
        return waitUtils.isElementDisplayed(YourHairLossRootCausesText);
    }
    public void swipeUntilYourhairlossrootcausesTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, YourHairLossRootCausesText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Free Add-Ons']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement FreeAddOnsText;
    public boolean isFreeAddOnsTextDisplayed() {
        return waitUtils.isElementDisplayed(FreeAddOnsText);
    }
   public void swipeUntilFreeAddOnsTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, FreeAddOnsText);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Buy Now']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement BuyNowButton;
    public boolean isBuyNowButtonDisplayed() {
        return waitUtils.isElementDisplayed(BuyNowButton);
    }
    public void clickBuyNowButton() {
        waitUtils.waitForElement(BuyNowButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='How does it work?']")
//   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement howDoesItWorkText;
    public boolean isHowDoesItWorkTextDisplayed() { 
        return waitUtils.isElementDisplayed(howDoesItWorkText); 
    }
    public void swipeUntilHowDoesItWorkTextVisible() { 
        SwipeUtils.swipeUntilVisible(driver, howDoesItWorkText); 
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Continue']")
//   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement continueButton2;
    public boolean isContinueButtonDisplayed2() {
         return waitUtils.isElementDisplayed(continueButton2); 
        }
    public void clickContinueButton2() { 
        waitUtils.waitForElement(continueButton2).click(); 
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Book A Call']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement bookACallText;
    public boolean isBookACallTextDisplayed() { 
        return waitUtils.isElementDisplayed(bookACallText); 
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Stage - 6']")
//   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement stage6Text;
    public boolean isStage6TextDisplayed() { 
        return waitUtils.isElementDisplayed(stage6Text); 
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='What happens at this stage?']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement whatHappensAtThisStageText;
    public boolean isWhatHappensAtThisStageTextDisplayed() {
        return waitUtils.isElementDisplayed(whatHappensAtThisStageText);
    }
    public void swipeUntilWhatHappensAtThisStageTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, whatHappensAtThisStageText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='iconIcon']")
//   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement BookACallBackButton;
    public void clickBookACallBackButton() { 
        waitUtils.waitForElement(BookACallBackButton).click(); 
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='iconIcon']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement assessmentReportBackButton;
    public void clickassessmentReportBackButton() { 
        waitUtils.waitForElement(assessmentReportBackButton).click(); 
    }
    
}
