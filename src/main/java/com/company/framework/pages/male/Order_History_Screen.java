package com.company.framework.pages.male;

import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;
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

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Others']")
//  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement OthersTabButton;
    public boolean isOthersTabDisplayed() {
        return waitUtils.isElementDisplayed(OthersTabButton);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Order History']")
//  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement OrderHistoryHeading;
    public boolean isOrderHistoryHeadingDisplayed() {
        return waitUtils.isElementDisplayed(OrderHistoryHeading);
    }
    public boolean isOrderHistoryHeadingDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(OrderHistoryHeading, timeout);
    }

    public void clickFirstOrder() {
        WebElement firstOrder = driver.findElement(AppiumBy.xpath(
                "(//android.widget.TextView[@text='Order History']/following-sibling::android.view.ViewGroup[@clickable='true'])[1]"));
        waitUtils.waitForElement(firstOrder).click();
    }

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
