package com.company.framework.pages.female;

import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Hamburger side-menu (Your Orders / Refer a friend / Coins / Daily Reminder / All Products /
 * Help & Support / Policies / About Us / Logout) - confirmed live to carry the same content-desc
 * scheme as the male flow's Customer_Hamberger_Screen for the two items this package's tests use.
 */
public class Customer_Hamberger_Screen {

    private final WaitUtils waitUtils;

    public Customer_Hamberger_Screen(AppiumDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Your Orders']")
    private WebElement YourOrdersMenuItem;
    public boolean isYourOrdersMenuItemDisplayed() {
        return waitUtils.isElementDisplayed(YourOrdersMenuItem);
    }
    public void clickYourOrdersMenuItem() {
        waitUtils.waitForElement(YourOrdersMenuItem).click();
    }

    // Confirmed live: tapping this opens a "Logout?" confirmation dialog whose own confirm button
    // also renders with text/content-desc "Logout" - the two are never on screen at the same time
    // (the dialog covers/replaces this menu item), so PageFactory re-resolving the same simple
    // locator on each call is safe. Two separate method names are kept anyway, matching the male
    // package's two-step (menu item, then confirm) naming convention.
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Logout']")
    private WebElement LogoutButton;
    public boolean isLogoutButtonDisplayed() {
        return waitUtils.isElementDisplayed(LogoutButton);
    }
    public void clickLogoutButton() {
        waitUtils.waitForElement(LogoutButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Logout']")
    private WebElement LogoutConfirmButton;
    public boolean isLogoutConfirmButtonDisplayed() {
        return waitUtils.isElementDisplayed(LogoutConfirmButton);
    }
    public void clickLogoutConfirmButton() {
        waitUtils.waitForElement(LogoutConfirmButton).click();
    }
}
