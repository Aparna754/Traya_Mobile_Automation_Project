package com.company.framework.pages.male;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Payment_Screen {
    private final WaitUtils waitUtils;

    public Payment_Screen(AppiumDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Proceed to Pay']")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement ProceedToPayButton;
    public boolean isProceedToPayButtonDisplayed() {
        return waitUtils.isElementDisplayed(ProceedToPayButton);
    }
    public void clickProceedToPayButton() {
        waitUtils.waitForElement(ProceedToPayButton).click();
    }
    

}
