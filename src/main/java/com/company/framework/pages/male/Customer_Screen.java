package com.company.framework.pages.male;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.company.framework.utils.SwipeUtils;
import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import java.time.Duration;

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

     @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/com.horcrux.svg.SvgView")
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

    // --- Order Summary screen (reached via the "Buy Again" tab) ---

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Order Summary']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement OrderSummaryText;
    public boolean isOrderSummaryTextDisplayed() {
        return waitUtils.isElementDisplayed(OrderSummaryText);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='PLACE ANOTHER ORDER']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement PlaceAnotherOrderButton;
    public boolean isPlaceAnotherOrderButtonDisplayed() {
        return waitUtils.isElementDisplayed(PlaceAnotherOrderButton);
    }
    public void clickPlaceAnotherOrderButton() {
        waitUtils.waitForElement(PlaceAnotherOrderButton).click();
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='View All Products']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement ViewAllProductsButton;
    public boolean isViewAllProductsButtonDisplayed() {
        return waitUtils.isElementDisplayed(ViewAllProductsButton);
    }

    // --- "Choose your plan" checkout screen (reached via "Place Another Order") ---

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Choose your plan']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement ChooseYourPlanText;
    public boolean isChooseYourPlanTextDisplayed() {
        return waitUtils.isElementDisplayed(ChooseYourPlanText);
    }

    // Live-verified real text on this screen - this reorder flow doesn't show separate "Product
    // you might like" or "Bill Details" headings; this discount line and the tax-inclusive total
    // line are the closest real equivalents. contains(), not exact match - the day count in
    // "ordering in N days" is dynamic (seen both "40 days" and "45 days" across runs).
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Special Discount for ordering in')]")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement DiscountText;
    public boolean isDiscountTextDisplayed() {
        return waitUtils.isElementDisplayed(DiscountText);
    }
    public void swipeUntilDiscountTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, DiscountText);
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
