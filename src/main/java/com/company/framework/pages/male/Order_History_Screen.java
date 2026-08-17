package com.company.framework.pages.male;

import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

/**
 * "Your Orders" screen, reached via the hamburger menu's "Your Orders" item - shows an
 * All/Ongoing/Delivered/Cancelled/Others filter tab row (real android.widget.Button elements,
 * each with a content-desc matching its label) above a dated "Order History" list of order cards,
 * each opening its own Order_Details_Screen when tapped. Confirmed live to be a plain native
 * screen (unlike OrderCancellation_Screen's WebView) - ordinary .click() works throughout.
 */
public class Order_History_Screen {

    private final WaitUtils waitUtils;
    private final AppiumDriver driver;

    public Order_History_Screen(AppiumDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Your Orders']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement YourOrdersTitle;
    public boolean isYourOrdersTitleDisplayed() {
        return waitUtils.isElementDisplayed(YourOrdersTitle);
    }
    public boolean isYourOrdersTitleDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(YourOrdersTitle, timeout);
    }

    // --- Filter tabs ---

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='All']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement AllTabButton;
    public boolean isAllTabDisplayed() {
        return waitUtils.isElementDisplayed(AllTabButton);
    }

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Ongoing']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement OngoingTabButton;
    public boolean isOngoingTabDisplayed() {
        return waitUtils.isElementDisplayed(OngoingTabButton);
    }

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Delivered']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement DeliveredTabButton;
    public boolean isDeliveredTabDisplayed() {
        return waitUtils.isElementDisplayed(DeliveredTabButton);
    }
    public void clickDeliveredTab() {
        waitUtils.waitForElement(DeliveredTabButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Cancelled']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement CancelledTabButton;
    public boolean isCancelledTabDisplayed() {
        return waitUtils.isElementDisplayed(CancelledTabButton);
    }
    public void clickCancelledTab() {
        waitUtils.waitForElement(CancelledTabButton).click();
    }

    // Real label is "Others" (plural), not "Other".
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Others']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement OthersTabButton;
    public boolean isOthersTabDisplayed() {
        return waitUtils.isElementDisplayed(OthersTabButton);
    }

    // --- Order list ---

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Order History']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement OrderHistoryHeading;
    public boolean isOrderHistoryHeadingDisplayed() {
        return waitUtils.isElementDisplayed(OrderHistoryHeading);
    }
    public boolean isOrderHistoryHeadingDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(OrderHistoryHeading, timeout);
    }

    // The first order card under whichever tab is currently selected - each card is a single
    // clickable ViewGroup whose content-desc groups its date/status/Order ID/line items; scoped
    // here as the first such clickable sibling after the "Order History" heading, since "1st
    // order" is about position, not any one card's specific content.
    public void clickFirstOrder() {
        WebElement firstOrder = driver.findElement(AppiumBy.xpath(
                "(//android.widget.TextView[@text='Order History']/following-sibling::android.view.ViewGroup[@clickable='true'])[1]"));
        waitUtils.waitForElement(firstOrder).click();
    }

    // --- Leaving this screen ---

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Go back']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement GoBackButton;
    public boolean isGoBackButtonDisplayed() {
        return waitUtils.isElementDisplayed(GoBackButton);
    }
    public void clickGoBackButton() {
        waitUtils.waitForElement(GoBackButton).click();
    }
}
