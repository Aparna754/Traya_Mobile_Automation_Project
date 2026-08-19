package com.company.framework.pages.female;

import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

/**
 * Returning-female-customer dashboard - confirmed live (mobile 8892257924, account "sapna") to be
 * structurally identical to the male flow's Customer_Screen ("Today's Checklist" hero, "Buy Again"
 * bottom-nav tab), unlike com.company.framework.pages.female.Home_Screen's "Hair Analysis Report"
 * hero layout seen on a different account state. Duplicated into this package per project
 * convention rather than shared across packages. Only the fields this package's tests actually use
 * are included here.
 */
public class Customer_Screen {

    private final WaitUtils waitUtils;

    public Customer_Screen(AppiumDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // Confirmed live: unlike the male flow's hamburger button (matched relative to a "MY COACH"
    // sibling elsewhere on the page), this button has no content-desc of its own - matched instead
    // as the clickable ViewGroup immediately preceding the "Hi, <name>" greeting text.
    @AndroidFindBy(xpath = "//android.widget.TextView[starts-with(@text,'Hi,')]/preceding-sibling::android.view.ViewGroup[@clickable='true']")
    private WebElement HamburgerMenuButton;
    public boolean isHamburgerMenuButtonDisplayed() {
        return waitUtils.isElementDisplayed(HamburgerMenuButton);
    }
    public void clickHamburgerMenuButton() {
        waitUtils.waitForElement(HamburgerMenuButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Today's Checklist\"]")
    private WebElement TodaysChecklistText;
    public boolean isTodaysChecklistTextDisplayed() {
        return waitUtils.isElementDisplayed(TodaysChecklistText);
    }
    public boolean isTodaysChecklistTextDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(TodaysChecklistText, timeout);
    }

    // Bottom-nav tab - confirmed live to carry the same content-desc as the male flow's
    // Customer_Screen.BuyAgainButton, unlike Home_Screen's "Take Hair Test Again" being reached via
    // a swipe-to-find flow instead.
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Buy Again']")
    private WebElement BuyAgainButton;
    public boolean isBuyAgainButtonDisplayed() {
        return waitUtils.isElementDisplayed(BuyAgainButton);
    }
    public void clickBuyAgainButton() {
        waitUtils.waitForElement(BuyAgainButton).click();
    }
}
