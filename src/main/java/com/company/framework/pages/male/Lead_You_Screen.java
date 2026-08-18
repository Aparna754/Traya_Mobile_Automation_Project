package com.company.framework.pages.male;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
public class Lead_You_Screen {

    private final WaitUtils waitUtils;

    public Lead_You_Screen(AppiumDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='You']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement youButton;
    public boolean youButtonDisplayed() {
        return waitUtils.isElementDisplayed(youButton);
    }
    public void clickYouButton() {
        waitUtils.waitForElement(youButton).click();
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
