package com.company.framework.pages.male;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.company.framework.utils.SwipeUtils;
import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import java.time.Duration;
import java.util.List;
public class Customer_Screen {

    private final WaitUtils waitUtils;
    private final AppiumDriver driver;

    public Customer_Screen(AppiumDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Your order is placed']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement YourOrderIsPlacedText;
    public boolean isYourOrderIsPlacedTextDisplayed() {
        return waitUtils.isElementDisplayed(YourOrderIsPlacedText);
    }
    public boolean isYourOrderIsPlacedTextDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(YourOrderIsPlacedText, timeout);
    }
    public void clickYourOrderIsPlacedText() {
        waitUtils.waitForElement(YourOrderIsPlacedText).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Your order is cancelled']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement YourOrderIsCancelledText;
    public boolean isYourOrderIsCancelledTextDisplayed() {
        return waitUtils.isElementDisplayed(YourOrderIsCancelledText);
    }
    public boolean isYourOrderIsCancelledTextDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(YourOrderIsCancelledText, timeout);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[translate(@text,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='DISMISS']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement DismissBannerButton;
    public boolean isDismissBannerButtonDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(DismissBannerButton, timeout);
    }
    public void clickDismissBannerButton() {
        waitUtils.waitForElement(DismissBannerButton).click();
    }

    public void refreshScreen() {
        Dimension size = driver.manage().window().getSize();
        int x = size.getWidth() / 2;
        int startY = (int) (size.getHeight() * 0.25);
        int endY = (int) (size.getHeight() * 0.75);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence pullDown = new Sequence(finger, 0);
        pullDown.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, startY));
        pullDown.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        pullDown.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), x, endY));
        pullDown.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(pullDown));
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='MY COACH']/preceding-sibling::android.view.ViewGroup[@clickable='true']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement HamburgerMenuButton;
    public boolean isHamburgerMenuButtonDisplayed() {
        return waitUtils.isElementDisplayed(HamburgerMenuButton);
    }
    public void clickHamburgerMenuButton() {
        waitUtils.waitForElement(HamburgerMenuButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Today's Checklist\"]")
//  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement TodaysChecklistText;
    public boolean isTodaysChecklistTextDisplayed() {
        return waitUtils.isElementDisplayed(TodaysChecklistText);
    }
    public boolean isTodaysChecklistTextDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(TodaysChecklistText, timeout);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Log Now']")
 //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement LogNowText;
    public boolean isLogNowTextDisplayed() {
        return waitUtils.isElementDisplayed(LogNowText);
    }
    public void swipeUntilLogNowTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, LogNowText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Free Advanced Scalp Test']")
 //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement FreeAdvancedScalpTestText;
    public boolean isFreeAdvancedScalpTestTextDisplayed() {
        return waitUtils.isElementDisplayed(FreeAdvancedScalpTestText);
    }
    public void swipeUntilFreeAdvancedScalpTestTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, FreeAdvancedScalpTestText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Diet Plan']")
 //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement DietPlanText;
    public boolean isDietPlanTextDisplayed() {
        return waitUtils.isElementDisplayed(DietPlanText);
    }
    public void swipeUntilDietPlanTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, DietPlanText);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Buy Again']")
 //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement BuyAgainButton;
    public boolean isBuyAgainButtonDisplayed() {
        return waitUtils.isElementDisplayed(BuyAgainButton);
    }
    public void clickBuyAgainButton() {
        waitUtils.waitForElement(BuyAgainButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Inclusive of all taxes']")
 //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement InclusiveOfAllTaxesText;
    public boolean isInclusiveOfAllTaxesTextDisplayed() {
        return waitUtils.isElementDisplayed(InclusiveOfAllTaxesText);
    }
    public void swipeUntilInclusiveOfAllTaxesTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, InclusiveOfAllTaxesText);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Continue']")
 //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement CheckoutContinueButton;
    public boolean isCheckoutContinueButtonDisplayed() {
        return waitUtils.isElementDisplayed(CheckoutContinueButton);
    }
    public void clickCheckoutContinueButton() {
        waitUtils.waitForElement(CheckoutContinueButton).click();
    }
}
