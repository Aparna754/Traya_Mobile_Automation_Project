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
    // Tapping this banner opens the order's own "Order Summary" details screen (Cancel Order lives there).
    public void clickYourOrderIsPlacedText() {
        waitUtils.waitForElement(YourOrderIsPlacedText).click();
    }

    // Dashboard banner shown after a cancellation goes through - replaces the "Your order is
    // placed" banner above once the order is cancelled ("No refund due as order was COD" is the
    // COD-specific subtext seen live underneath it).
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Your order is cancelled']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement YourOrderIsCancelledText;
    public boolean isYourOrderIsCancelledTextDisplayed() {
        return waitUtils.isElementDisplayed(YourOrderIsCancelledText);
    }
    public boolean isYourOrderIsCancelledTextDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(YourOrderIsCancelledText, timeout);
    }

    // This order-status banner (whichever of the two above is currently showing) is sticky -
    // confirmed live that it persists across app relaunches (noReset) and does NOT get replaced
    // by a fresh order's own banner until manually dismissed, so a leftover banner from an earlier
    // run/order can shadow the one a test just earned. DISMISS its own text case wasn't confirmed
    // live via a page-source dump (only via a raw coordinate tap, which did work) - matched
    // case-insensitively here so this doesn't silently miss it over a stray case difference.
    @AndroidFindBy(xpath = "//android.widget.TextView[translate(@text,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='DISMISS']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement DismissBannerButton;
    public boolean isDismissBannerButtonDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(DismissBannerButton, timeout);
    }
    public void clickDismissBannerButton() {
        waitUtils.waitForElement(DismissBannerButton).click();
    }

    // Pull-to-refresh gesture for the home dashboard - the order-status banner above (and other
    // dashboard state) has been seen live to not reflect a just-completed action (e.g. a
    // cancellation) right after navigating back onto this screen. There's no WebView here for
    // driver.navigate().refresh() to act on (this is the native RN checklist dashboard), and no
    // dedicated "refresh" control exists on-screen, so a genuine drag-down touch gesture (same
    // W3C Actions approach used elsewhere in this app, e.g. ThankYou_Screen's w3cTap) is what
    // actually triggers this screen's own RefreshControl.
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

    // Was previously a 17-level-deep absolute XPath pinned to exact sibling indices - confirmed
    // live to break the instant an extra top-of-screen element (e.g. the "Your order is
    // cancelled" banner) shifts those indices by even one. Anchored here on the "MY COACH"
    // button's content-desc instead (a stable, always-present sibling in the same top bar row)
    // and picking out its one clickable preceding sibling - that's this hamburger icon's outer
    // ViewGroup (the icon itself, an SvgView, exposes no content-desc/clickable of its own).
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='MY COACH']/preceding-sibling::android.view.ViewGroup[@clickable='true']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement HamburgerMenuButton;
    public boolean isHamburgerMenuButtonDisplayed() {
        return waitUtils.isElementDisplayed(HamburgerMenuButton);
    }
    public void clickHamburgerMenuButton() {
        waitUtils.waitForElement(HamburgerMenuButton).click();
    }

    // --- Returning-customer "checklist" home dashboard (e.g. "Hi, <name>") ---

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

    // Bottom nav tab - present/clickable regardless of scroll position on the checklist screen.
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Buy Again']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement BuyAgainButton;
    public boolean isBuyAgainButtonDisplayed() {
        return waitUtils.isElementDisplayed(BuyAgainButton);
    }
    public void clickBuyAgainButton() {
        waitUtils.waitForElement(BuyAgainButton).click();
    }

    // --- "Choose your plan" checkout screen (reached via "Place Another Order") ---
    // (ChooseYourPlanText/ThreeMonthsPlan/DiscountText moved to OrderSummary_Screen.java)

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
