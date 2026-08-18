package com.company.framework.pages.male;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
public class Customer_Hamberger_Screen {

    private final WaitUtils waitUtils;

    public Customer_Hamberger_Screen(AppiumDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Your Orders']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement YourOrdersMenuItem;
    public boolean isYourOrdersMenuItemDisplayed() {
        return waitUtils.isElementDisplayed(YourOrdersMenuItem);
    }
    public void clickYourOrdersMenuItem() {
        waitUtils.waitForElement(YourOrdersMenuItem).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Logout']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement LogoutButton;
    public boolean isLogoutButtonDisplayed() {
        return waitUtils.isElementDisplayed(LogoutButton);
    }
    public void clickLogoutButton() {
        waitUtils.waitForElement(LogoutButton).click();
    }
    
     @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Logout']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement LogoutToggle_button;
    public boolean isLogoutToggle_buttonDisplayed() {
        return waitUtils.isElementDisplayed(LogoutToggle_button);
    }
    public void clickLogoutToggle_button() {
        waitUtils.waitForElement(LogoutToggle_button).click();
    }

}
