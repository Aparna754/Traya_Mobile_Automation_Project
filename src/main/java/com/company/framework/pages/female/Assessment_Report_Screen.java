package com.company.framework.pages.female;

import com.company.framework.utils.SwipeUtils;
import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

/**
 * Post-Hair-Test "Assessment Report" screen for the female flow - reached immediately after the
 * last quiz question. Shares the "Assessment Report" heading and "who matches your profile" /
 * "Google Reviews & Ratings" copy with the male flow's equivalent screen, but with female-specific
 * sections ("For Complete Hair Care", "Frequently Asked Questions") in place of the male screen's
 * "Free Add-Ons"/"How does it work?".
 */
public class Assessment_Report_Screen {

    private final WaitUtils waitUtils;
    private final AppiumDriver driver;

    public Assessment_Report_Screen(AppiumDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Assessment Report']")
    private WebElement AssessmentReportText;
    public boolean isAssessmentReportTextDisplayed() {
        return waitUtils.isElementDisplayed(AssessmentReportText);
    }
    public boolean isAssessmentReportTextDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(AssessmentReportText, timeout);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'who matches your profile')]")
    private WebElement WhoMatchesYourProfileText;
    public boolean isWhoMatchesYourProfileTextDisplayed() {
        return waitUtils.isElementDisplayed(WhoMatchesYourProfileText);
    }
    public void swipeUntilWhoMatchesYourProfileTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, WhoMatchesYourProfileText);
    }

    // The "for complete hair care" bonus section has no single heading with that literal text -
    // confirmed live it's a "Hair Coach Support" / "Flexible diet plan" free-add-on card pair
    // (the female equivalent of the male flow's "Free Add-Ons" section).
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Hair Coach Support']")
    private WebElement ForCompleteHairCareText;
    public boolean isForCompleteHairCareTextDisplayed() {
        return waitUtils.isElementDisplayed(ForCompleteHairCareText);
    }
    public void swipeUntilForCompleteHairCareTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, ForCompleteHairCareText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Google Reviews & Ratings']")
    private WebElement GoogleReviewsAndRatingsText;
    public boolean isGoogleReviewsAndRatingsTextDisplayed() {
        return waitUtils.isElementDisplayed(GoogleReviewsAndRatingsText);
    }
    public void swipeUntilGoogleReviewsAndRatingsTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, GoogleReviewsAndRatingsText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Frequently Asked Questions')]")
    private WebElement FrequentlyAskedQuestionsText;
    public boolean isFrequentlyAskedQuestionsTextDisplayed() {
        return waitUtils.isElementDisplayed(FrequentlyAskedQuestionsText);
    }
    public void swipeUntilFrequentlyAskedQuestionsTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, FrequentlyAskedQuestionsText);
    }

    @AndroidFindBy(xpath = "//*[contains(@text,'Buy Now') or contains(@content-desc,'Buy Now')]")
    private WebElement BuyNowButton;
    public boolean isBuyNowButtonDisplayed() {
        return waitUtils.isElementDisplayed(BuyNowButton);
    }
    public void clickBuyNowButton() {
        waitUtils.waitForElement(BuyNowButton).click();
    }
}
